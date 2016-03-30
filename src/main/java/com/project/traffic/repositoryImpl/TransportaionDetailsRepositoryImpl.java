package com.project.traffic.repositoryImpl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jfree.data.general.DefaultPieDataset;

import com.project.traffic.model.TransportaionDetails;
import com.project.traffic.persistenceUtil.DataBaseUtil;
import com.project.traffic.repository.TransportaionDetailsRepository;

public class TransportaionDetailsRepositoryImpl implements TransportaionDetailsRepository{

	@Override
	public boolean feed(List<TransportaionDetails> details) {
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();
		
		for (TransportaionDetails transportaionDetails : details) {
			session.save(transportaionDetails);
		}

		session.getTransaction().commit();
		session.close();
		return true;
	}
	
	public DefaultPieDataset getData(){
		
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();

		Query q = session.createSQLQuery("SELECT distinct(t.provider),count(t.seats) count FROM transportaiondetails t	group by provider");
		HashMap<String, Integer> data = new HashMap<>();
		List<Object[]> rows = q.list();
		DefaultPieDataset dataSet = new DefaultPieDataset();
		for (Object[] row : rows) {
			dataSet.setValue(((Comparable) row[0])+"-"+((BigInteger)row[1]).floatValue(),((BigInteger)row[1]).floatValue());
		}

		session.getTransaction().commit();
		session.close();
		
		return dataSet;
	}
}
