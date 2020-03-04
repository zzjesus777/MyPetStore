package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Account;

public interface AccountDAO {
    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(String username, String password);

    void insertAccount(Account account);

    void updateAccount(Account account);

    public String getBannerName(String favouriteCategoryId);
}
