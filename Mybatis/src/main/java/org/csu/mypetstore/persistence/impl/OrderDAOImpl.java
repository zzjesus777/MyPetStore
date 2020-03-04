package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.Orderstatus;
import org.csu.mypetstore.persistence.HibernateUtil;
import org.csu.mypetstore.persistence.OrderDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private Session session;

    @Override
    public List<Order> getOrdersByUsername(String username) {
        List<Order> orderList = new ArrayList<Order>();
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Order.class);
        criteria.add(Restrictions.eq("username", username));
        orderList = criteria.list();
        session.getTransaction().commit();
        session.close();
        return orderList;
    }

    @Override
    public Order getOrder(int orderId) {
        Order order = null;
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Order.class);
        criteria.add(Restrictions.eq("orderId", orderId));
        List<Order> accountList = criteria.list();
        if (!accountList.isEmpty()) {
            order = accountList.get(0);
        }
        session.getTransaction().commit();
        session.close();
        return order;
    }

    @Override
    public void insertOrder(Order order) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Orderstatus orderstatus = order.getOrderstatus();
        session.save(orderstatus);
        session.save(order);
        session.getTransaction().commit();
        session.close();
    }
    
}
