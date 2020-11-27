package com.lolaage.crm.service.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.lolaage.crm.service.CrmServiceApplication;
import com.lolaage.crm.service.model.Org;
import com.lolaage.crm.service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description: UserOrgTest
 * @author: lijiayu
 * @date: 2020-11-18 17:44
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmServiceApplication.class)
public class UserOrgTest {

    @Autowired
    IUserOrgService userOrgService;
    @Autowired
    IOrgService orgService;
    @Autowired
    IUserService userService;

    @Test
    public void listAllTest() {
        List<Org> orgs = orgService.list();
        List<User> users = userService.list();
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("D:/doc/testFiles/企业人员信息+电话+邮箱.xls"), 1);
        List<Map<String,Object>> readAll = reader.readAll();
        for(Map<String,Object> obj : readAll){
            Iterator<Map.Entry<String, Object>> it = obj.entrySet().iterator();
            StringBuilder sb = new StringBuilder("insert into t_user_org(f_user_id, f_org_id,f_is_leader) VALUES(");
            sb.append(getUser(users, String.valueOf(obj.get("联系电话"))).getId()).append(",").append(getOrg(orgs, String.valueOf(obj.get("部门"))).getId()).append(",1);");
            System.out.println(sb.toString());
        }
    }


    public Org getOrg(List<Org> list, String OrgName) {
        for (Org org : list) {
            if (org.getName().equals(OrgName)) {
                return org;
            }
        }
        return null;
    }

    public User getUser(List<User> users, String telephone) {
        for (User user : users) {
            if (user.getTelephone().equals(telephone)) {
                return user;
            }
        }
        return null;
    }
}
