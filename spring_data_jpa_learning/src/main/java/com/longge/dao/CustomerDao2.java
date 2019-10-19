package com.longge.dao;

import com.longge.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-19 下午1:12
 */
public interface CustomerDao2 extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    //根据名称查询
    @Query("from Customer where name=?1")
    Customer findJpql(String name);

    //根据名称和id查询
    @Query("from Customer where name=?1 and id=?2")
    Customer findByNameAndId(String name,Long id);

    //根据id更新name
    @Query("update Customer set name=?2 where id=?1")
    @Modifying
    void updateById(Long id,String name);

    //-----------------sql查询-------------

    //查询全部
    @Query(value="select * from customer",nativeQuery = true)
    List<Object[]> findSql();

    //条件查询
    @Query(value="select * from customer where name like ?1",nativeQuery = true)
    List<Object[]> findByNameSql(String name);

    //-----------------方法命名规则查询-------------

    Customer findByName(String name);

    List<Customer> findByNameLike(String name);

    Customer findByNameLikeAndIndustry(String name,String industry);
}
