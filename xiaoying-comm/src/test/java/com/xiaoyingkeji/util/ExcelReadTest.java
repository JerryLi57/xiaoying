package com.xiaoyingkeji.service.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description: excel测试
 * @author: lijiayu
 * @date: 2020-11-18 17:02
 **/
public class ExcelReadTest {

    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("D:/doc/testFiles/企业人员信息+电话+邮箱.xls"), 0);
        List<Map<String,Object>> readAll = reader.readAll();
        ExcelReader reader2 = ExcelUtil.getReader(FileUtil.file("D:/doc/testFiles/企业人员信息+电话+邮箱.xls"), 1);
        List<Map<String,Object>> readAll2 = reader2.readAll();
        for(Map<String,Object> obj : readAll){
            Iterator<Map.Entry<String, Object>> it = obj.entrySet().iterator();
            StringBuilder sb = new StringBuilder("insert into t_user(f_password,f_name,f_username,f_telephone,f_email) values('e10adc3949ba59abbe56e057f20f883e'");
            sb.append(",'").append(obj.get("姓名")).append("','").append(obj.get("工号")).append("','").append(obj.get("联系电话")).append("','").append(obj.get("邮箱"))
                    .append("');");
            System.out.println(sb.toString());
        }

    }
}
