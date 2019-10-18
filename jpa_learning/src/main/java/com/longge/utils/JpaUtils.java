package com.longge.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author longge
 * @create 2019-10-18 下午3:27
 */
//解决实体管理器工厂的浪费资源和耗时问题
public class JpaUtils {
    private static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
