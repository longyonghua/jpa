package com.longge.test;

import com.longge.dao.CustomerDao;
import com.longge.domain.Customer;
import com.longge.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author longge
 * @create 2019-10-20 下午7:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {
    @Autowired
    private CustomerDao dao;

    @Test
    @Transactional
    public void test1(){
        Customer c = dao.getOne(1l);
        Set<LinkMan> linkMans = c.getLinkMans();
        for(LinkMan lm : linkMans){
            System.out.println(lm);
        }
    }

}
