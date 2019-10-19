package com.longge.test;

import com.longge.dao.CustomerDao2;
import com.longge.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author longge
 * @create 2019-10-19 下午1:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {
    @Autowired
    CustomerDao2 dao;

    @Test
    public void testFindJpql(){
        Customer c = dao.findJpql("龙哥");
        System.out.println(c);
    }
    @Test
    public void findByNameAndId(){
        Customer c = dao.findByNameAndId("大胖子", 4l);
        System.out.println(c);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void updateById(){
        dao.updateById(3l,"小龙龙");
    }
    
    @Test
    public void findSql(){
        List<Object[]> list = dao.findSql();
        for(Object[] obj : list){
            System.out.println(Arrays.toString(obj));
        }
    }
    @Test
    public void findByNameSql(){
        List<Object[]> list = dao.findByNameSql("%龙哥");
        for(Object[] obj : list){
            System.out.println(Arrays.toString(obj));
        }
    }

    @Test
    public void findByName(){
        Customer c = dao.findByName("大胖子");
        System.out.println(c);
    }
    @Test
    public void findByNameLike(){
        List<Customer> c = dao.findByNameLike("%龙哥");
        c.stream().forEach(System.out::println);
    }
    @Test
    public void findByNameLikeAndIndustry(){
        Customer c = dao.findByNameLikeAndIndustry("%龙哥","软件");
        System.out.println(c);
    }

}
