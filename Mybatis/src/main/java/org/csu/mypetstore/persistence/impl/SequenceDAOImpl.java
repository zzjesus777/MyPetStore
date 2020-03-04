package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.HibernateUtil;
import org.csu.mypetstore.persistence.SequenceDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SequenceDAOImpl implements SequenceDAO {

    private Session session;

    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence sequence1 = null;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Sequence.class);
        criteria.add(Restrictions.eq("name", sequence.getName()));
        List<Sequence> sequenceList = criteria.list();
        if (!sequenceList.isEmpty()) {
            sequence1 = sequenceList.get(0);
        }
        session.getTransaction().commit();
        session.close();
        return sequence1;
    }

    @Override
    public void updateSequence(Sequence sequence) {
        session = HibernateUtil.getSession();
        session.beginTransaction();

        session.update(sequence);
        session.getTransaction().commit();
        session.close();
    }
}
