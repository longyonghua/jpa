package com.longge.test;

import com.longge.dao.CustomerDao;
import com.longge.dao.LinkManDao;
import com.longge.domain.Customer;
import com.longge.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author longge
 * @create 2019-10-20 上午9:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional //配置事务
    @Rollback(false) //不自动回滚
    public void testAdd(){
        Customer customer = new Customer();
        customer.setName("画中仙2");
        LinkMan linkMan = new LinkMan();
        linkMan.setName("龙哥2");
        //建立关系
        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        Customer c = customerDao.getOne(1l);
        customerDao.delete(c);
    }

    @Test
    @Transactional
    public void test2(){
        Customer c = customerDao.getOne(5l);
        System.out.println(c);
//        customerDao.delete(c);
    }

}
