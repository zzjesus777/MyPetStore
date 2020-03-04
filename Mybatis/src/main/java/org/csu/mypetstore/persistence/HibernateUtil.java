package org.csu.mypetstore.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
        private static SessionFactory sf = null;

        private static SessionFactory getSessionFactory(){
            if (sf == null){
                sf = new Configuration().configure().buildSessionFactory();
            }
            return sf;
        }

        public static Session getSession(){
            return getSessionFactory().openSession();
        }

        public static void main(String[] args){

        }
}

