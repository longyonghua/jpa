package com.longge.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author longge
 * @create 2019-10-18 下午8:11
 */
@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    /*配置客户和联系人的一对多关系
        mappedBy 对方配置关系的属性名称
        cascade=CascadeType.ALL 级联所有操作，即对主表执行增删改操作时，从表中对应的记录也一并操作
    */
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<LinkMan> linkMans = new HashSet<>();

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


    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", linkMans=" + linkMans +
                '}';
    }
}
