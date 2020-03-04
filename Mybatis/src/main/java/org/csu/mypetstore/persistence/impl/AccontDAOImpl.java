package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AccontDAOImpl implements AccountDAO {

    private Session session;

    @Override
    public Account getAccountByUsername(String username) {
        Account result = null;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Account.class);
        criteria.add(Restrictions.eq("username", username));
        List<Account> accountList = criteria.list();
        if (!accountList.isEmpty()) {
            result = accountList.get(0);
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Account getAccountByUsernameAndPassword(String username, String password) {
        Account result = null;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Signon.class);
        criteria.add(Restrictions.eq("username", username));
        criteria.add(Restrictions.eq("password", password));
        List<Signon> signonList = criteria.list();
        if (signonList != null) {
            Criteria criteria1 = session.createCriteria(Account.class);
            criteria1.add(Restrictions.eq("username", username));
            List<Account> accountList = criteria1.list();
            result = accountList.get(0);
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void insertAccount(Account account) {
        session = HibernateUtil.getSession();
        session.beginTransaction();

        Profile profile = account.getProfile();
        Signon signon = account.getSignon();
        session.save(profile);
        session.save(signon);
        session.save(account);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateAccount(Account account) {
        session = HibernateUtil.getSession();
        session.beginTransaction();

        Profile profile = account.getProfile();
        Signon signon = account.getSignon();
        session.update(profile);
        session.update(signon);
        session.update(account);
        if (account.getPassword() != null && account.getPassword().length() > 0) {
            session.update(account);
        }
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public String getBannerName(String favouriteCategoryId){
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Bannerdata.class);
        criteria.add(Restrictions.eq("favouriteCategoryId",favouriteCategoryId));
        List<Bannerdata> bannerdataList = criteria.list();
        session.getTransaction().commit();
        session.close();
        return bannerdataList.get(0).getBannerName();
    }
}
