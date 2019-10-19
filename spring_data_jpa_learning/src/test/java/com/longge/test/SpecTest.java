package com.longge.test;

import com.longge.dao.CustomerDao3;
import com.longge.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

/**
 * @author longge
 * @create 2019-10-19 下午4:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {
    @Autowired
    CustomerDao3 dao;

    @Test
    public void testSpec(){
        Specification<Customer> spec = (root, criteriaQuery, criteriaBuilder) -> {
            //获取比较的属性
            Path<Object> name = root.get("name");
            //构造查询条件
            return criteriaBuilder.equal(name, "小龙龙");
        };
        //Specification<Customer> spec = (root,cq,cb) -> cb.equal(root.get("name"),"小龙龙");
        Optional<Customer> optional = dao.findOne(spec);
        System.out.println(optional.get());
    }

    @Test
    public void testSpec2(){
        Specification<Customer> spec = (root, criteriaQuery, criteriaBuilder) -> {
            //获取比较的属性
            Path<Object> name = root.get("name");
            Path<Object> industry = root.get("industry");
            //构建查询条件
            Predicate p1 = criteriaBuilder.equal(name, "龙哥");
            Predicate p2 = criteriaBuilder.equal(industry, "软件1");
            //将多个查询条件组合到一起 and、or
            Predicate p = criteriaBuilder.and(p1, p2);
            return p;
        };
        List<Customer> list = dao.findAll(spec); //使用findOne还是findAll，视可能返回的结果个数而定
        list.stream().forEach(System.out::println);
    }

    @Test
    public void testSpec3(){
        Specification<Customer> spec = (root,criteriaQuery,crieriaBuilder)->{
            Path<Object> name = root.get("name");
            //对于equal——得到path对象后直接进行比较；
            //对于gt|lt|ge|le|like——得到path对象，根据其指定的比较类型 path.as(类型的字节码对象)，再进行比较
            Predicate p = crieriaBuilder.like(name.as(String.class), "%龙哥");
            return p;
        };
        List<Customer> list = dao.findAll(spec);
        list.stream().forEach(System.out::println);
    }

    @Test
    public void testSpec4(){
        Specification<Customer> spec = (root,criteriaQuery,crieriaBuilder)->{
            Path<Object> name = root.get("name");
            Predicate p = crieriaBuilder.like(name.as(String.class), "%龙哥");
            return p;
        };
        //创建排序对象
        //参数1：顺序（Sort.Direction.DESC倒序、Sort.Direction.ASC升序）参数2：用于排序的属性名
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<Customer> list = dao.findAll(spec,sort);
        list.stream().forEach(System.out::println);
    }

    @Test
    /*
        Specification查询条件 Pageable分页参数（查询的页码，每页查询的条数）
        findAll(Specification,Pageable)：带有条件的分页
        findAll(Pageable)：没有条件的分页
        返回：Page——spring data jpa封装好的pagebean对象，含有数据列表、总条数
    */
    public void testSpec5(){
        Pageable pageable = PageRequest.of(0,2); //查询第1页，每页查询2条
        Page<Customer> page = dao.findAll(pageable);
        System.out.println("总记录数："+page.getTotalElements()+"总页数"+page.getTotalPages());
        List<Customer> list = page.getContent();
        list.stream().forEach(System.out::println);
    }

}
