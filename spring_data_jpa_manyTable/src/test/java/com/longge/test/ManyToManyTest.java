package com.longge.test;

import com.longge.dao.RoleDao;
import com.longge.dao.UserDao;
import com.longge.domain.Role;
import com.longge.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author longge
 * @create 2019-10-20 下午4:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Test
    @Transactional
    @Rollback(false)
    public void test1(){
        User user = new User();
        user.setName("大龙哥");
        Role role = new Role();
        role.setName("管理员");
        //建立关系
        user.getRoles().add(role);
        //role.getUsers().add(user);
        userDao.save(user);
    }


}
