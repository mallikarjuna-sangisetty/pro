package com.project.traffic.service;

import java.util.List;
import java.util.Set;

import com.project.traffic.model.BookTicket;
import com.project.traffic.model.CoPassenger;
import com.project.traffic.model.TransportaionDetails;

public interface BookingService {

	int book(BookTicket bookTicket);
	List<TransportaionDetails> getAvailableTransport(String source,String destination);
	List<TransportaionDetails> findByProvider(String provider);
	boolean bookForCopassengers(Set<CoPassenger> coPassengers);
	boolean cancelTicket(BookTicket bookTicket);
}
