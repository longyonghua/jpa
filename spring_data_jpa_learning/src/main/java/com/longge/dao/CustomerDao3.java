package com.longge.dao;

import com.longge.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author longge
 * @create 2019-10-19 下午4:41
 */
public interface CustomerDao3 extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
}
