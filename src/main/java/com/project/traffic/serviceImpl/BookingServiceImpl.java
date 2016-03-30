package com.project.traffic.serviceImpl;

import java.util.List;
import java.util.Set;

import com.project.traffic.enums.EnumUtils.StatusType;
import com.project.traffic.model.BookTicket;
import com.project.traffic.model.CoPassenger;
import com.project.traffic.model.TransportaionDetails;
import com.project.traffic.repository.BookingRepository;
import com.project.traffic.repositoryImpl.BookingRepositoryImpl;
import com.project.traffic.service.BookingService;

public class BookingServiceImpl implements BookingService{

	private BookingRepository bookingRepository;
	
	public BookingServiceImpl() {
		bookingRepository = new BookingRepositoryImpl();
	}
	@Override
	public int book(BookTicket bookTicket) {
		return bookingRepository.book(bookTicket);
	}

	@Override
	public List<TransportaionDetails> getAvailableTransport(String source, String destination) {
		return bookingRepository.getAvailableTransport(source, destination);
	}

	@Override
	public List<TransportaionDetails> findByProvider(String provider) {
		return bookingRepository.findByProvider(provider);
	}
	@Override
	public boolean bookForCopassengers(Set<CoPassenger> passengers) {
		return bookingRepository.bookForCopassengers(passengers);
	}
	@Override
	public boolean cancelTicket(BookTicket bookTicket) {
		bookTicket.setStatus(StatusType.INACTIVE);
		return bookingRepository.cancelTicket(bookTicket);
	}

}
