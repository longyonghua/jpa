package com.longge.test;

import com.longge.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @author longge
 * @create 2019-10-18 下午4:29
 */
public class JpqlTest {

    @Test
    public void testFindAll(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = "from Customer";
        Query query = em.createQuery(jpql); //创建Query查询对象，该对象才是执行jpql的对象
        List list = query.getResultList(); //发送查询，并封装结果集
        list.stream().forEach(System.out::println);

        tx.commit();
        em.close();
    }

    @Test
    public void testOrder(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = "from Customer order by id desc";
        Query query = em.createQuery(jpql); //创建Query查询对象，该对象才是执行jpql的对象
        List list = query.getResultList(); //发送查询，并封装结果集
        list.stream().forEach(System.out::println);

        tx.commit();
        em.close();
    }

    @Test
    public void testCount(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = "select count(id) from Customer";
        Query query = em.createQuery(jpql);
        Object count = query.getSingleResult();
        System.out.println(count);

        tx.commit();
        em.close();
    }

    @Test
    public void testPaged(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = "from Customer";
        Query query = em.createQuery(jpql);
        //对参数赋值——分页参数
        query.setFirstResult(0); //起始索引
        query.setMaxResults(2); //每次查询的条数
        List list = query.getResultList();
        list.stream().forEach(System.out::println);

        tx.commit();
        em.close();
    }

    @Test
    public void testCondition(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String jpql = "from Customer where name like ?1";
        Query query = em.createQuery(jpql);
        //对参数赋值——占位符参数
        query.setParameter(1,"%龙哥"); //参数1：占位符的索引位置；参数2：取值
        List list = query.getResultList();
        list.stream().forEach(System.out::println);

        tx.commit();
        em.close();
    }

}
