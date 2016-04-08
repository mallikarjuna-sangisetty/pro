package com.project.traffic.repositoryImpl;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.project.traffic.enums.EnumUtils.StatusType;
import com.project.traffic.model.BookTicket;
import com.project.traffic.model.CoPassenger;
import com.project.traffic.model.TransportaionDetails;
import com.project.traffic.persistenceUtil.DataBaseUtil;
import com.project.traffic.repository.BookingRepository;
import com.project.traffic.util.DateUtil;

public class BookingRepositoryImpl implements BookingRepository{

	@Override
	public int book(BookTicket bookTicket) {
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();
		session.save(bookTicket);
		session.getTransaction().commit();
		session.close();
		return bookTicket.getId();
	}

	@Override
	public List<TransportaionDetails> getAvailableTransport(String source,String destination){
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(TransportaionDetails.class);
		criteria.add(Restrictions.like("source", source,MatchMode.ANYWHERE));
		criteria.add(Restrictions.like("destination", destination,MatchMode.ANYWHERE));
		List<TransportaionDetails> rows = criteria.list();
		session.getTransaction().commit();
		session.close();
		return rows;
	}

	@Override
	public List<TransportaionDetails> findByProvider(String provider) {
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(TransportaionDetails.class);
		criteria.add(Restrictions.like("provider", provider,MatchMode.ANYWHERE));
		List<TransportaionDetails> rows = criteria.list();
		session.getTransaction().commit();
		session.close();
		return rows;
	}

	@Override
	public List<String> getAllProviders() {
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(TransportaionDetails.class);
		List<String> rows = criteria.list();
		session.getTransaction().commit();
		session.close();
		return rows;
	}

	@Override
	public List<TransportaionDetails> getAllBookingsByUser(Integer id) {
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(TransportaionDetails.class);
		criteria.add(Restrictions.eq("", id));
		List<TransportaionDetails> rows = criteria.list();
		session.getTransaction().commit();
		session.close();
		return rows;
	}

	@Override
	public boolean bookForCopassengers(Set<CoPassenger> passengers) {
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();
		for (CoPassenger coPassenger : passengers) {
			session.save(coPassenger);
		}
		session.getTransaction().commit();
		session.close();
		return true;
	}


	@Override
	public boolean cancelTicket(BookTicket bookTicket) {
		Session session = DataBaseUtil.getSession();
		session.beginTransaction();
		session.update(bookTicket);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public List<BookTicket> getAllBookedDetails(String username) {
		Session session = DataBaseUtil.getSession();
		Criteria criteria = session.createCriteria(BookTicket.class);
		List<BookTicket> rows = criteria.list();
		return rows;
	}

	@Override
	public List<BookTicket> getAllBookedDetails(int user_id) {
		Session session = DataBaseUtil.getSession();
		Criteria criteria = session.createCriteria(BookTicket.class);
		criteria.add(Restrictions.eq("user.id", user_id));
		criteria.add(Restrictions.eq("status", StatusType.ACTIVE));
		criteria.add(Restrictions.ge("travelDate",DateUtil.getDate()));
		List<BookTicket> rows = criteria.list();
		return rows;
	}
	@Override
	public List<CoPassenger> coPassengerList(int book_id) {
		Session session = DataBaseUtil.getSession();
		Criteria criteria = session.createCriteria(CoPassenger.class);
		criteria.add(Restrictions.eq("book_id", book_id));
		List<CoPassenger> rows = criteria.list();
		return rows;	
	}
}
