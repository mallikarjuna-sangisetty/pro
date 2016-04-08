package com.project.traffic.repositoryImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jfree.data.category.DefaultCategoryDataset;
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

    public DefaultPieDataset getData(String query,int top){

        Session session = DataBaseUtil.getSession();
        session.beginTransaction();

        Query q = session.createSQLQuery(query);
        if(top > 0){
            q.setFirstResult(0);
            q.setMaxResults(top);
        }
        HashMap<String, Integer> data = new HashMap<>();
        List<Object[]> rows = q.list();
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Object[] row : rows) {
            dataSet.setValue(((Comparable) row[0])+"-"+((BigInteger)row[1]).intValue(),((BigInteger)row[1]).intValue());
        }

        session.getTransaction().commit();
        session.close();

        return dataSet;
    }

    public DefaultCategoryDataset getDataForLineChart(String query,int top){

        Session session = DataBaseUtil.getSession();
        session.beginTransaction();

        Query q = session.createSQLQuery(query);
        List<Integer> years = new ArrayList<>();
        int currentYear = Calendar.getInstance().YEAR;
        for(int i=1;i<=top;i++){
            years.add(currentYear--);
        }
        q.setParameter(1 , years);
        HashMap<String, Integer> data = new HashMap<>();
        List<Object[]> rows = q.list();
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Object[] row : rows) {
            dataSet.addValue(((BigInteger)row[1]).intValue(),((Comparable) row[0]),((Comparable) row[1]));
        }

        session.getTransaction().commit();
        session.close();

        return dataSet;
    }
    
    public DefaultCategoryDataset getDataBarChart(){

        Session session = DataBaseUtil.getSession();
        session.beginTransaction();

        Query q = session.createSQLQuery("SELECT distinct(t.provider),count(t.seats) count FROM transportaiondetails t  group by provider");
        HashMap<String, Integer> data = new HashMap<>();
        List<Object[]> rows = q.list();
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Object[] row : rows) {
            dataSet.addValue(((BigInteger)row[1]).intValue(),((Comparable) row[0]),((Comparable) row[0])+"-"+((BigInteger)row[1]).intValue());
        }

        session.getTransaction().commit();
        session.close();

        return dataSet;
    }

    
   /* public DefaultPieDataset getDataForProvidervsBooked(){

        Session session = DataBaseUtil.getSession();
        session.beginTransaction();

        Query q = session.createSQLQuery("select distinct(provider),count(*) booked  from transportaiondetails t,bookticket b where t.transport_id = b.transport_id group by provider");
        HashMap<String, Integer> data = new HashMap<>();
        List<Object[]> rows = q.list();
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Object[] row : rows) {
            dataSet.setValue(((Comparable) row[0])+"-"+((BigInteger)row[1]).intValue(),((BigInteger)row[1]).intValue());
        }

        session.getTransaction().commit();
        session.close();

        return dataSet;
    }

    public DefaultPieDataset getValuedCustomers(int people){

        Session session = DataBaseUtil.getSession();
        session.beginTransaction();

        Query q = session.createSQLQuery("SELECT distinct(u.userName),count(*) tickets FROM user u,bookticket b where u.user_id = b.user_id group by username order by tickets desc");
        q.setFirstResult(0);
        q.setMaxResults(people);
        HashMap<String, Integer> data = new HashMap<>();
        List<Object[]> rows = q.list();
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Object[] row : rows) {
            dataSet.setValue(((Comparable) row[0])+"-"+((BigInteger)row[1]).intValue(),((BigInteger)row[1]).intValue());
        }

        session.getTransaction().commit();
        session.close();

        return dataSet;
    }
    
    public DefaultPieDataset getMostTravelledDestinations(int count){

        Session session = DataBaseUtil.getSession();
        session.beginTransaction();

        Query q = session.createSQLQuery("select distinct(destination),count(*) counter from transportaiondetails t,bookticket b where t.transport_id = b.transport_id group by destination order by counter desc");
        q.setFirstResult(0);
        q.setMaxResults(count);
        HashMap<String, Integer> data = new HashMap<>();
        List<Object[]> rows = q.list();
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Object[] row : rows) {
            dataSet.setValue(((Comparable) row[0])+"-"+((BigInteger)row[1]).intValue(),((BigInteger)row[1]).intValue());
        }

        session.getTransaction().commit();
        session.close();

        return dataSet;
    }
    
    public DefaultPieDataset getMostTrafficSources(int count){

        Session session = DataBaseUtil.getSession();
        session.beginTransaction();

        Query q = session.createSQLQuery("select distinct(source),count(*) counter from transportaiondetails t,bookticket b where t.transport_id = b.transport_id group by source order by counter desc");
        q.setFirstResult(0);
        q.setMaxResults(count);
        HashMap<String, Integer> data = new HashMap<>();
        List<Object[]> rows = q.list();
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Object[] row : rows) {
            dataSet.setValue(((Comparable) row[0])+"-"+((BigInteger)row[1]).intValue(),((BigInteger)row[1]).intValue());
        }

        session.getTransaction().commit();
        session.close();

        return dataSet;
    }*/
}
