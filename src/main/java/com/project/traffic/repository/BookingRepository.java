package com.project.traffic.repository;

import java.util.List;
import java.util.Set;

import com.project.traffic.model.BookTicket;
import com.project.traffic.model.CoPassenger;
import com.project.traffic.model.TransportaionDetails;

public interface BookingRepository {
	int book(BookTicket bookTicket);
	List<TransportaionDetails> getAvailableTransport(String source,String destination);
	List<TransportaionDetails> findByProvider(String provider);
	List<String> getAllProviders();
	List<TransportaionDetails> getAllBookingsByUser(Integer id);
	boolean bookForCopassengers(Set<CoPassenger> passengers);
	boolean cancelTicket(BookTicket bookTicket);
	List<BookTicket> getAllBookedDetails(String username);
	List<BookTicket> getAllBookedDetails(int user_id);
	List<CoPassenger> coPassengerList(int book_id);
}
