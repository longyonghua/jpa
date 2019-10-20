package com.longge.domain;

import javax.persistence.*;

/**
 * @author longge
 * @create 2019-10-19 下午9:02
 */
@Entity
@Table(name="linkman")
public class LinkMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    //配置联系人到客户的多对一关系
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", customerId=" + customer.getId() +
                '}';
    }
}
