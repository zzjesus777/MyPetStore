package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.persistence.HibernateUtil;
import org.csu.mypetstore.persistence.LineItemDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class LineItemDAOImpl implements LineItemDAO {

    private Session session;

    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItemList = new ArrayList<LineItem>();
        LineItem result = null;
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LineItem.class);
        criteria.add(Restrictions.eq("orderId", orderId));
        lineItemList = criteria.list();
        session.getTransaction().commit();
        session.close();
        return lineItemList;
    }
    @Override
    public void insertLineItem(LineItem lineItem) {
        session = HibernateUtil.getSession();
        session.beginTransaction();

        session.save(lineItem);
        session.getTransaction().commit();
        session.close();
    }
}
