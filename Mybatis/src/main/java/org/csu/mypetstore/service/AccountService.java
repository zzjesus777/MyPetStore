package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Bannerdata;
import org.csu.mypetstore.domain.Profile;
import org.csu.mypetstore.domain.Signon;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.HibernateUtil;
import org.csu.mypetstore.persistence.impl.AccontDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        this.accountDAO = new AccontDAOImpl();
    }

    public Account getAccount(String username) {
        return accountDAO.getAccountByUsername(username);
    }

    public Account getAccount(String username, String password) {
        return accountDAO.getAccountByUsernameAndPassword(username,password);
    }

    public void insertAccount(Account account) {
        accountDAO.insertAccount(account);
    }

    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }

    public String getBannerName(String favouriteCategoryId){
       return accountDAO.getBannerName(favouriteCategoryId);
    }
}
