package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.persistence.CategoryDAO;
import org.csu.mypetstore.persistence.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private Session session;

    @Override
    public List<Category> getCategoryList() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Category.class);
        List<Category> categoryList = criteria.list();

        session.getTransaction().commit();
        session.close();
        return categoryList;
    }

    @Override
    public Category getCategory(String categoryId) {
        Category category = null;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Category.class);
        criteria.add(Restrictions.eq("categoryId",categoryId));
        List<Category> categoryList = criteria.list();
        if (!categoryList.isEmpty()){
            category = categoryList.get(0);
        }
        session.getTransaction().commit();
        session.close();
        return category;
    }
}
