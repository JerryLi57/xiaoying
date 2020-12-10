package com.xiaoyingkeji.comm.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import com.xiaoyingkeji.comm.config.MailConfig;
import com.xiaoyingkeji.comm.constant.ErrorEnum;
import com.xiaoyingkeji.comm.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;

/**
 * @description: 发送邮件工具类
 * @author: zhanglei
 * @date: 2020-11-17 15:39
 **/
@Slf4j
@Component
public class MailUtil {

    @Autowired
    private MailConfig mailConfig;

    private String host;

    private String username;

    private String password;

    private String from;

    private String nickName;

    private String mailSubject;


    @PostConstruct
    public void init() throws Exception {
        host = mailConfig.getHost();
        username = mailConfig.getUsername();
        password = mailConfig.getPassword();
        from = mailConfig.getFrom();
        nickName = mailConfig.getNickName();
        mailSubject = mailConfig.getMailSubject();

        log.warn("开始加载>>>>>>>>>>>>>>mail等相关配置 host:" + host + ";username:" + username + ";password:"
                + password + ";from:" + from);

    }


    /**
     * 获取邮件配置Properties
     *
     * @return 邮件配置
     */
    private Session getMailConfig() throws GeneralSecurityException {
        Properties prop = new Properties();
        prop.setProperty("mail.host", host);
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        //设置中文字符过长后，导致附件成为.bin的未知文件
        System.getProperties().setProperty("mail.mime.splitlongparameters", "false");


        // 1、创建定义整个应用程序所需的环境信息的 Session 对象
        return Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮件用户名、授权码
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private MimeMessage getMailObject(Session session) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);
        // 防止成为垃圾邮件
        message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869");
        // 发件人
        message.setFrom(new InternetAddress(from, nickName, "UTF-8"));
        // 邮件的标题
        message.setSubject(mailSubject);
        return message;
    }

    /**
     * 发送邮件
     *
     * @param toEmail 收件人邮箱
     * @param content 内容
     */
    public void sendMail(String toEmail, String mailSubject, String content) {
        try {

            Session session = getMailConfig();
            // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            //session.setDebug(true);

            // 2、通过session得到transport对象
            Transport ts = session.getTransport();

            // 3、使用邮箱的用户名和授权码连上邮件服务器
            ts.connect(host, username, password);

            // 4、创建邮件
            MimeMessage message = getMailObject(session);

            message.setSubject(mailSubject);
            // 收件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            // 邮件设置内容
            message.setContent(content, "text/html;charset=UTF-8");
            // 邮件保存改变格式
            message.saveChanges();

            // 发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 群发邮件
     *
     * @param toEmails    收件人邮箱
     * @param content     内容
     * @param attachments 附件列表
     */
    public void sendMailByGroup(List<String> toEmails, String mailSubject, String content, List<Long> attachments) {
        try {
            Session session = getMailConfig();
            // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            // session.setDebug(true);

            // 2、通过session得到transport对象
            Transport ts = session.getTransport();

            // 3、使用邮箱的用户名和授权码连上邮件服务器
            ts.connect(host, username, password);

            // 4、创建邮件
            MimeMessage message = getMailObject(session);


            //4.1、设置邮件主题
            message.setSubject(mailSubject);

            // 5、添加接收人地址
            InternetAddress[] address = new InternetAddress[toEmails.size()];

            for (int i = 0; i < toEmails.size(); i++) {
                address[i] = new InternetAddress(toEmails.get(i));
            }
            message.addRecipients(Message.RecipientType.TO, address);

            // 创建多重消息
            Multipart multipart = new MimeMultipart();

            // 6、设置发送内容
            BodyPart messageBodyPart = new MimeBodyPart();

            // 6.1 设置发送的内容
            messageBodyPart.setContent(content, "text/html;charset=UTF-8");

            multipart.addBodyPart(messageBodyPart);

            // 6.2 附件部分
            if (attachments != null) {
                for (Long attachmentId : attachments) {
                    BodyPart attachmentBodyPart = new MimeBodyPart();


                    // 防止urlDataSource三次获取资源，改用HttpURLConnection 获取byte后set到ByteArrayDataSource
                    StringBuilder str = new StringBuilder();
                    String conetentType = "application/octet-stream";
                    String url = "";
                    byte[] data = dowloadWebFile(url, str);
                    if (str.length() <= 0) {
                        conetentType = str.toString();
                    }

                    DataSource dataSource = new ByteArrayDataSource(data, conetentType);
                    attachmentBodyPart.setDataHandler(new DataHandler(dataSource));

                    // MimeUtility.encodeWord可以避免文件名乱码
                    /*FileDetailDto fileDetailDto = fileService.getFileDetail(attachmentId);
                    attachmentBodyPart.setFileName(MimeUtility.encodeText(fileDetailDto == null ? "" : fileDetailDto.getName()) + "." + fileDetailDto.getExt());

                    multipart.addBodyPart(attachmentBodyPart);*/
                }
            }

            // 发送完整消息
            message.setContent(multipart);

            // 邮件保存改变格式
            message.saveChanges();

            // 发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 下载网络文件
     *
     * @param filePath
     * @return
     * @throws ServiceException
     */
    public byte[] dowloadWebFile(String filePath, StringBuilder conetentType) throws ServiceException {
        try {
            URL url = new URL(filePath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为60秒
            conn.setConnectTimeout(60 * 1000);
            //防止屏蔽程序抓取而返回403错误
//            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //设置请求头
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");

            //得到输入流
            InputStream inputStream = conn.getInputStream();

            //获取自己数组
            byte[] bs = readInputStream(inputStream);

            conetentType.append(conn.getContentType());
            return bs;
        } catch (Exception e) {
            throw new ServiceException(ErrorEnum.SERVICE_ERROR, "下载附件异常：" + e.getMessage());
        }
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }



}

