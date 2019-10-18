package com.longge.test;

import com.longge.domain.Customer;
import com.longge.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author longge
 * @create 2019-10-18 上午11:28
 */
public class JpaTest {

    @Test
    public void testSave(){
        //1.加载配置文件创建实体管理器工厂对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体管理器工厂获取实体管理器
        EntityManager em = factory.createEntityManager();
        //3.获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //4.完成增删改查操作
        Customer customer = new Customer();
        customer.setName("龙哥");
        customer.setIndustry("软件");
        em.persist(customer);
        //5.提交事务|回滚事务
        tx.commit();
        //6.释放资源
        em.close();
        factory.close();
    }

    @Test
    public void testFind(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //Customer customer = em.find(Customer.class, 1l);
        Customer customer = em.getReference(Customer.class, 1l);
        System.out.println(customer);
        tx.commit();
        em.close();
    }

    @Test
    public void testRemove(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.find(Customer.class,1l);
        em.remove(customer);
        tx.commit();
        em.close();
    }

    @Test
    public void testUpdate(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.find(Customer.class,2l);
        customer.setName("超级大龙哥");
        em.merge(customer);
        tx.commit();
        em.close();
    }

}
