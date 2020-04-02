package com.mediamovimiento.controller;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mediamovimiento.model.Category;
import com.mediamovimiento.model.Order;
import com.mediamovimiento.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        //insert
        hql = "insert into Category (id,name) " +
                "select id,name from Oldcategory";
        query = entityManager.createQuery(hql);
        int rowsAffected;
        rowsAffected = query.executeUpdate();
        if(rowsAffected>0){
            System.out.println(rowsAffected + "(s) fueron insertados.");
        }
        //update
        hql = "update Product set price = :price where id = :id";
        query = entityManager.createQuery(hql);
        query.setParameter("price",10f);
        query.setParameter("id",1l);
        rowsAffected = query.executeUpdate();
        if(rowsAffected>0){
            System.out.println("Updated " + rowsAffected + " rows.");
        }
        //delete
        hql = "delete from Category where id=:id";
        query = entityManager.createQuery(hql);
        query.setParameter("id",3);
        rowsAffected = query.executeUpdate();
        if(rowsAffected>0){
            System.out.println("Deleted " + rowsAffected + " rows.");
        }
        //join
        hql = "from Product p inner join p.category with p.price > 5";
        query = entityManager.createQuery(hql);
        List<Object[]> resultList = query.getResultList();
        for(Object[] aRow: resultList){
            Product product = (Product) aRow[0];
            Category category = (Category) aRow[1];
            System.out.println(product.getName() + " - " + category.getName());
        }
        //sorting
        hql = "from Product order by price ASC";
        query = entityManager.createQuery(hql);
        productList = query.getResultList();
        for (Product aProduct : productList) {
            System.out.println(aProduct.getName() + "\t - " + aProduct.getPrice());
        }
        //group by
        hql = "select sum(p.price), p.category.name from Product p group by category";
        query = entityManager.createQuery(hql);
        resultList = query.getResultList();
        for (Object[] aRow : resultList) {
            Double sum = (Double) aRow[0];
            String category = (String) aRow[1];
            System.out.println(category + " - " + sum);
        }
        //pagination
        hql = "from Product";
        query = entityManager.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(2);
        productList = query.getResultList();
        for (Product aProduct : productList) {
            System.out.println(aProduct.getName() + "\t - " + aProduct.getPrice());
        }
        //date range
        try {
            hql = "from Order where purchaseDate >= :beginDate and purchaseDate <= :endDate";
            query = entityManager.createQuery(hql);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginDate = dateFormatter.parse("2020-04-01 00:00:00");
            query.setParameter("beginDate", beginDate);
            Date endDate = dateFormatter.parse("2020-04-02 00:00:00");
            query.setParameter("endDate", endDate);

            List<Order> orderList = query.getResultList();
            for (Order anOrder : orderList) {
                System.out.println(anOrder.getProduct().getName() + " - "
                        +  anOrder.getAmount() + " - "
                        + anOrder.getPurchaseDate());
            }

        }catch (ParseException pe){
            pe.printStackTrace();
        }
        //expressions
        hql = "from Product p where p.price > 5.0";
        query = entityManager.createQuery(hql);

        productList = query.getResultList();
        for (Product aProduct : productList) {
            System.out.println(aProduct.getName() + "\t - " + aProduct.getPrice());
        }
        //aggregate functions
        hql = "select count(name) from Product";
        query = entityManager.createQuery(hql);

        List list = query.getResultList();
        Number number = (Number) list.get(0);
        System.out.println("count(name) = "+number.intValue());
        System.exit(0);
    }

}
