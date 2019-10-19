package com.longge.test;

import com.longge.dao.CustomerDao;
import com.longge.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author longge
 * @create 2019-10-18 下午8:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {
    @Autowired
    CustomerDao dao;

    @Test
    public void testFindAll(){
        List<Customer> customers = dao.findAll();
        customers.stream().forEach(System.out::println);
    }

    @Test
    public void testFindOne(){
        Optional<Customer> c = dao.findById(1l);
        System.out.println(c.get());

        //对于老点的版本，可以用Customer c = dao.findOne(1l);
        //对于新版本的findOne方法，需要这样使用：
        Customer customer = new Customer();
        customer.setId(2l);
        Optional<Customer> c2 = dao.findOne(Example.of(customer));
        System.out.println(c2.get());

        /*使用getOne会报错：
        org.hibernate.LazyInitializationException: could not initialize proxy [com.longge.domain.Customer#1] - no Session
        解决办法：在实体类上添加注解 @Proxy(lazy = false)
        */
        Customer c3 = dao.getOne(3l);
        System.out.println(c3);
    }

    @Test
    public void testSave(){
        /*save() 保存或者更新
        若传递的对象参数未设置主键id属性，表示保存
        若传递的对象参数设置了主键id属性，则会根据id查询数据
            若查到了数据，则进行更新
            若没查到数据，则进行保存
        */
        Customer c = new Customer();
        c.setId(5l);
        c.setName("大胖子");
        c.setIndustry("会计");
        dao.save(c);
    }

    @Test
    public void testDelete(){
        dao.deleteById(5l);
    }


    //-----------------------------查询方法详解--------------------------------------

    @Test
    public void testCount(){
        long count = dao.count();
        System.out.println(count);

        Customer c = new Customer();
        c.setName("臭大胖子");
        long count1 = dao.count(Example.of(c));
        System.out.println(count1);
    }
    @Test
    public void testExists(){
        boolean b = dao.existsById(4l);
        System.out.println(b);

        Customer customer = new Customer();
        customer.setName("超级大龙哥");
        boolean b1 = dao.exists(Example.of(customer));
        System.out.println(b1);
    }
    @Test
    @Transactional
    public void testGetOne(){
        Customer customer = dao.getOne(4l);
        System.out.println(customer);
    }




}
