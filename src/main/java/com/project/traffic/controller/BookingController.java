package com.project.traffic.controller;

import java.util.List;
import java.util.Set;

import com.project.traffic.model.BookTicket;
import com.project.traffic.model.CoPassenger;
import com.project.traffic.model.TransportaionDetails;
import com.project.traffic.service.BookingService;
import com.project.traffic.serviceImpl.BookingServiceImpl;

public class BookingController {

	BookingService bookingService;
	
	public BookingController() {
		bookingService = new BookingServiceImpl();
	}
	public int book(BookTicket bookTicket) {
		return bookingService.book(bookTicket);
	}
	public boolean bookForCopassengers(Set<CoPassenger> coPassengers) {
		return bookingService.bookForCopassengers(coPassengers);
	}
	public List<TransportaionDetails> getAvailableTransport(String source, String destination) {
		return bookingService.getAvailableTransport(source, destination);
	}

	public List<TransportaionDetails> findByProvider(String provider) {
		return bookingService.findByProvider(provider);
	}
	public List<BookTicket> getAllBookedDetails(String username) {
		return bookingService.getAllBookedDetails(username);
	}
	public List<BookTicket> getAllBookedDetails(int user_id){
		return bookingService.getAllBookedDetails(user_id);
	}
	public boolean cancelTicket(BookTicket bookTicket){
		return bookingService.cancelTicket(bookTicket);
	}
	
	public List<CoPassenger> coPassengerList(int book_id){
		return bookingService.coPassengerList(book_id);
	}
}
