package com.mediamovimiento.controller;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mediamovimiento.model.Category;
import com.mediamovimiento.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Tutorial https://www.codejava.net/frameworks/hibernate/hibernate-query-language-hql-example
 */
public class HQLExamples {
    private static SessionFactory sessionFactory;
    public static void main(String args[]){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("StoreDB");
        EntityManager entityManager = factory.createEntityManager();


        entityManager.getTransaction().begin();

        //Select
        String hql = "from Category";
        Query query = entityManager.createQuery(hql);
        List<Category> listCategories = query.getResultList();

        for(Category category: listCategories){
            System.out.println(category.getName());
        }
        //Select join
        hql = "from Product where category.name = 'Verduras'";
        query = entityManager.createQuery(hql);
        List<Product> productList = query.getResultList();
        for(Product product: productList){
            System.out.println(product.getName());
        }
        //named parameters
        hql = "from Product where description like :parameter";
        String parameter = "%mgrs%";
        query = entityManager.createQuery(hql);
        query.setParameter("parameter",parameter);
        productList = query.getResultList();
        for(Product product: productList){
            System.out.println(product.getName());
        }
        //
        System.exit(0);
    }

}
