package com.project.traffic.uiImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.project.traffic.constants.ConsoleMessageConstants;
import com.project.traffic.constants.MailConstants;
import com.project.traffic.controller.BookingController;
import com.project.traffic.controller.UserController;
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

	private static final Logger LOGGER = Logger.getLogger(UserMenuHandler.class); 
	static BookingController bookingController;
	static UserController userController;
	public UserMenuHandler() {
		bookingController = new BookingController();
		userController = new UserController();
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
			if(list.size() <= 0)
			{
				JOptionPane.showMessageDialog(null,"No tickets available to book","Information",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			int index = InputFromConsoleUtil.getIndex(ConsoleMessageConstants.TAKE_INDEX, list.size());
			TransportaionDetails details = list.get(index);
			int people = InputFromConsoleUtil.getInteger(ConsoleMessageConstants.NOOFPEOPLETRAVELLING);
			Set<CoPassenger> coPassengers = new HashSet<>();

			User user = (User) AppSession.session.get("loginUser");

			ticket.setTransportaionDetails(details);
			ticket.setUser(user);
			ticket.setCancelledDate(null);
			ticket.setBookedDate(DateUtil.getDate());
			ticket.setTotal(people+1);
			int bookId = bookingController.book(ticket);

			for(int i = 1;i <= people ; i++){
				System.out.println("Passenger "+i+" details:");
				CoPassenger coPassenger = new CoPassenger();
				coPassenger.setBook_id(bookId);
				coPassenger.setName(InputFromConsoleUtil.getString(ConsoleMessageConstants.VALID_FNAME, true));
				coPassenger.setAge(InputFromConsoleUtil.getInteger(ConsoleMessageConstants.VALID_AGE));
				coPassengers.add(coPassenger);
				coPassenger.setStatus(StatusType.ACTIVE);
			}
			bookingController.bookForCopassengers(coPassengers);
			SSLMailUtil.sendTicketBookingMail(ticket,coPassengers,MailConstants.SUCCESS_BOOK);
			JOptionPane.showMessageDialog(null,"Ticket Successfully booked from "+source+" to "+destination,"Information",JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Ticket booking failed from "+source+" to "+destination,"Information",JOptionPane.ERROR_MESSAGE);
			LOGGER.info("Exception while booking ticket",e);
		}
	}

	public void getAllBookedDetails(String username){
		bookingController.getAllBookedDetails(username);
	}

	/**
	 * Displays all records
	 * 
	 * @param transList
	 */
	public static void displayList(List<TransportaionDetails> transList) {
		System.out.println(
				"****************************************************************************************************************************************************");
		String format = "|%1$-5s|%2$-15s|%3$-20s|%4$-15s|%5$-15s|%6$-20s|%7$-10s|\n";
		System.out.format(format, "SNo", "Source", "Destination","Arrival", "Departure", "Journey time", "Fare");
		System.out.println(
				"****************************************************************************************************************************************************");
		for (int i = 0; i < transList.size(); i++) {

			String source = (transList.get(i).getSource() == null || transList.get(i).getSource().isEmpty()) ? "---"
					: transList.get(i).getSource();
			String destination = (transList.get(i).getDestination() == null || transList.get(i).getDestination().isEmpty())
					? "---" : transList.get(i).getDestination();
			String arrival = (transList.get(i).getArrival() == null || transList.get(i).getArrival().isEmpty())
					? "---" : transList.get(i).getArrival();
			String departure = (transList.get(i).getDeparture() == null) ? "---" : transList.get(i).getDeparture() + "";
			String journeyTime = (transList.get(i).getJourneyTime() == null) ? "---" : transList.get(i).getJourneyTime() + "";
			String fare = (transList.get(i).getPrice() == null) ? "---" : transList.get(i).getPrice() + "";

			System.out.format(format, i + 1, source, destination, arrival, departure,journeyTime,fare);
		}
	}

	/**
	 * Displays all records
	 * 
	 * @param bookList
	 */
	public static void displayBookingHistory(List<BookTicket> bookList) {
		System.out.println(
				"****************************************************************************************************************************************************");
		String format = "|%1$-10s|%2$-15s|%3$-20s|%4$-15s|%5$-15s|%6$-20s|%7$-15s|%8$-10s|%9$-10s|\n";
		System.out.format(format, "SNo", "Source", "Destination","Travel Date", "Arrival", "Departure", "Journey time", "Fare","Status");
		System.out.println(
				"****************************************************************************************************************************************************");
		for (int i = 0; i < bookList.size(); i++) {

			String source = (bookList.get(i).getTransportaionDetails().getSource() == null || bookList.get(i).getTransportaionDetails().getSource().isEmpty()) ? "---"
					: bookList.get(i).getTransportaionDetails().getSource();
			String destination = (bookList.get(i).getTransportaionDetails().getDestination() == null || bookList.get(i).getTransportaionDetails().getDestination().isEmpty())
					? "---" : bookList.get(i).getTransportaionDetails().getDestination();
			String traveldate = (bookList.get(i).getTravelDate() == null || bookList.get(i).getTravelDate().isEmpty())
					? "---" : bookList.get(i).getTravelDate();

			String arrival = (bookList.get(i).getTransportaionDetails().getArrival() == null || bookList.get(i).getTransportaionDetails().getArrival().isEmpty())
					? "---" : bookList.get(i).getTransportaionDetails().getArrival();
			String departure = (bookList.get(i).getTransportaionDetails().getDeparture() == null) ? "---" : bookList.get(i).getTransportaionDetails().getDeparture() + "";
			String journeyTime = (bookList.get(i).getTransportaionDetails().getJourneyTime() == null) ? "---" : bookList.get(i).getTransportaionDetails().getJourneyTime() + "";
			String fare = (bookList.get(i).getTransportaionDetails().getPrice() == null) ? "---" : bookList.get(i).getTransportaionDetails().getPrice() + "";
			StatusType status = bookList.get(i).getStatus();

			System.out.format(format, i + 1, source, destination, traveldate, arrival, departure,journeyTime,fare,status);

			List<CoPassenger> colist = bookingController.coPassengerList(bookList.get(i).getId());
			if(!colist.isEmpty()){
				System.out.println(
						"\t---------------Co Passengers----------------");
				String format1 = "\t|%1$-7s|%2$-15s|%3$-5s|%4$-10s|\n";
				System.out.format(format1, "SNo", "Name", "Age","Status");
				System.out.println(
						"\t--------------------------------------------");
				int j = 1;
				for (CoPassenger coPassenger : colist) {
					System.out.format(format1, j++, coPassenger.getName(), coPassenger.getAge(),coPassenger.getStatus());
					System.out.println();
				}
			}
		}
	}

	public void showBookingHistory(){
		try {
			
			User user = (User)AppSession.session.get("loginUser");
			List<BookTicket> tickets = userController.findByLogin(user.getUserName()).getTickets();
			displayBookingHistory(tickets);
		} catch (Exception e) {
			LOGGER.info("Exception while showing bookinghistory ticket",e);
		}
	}

	public void cancelTicket(){
		BookTicket ticket = null;
		try {
			User user = (User)AppSession.session.get("loginUser");
			//List<BookTicket> tickets = user.getTickets();
			List<BookTicket> cancelTickets = bookingController.getAllBookedDetails(user.getId());
			displayBookingHistory(cancelTickets);
			if(cancelTickets.size() <= 0)
			{
				JOptionPane.showMessageDialog(null,"No tickets available to cancel","Information",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			int index = InputFromConsoleUtil.getIndex(ConsoleMessageConstants.TAKE_INDEX, cancelTickets.size());
			int result = JOptionPane.showConfirmDialog(null,"Do you want to cancel ticket?","Confirmation",JOptionPane.YES_NO_OPTION);
			if(result != JOptionPane.YES_OPTION)
				return;
			ticket = cancelTickets.get(index);
			bookingController.cancelTicket(ticket);
			SSLMailUtil.sendTicketBookingMail(ticket, null, MailConstants.CANCEL_BOOK);
			JOptionPane.showMessageDialog(null,"Ticket Successfully cancelled from "+ticket.getTransportaionDetails().getSource()+" to "+ticket.getTransportaionDetails().getDestination(),"Information",JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Ticket Successfully cancelled from "+ticket.getTransportaionDetails().getSource()+" to "+ticket.getTransportaionDetails().getDestination(),"Information",JOptionPane.ERROR_MESSAGE);
			LOGGER.info("Exception while cancelling ticket",e);
		}
	}

}
