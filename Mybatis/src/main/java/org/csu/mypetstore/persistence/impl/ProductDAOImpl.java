package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.HibernateUtil;
import org.csu.mypetstore.persistence.ProductDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private Session session;

    @Override
    public Product getProduct(String productId) {

        Product product = null;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("productId",productId));
        List<Product> productList = criteria.list();
        if (!productList.isEmpty()){
            product = productList.get(0);
        }
        session.getTransaction().commit();
        session.close();
        return product;
    }

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("categoryId",categoryId));
        List<Product> productList = criteria.list();
        session.getTransaction().commit();
        session.close();
        return productList;
    }

    // TODO enable using more than one keyword
    @Override
    public List<Product> searchProductList(String keyword) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.like("name",keyword));
        List<Product> productList = criteria.list();
        session.getTransaction().commit();
        session.close();
        return productList;
    }
}
