package com.lolaage.crm.service.service;

import com.lolaage.crm.service.CrmServiceApplication;
import com.lolaage.crm.service.utils.FileServerIntranetHelper;
import com.lolaage.crm.service.utils.mail.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhanglei
 * @date:
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmServiceApplication.class)
public class emailTest {

    @Autowired
    MailUtil mailUtil;
    @Autowired
    FileServerIntranetHelper fileServerIntranetHelper;

    @Test
    public void listAllTest() throws Exception {
        List<String> email = new ArrayList<>();
        email.add("1lei.zhang@lolaage.com");
        List<Long> fileList = new ArrayList<>();
        fileList.add(2647L);
        fileList.add(2655L);
        fileList.add(2646L);
        String fileUrl = fileServerIntranetHelper.getDownloadUrl(2654L);
        mailUtil.sendMailByGroup(email, "", "测试:" + fileUrl, fileList);
    }


}
