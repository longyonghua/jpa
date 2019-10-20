package com.longge.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author longge
 * @create 2019-10-20 下午4:26
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Long id;
    @Column(name="u_name")
    private String name;

    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL)
    @JoinTable(name = "user_and_role",
        //当前对象在中间表的外键
        joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "u_id")},
        //对方对象在中间表的外键
        inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "r_id")}
    )
    private Set<Role> roles = new HashSet<>();

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
