package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.persistence.HibernateUtil;
import org.csu.mypetstore.persistence.ItemDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDAOImpl implements ItemDAO {

    private Session session;
    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {
        session = HibernateUtil.getSession();
        session.beginTransaction();

        Inventory inventory = new Inventory();
        inventory.setQuantity((Integer) param.get("increment"));
        inventory.setItemId((String) param.get("itemId"));
        session.update(inventory);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int qty = -1;
        Inventory inventory = null;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Inventory.class);
        criteria.add(Restrictions.eq("itemId",itemId));
        List<Inventory> productList = criteria.list();
        if (!productList.isEmpty()){
            inventory = productList.get(0);
            qty = inventory.getQuantity();
        }
        session.getTransaction().commit();
        session.close();
        return qty;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Item.class);
        criteria.add(Restrictions.eq("productId",productId));
        List<Item> itemList = criteria.list();
        session.getTransaction().commit();
        session.close();
        return itemList;
    }

    @Override
    public Item getItem(String itemId) {
        Item item = null;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Item.class);
        criteria.add(Restrictions.eq("itemId",itemId));
        List<Item> productList = criteria.list();
        if (!productList.isEmpty()){
            item = productList.get(0);
        }
        session.getTransaction().commit();
        session.close();
        return item;
    }
}
