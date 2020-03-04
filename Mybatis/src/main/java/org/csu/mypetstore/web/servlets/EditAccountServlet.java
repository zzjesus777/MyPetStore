package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Bannerdata;
import org.csu.mypetstore.domain.Profile;
import org.csu.mypetstore.domain.Signon;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditAccountServlet extends HttpServlet {
    private String username;
    private String password;
    private String repassword;
    private String email;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String favouriteCategoryId;
    private String languagePreference;
    private boolean listOption = false;
    private String[] listOptions ;
    private boolean bannerOption = false;
    private String[] bannerOptions;
    private String BannerName;
    private static final String VIEW_EDIT_FORM = "/WEB-INF/jsp/account/EditAccountForm.jsp";
    private AccountService accountService;
    private Account account;
    private Profile profile;
    private Signon signon;
    private Bannerdata bannerdata;
    private Account account1;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        account1 = (Account)session.getAttribute("account");
        username = account1.getUsername();
            password = request.getParameter("password");
            repassword = request.getParameter("repassword");
            email = request.getParameter("account.email");
            firstName = request.getParameter("account.firstName");
            lastName = request.getParameter("account.lastName");
            zip = request.getParameter("account.zip");
            country = request.getParameter("account.country");
            address1 = request.getParameter("account.address1");
            address2 = request.getParameter("account.address2");
            city = request.getParameter("account.city");
            phone = request.getParameter("account.phone");
            state = request.getParameter("account.state");
            favouriteCategoryId = request.getParameter("account.favouriteCategoryId");
            languagePreference = request.getParameter("account.languagePreference");
            listOptions = request.getParameterValues("account.listOption");
            bannerOptions = request.getParameterValues("account.bannerOption");
            if (listOptions !=null&&listOptions.length>0) {
                listOption = true;
            }
            else
                listOption = false;
            if (bannerOptions !=null&&bannerOptions.length>0) {
                bannerOption = true;
            }
            else
                bannerOption = false;
            if (!email.isEmpty()&&!firstName.isEmpty()&&!lastName.isEmpty()&&
                    !zip.isEmpty()&&!country.isEmpty()&&!address1.isEmpty()&&!city.isEmpty()&&!phone.isEmpty()&&!state.isEmpty()
                    &&password.equals(repassword)) {
                account = new Account();
                profile = new Profile();
                signon = new Signon();
                bannerdata = new Bannerdata();
                account.setUsername(username);
                signon.setUsername(username);
                signon.setPassword(password);
                account.setSignon(signon);
                account.setEmail(email);
                account.setFirstName(firstName);
                account.setLastName(lastName);
                account.setZip(zip);
                account.setCountry(country);
                account.setCity(city);
                account.setAddress1(address1);
                account.setAddress2(address2);
                account.setPhone(phone);
                account.setState(state);
                accountService = new AccountService();
                BannerName = accountService.getBannerName(favouriteCategoryId);
                bannerdata.setFavouriteCategoryId(favouriteCategoryId);
                bannerdata.setBannerName(BannerName);
                profile.setUsername(username);
                profile.setBannerdata(bannerdata);
                profile.setFavouriteCategoryId(favouriteCategoryId);
                profile.setLanguagePreference(languagePreference);
                profile.setListOption(listOption);
                profile.setBannerOption(bannerOption);
                account.setProfile(profile);
                session.setAttribute("account",account);
                accountService.updateAccount(account);
                List<String> languages = new ArrayList<String>();
                languages.add("english");
                languages.add("japanese");

                List<String> categories = new ArrayList<String>();
                categories.add("FISH");
                categories.add("DOGS");
                categories.add("REPTILES");
                categories.add("CATS");
                categories.add("BIRDS");
                request.setAttribute("languages",languages);
                request.setAttribute("categories",categories);
                request.getRequestDispatcher(VIEW_EDIT_FORM).forward(request, response);
        }
        List<String> languages = new ArrayList<String>();
        languages.add("english");
        languages.add("japanese");

        List<String> categories = new ArrayList<String>();
        categories.add("FISH");
        categories.add("DOGS");
        categories.add("REPTILES");
        categories.add("CATS");
        categories.add("BIRDS");
        request.setAttribute("languages",languages);
        request.setAttribute("categories",categories);
        request.getRequestDispatcher(VIEW_EDIT_FORM).forward(request,response);
    }
}
