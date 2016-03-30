package com.project.traffic.uiImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import com.project.traffic.constants.ConsoleMessageConstants;
import com.project.traffic.controller.BookingController;
import com.project.traffic.enums.EnumUtils.StatusType;
import com.project.traffic.mail.SSLMailUtil;
import com.project.traffic.model.BookTicket;
import com.project.traffic.model.CoPassenger;
import com.project.traffic.model.TransportaionDetails;
import com.project.traffic.model.User;
import com.project.traffic.session.AppSession;
import com.project.traffic.util.DateUtil;
import com.project.traffic.util.InputFromConsoleUtil;

public class UserMenuHandler {
		BookingController bookingController;
	public UserMenuHandler() {
		bookingController = new BookingController();
	}
	public void handleBooking(){
		String source = null;
		String destination = null;
		try {
			source = InputFromConsoleUtil.getString(ConsoleMessageConstants.SOURCE, true);
			destination = InputFromConsoleUtil.getString(ConsoleMessageConstants.DESTINATION, true);
			List<TransportaionDetails> list = bookingController.getAvailableTransport(source, destination);
			BookTicket ticket = new BookTicket();
			ticket.setTravelDate(InputFromConsoleUtil.getDate(ConsoleMessageConstants.TRAVEL_DATE, true));
			ticket.setStatus(StatusType.ACTIVE);
			displayList(list);
			int index = InputFromConsoleUtil.getIndex(ConsoleMessageConstants.TAKE_INDEX, list.size());
			TransportaionDetails details = list.get(index);
			int people = InputFromConsoleUtil.getInteger(ConsoleMessageConstants.NOOFPEOPLETRAVELLING);
			Set<CoPassenger> coPassengers = new HashSet<>();
			
			User user = (User) AppSession.session.get("loginUser");
			
			ticket.setTransportaionDetails(details);
			ticket.setUser(user);
			ticket.setCancelledDate(null);
			ticket.setBookedDate(DateUtil.getDate());
			int bookId = bookingController.book(ticket);
			
			for(int i = 1;i <= people ; i++){
				System.out.println("Passenger "+i+" details:");
				CoPassenger coPassenger = new CoPassenger();
				coPassenger.setBook_id(bookId);
				coPassenger.setName(InputFromConsoleUtil.getString(ConsoleMessageConstants.VALID_FNAME, true));
				coPassenger.setAge(InputFromConsoleUtil.getInteger(ConsoleMessageConstants.VALID_AGE));
				coPassengers.add(coPassenger);
			}
			bookingController.bookForCopassengers(coPassengers);
			SSLMailUtil.sendTicketBookingMail(user,details);
			JOptionPane.showMessageDialog(null,"Ticket Successfully booked from "+source+" to "+destination,"Information",JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Ticket booking failed from "+source+" to "+destination,"Information",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	/**
	 * Displays all records
	 * 
	 * @param usersList
	 */
	public static void displayList(List<TransportaionDetails> usersList) {
		System.out.println(
				"****************************************************************************************************************************************************");
		String format = "|%1$-5s|%2$-15s|%3$-20s|%4$-15s|%5$-15s|%6$-20s|%7$-10s|\n";
		System.out.format(format, "SNo", "Source", "Destination", "Arrival", "Departure", "Journey time", "Fare");
		System.out.println(
				"****************************************************************************************************************************************************");
		for (int i = 0; i < usersList.size(); i++) {

			String source = (usersList.get(i).getSource() == null || usersList.get(i).getSource().isEmpty()) ? "---"
					: usersList.get(i).getSource();
			String destination = (usersList.get(i).getDestination() == null || usersList.get(i).getDestination().isEmpty())
					? "---" : usersList.get(i).getDestination();
			String arrival = (usersList.get(i).getArrival() == null || usersList.get(i).getArrival().isEmpty())
					? "---" : usersList.get(i).getArrival();
			String departure = (usersList.get(i).getDeparture() == null) ? "---" : usersList.get(i).getDeparture() + "";
			String journeyTime = (usersList.get(i).getJourneyTime() == null) ? "---" : usersList.get(i).getJourneyTime() + "";
			String fare = (usersList.get(i).getPrice() == null) ? "---" : usersList.get(i).getPrice() + "";
			
			System.out.format(format, i + 1, source, destination, arrival, departure,journeyTime,fare);
		}
	}
}
