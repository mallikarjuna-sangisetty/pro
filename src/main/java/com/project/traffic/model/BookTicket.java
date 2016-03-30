package com.project.traffic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.traffic.enums.EnumUtils.StatusType;

@Entity
@Table(name="bookTicket",catalog="trafficAnalysis")
public class BookTicket {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "book_id", unique = true, nullable = false)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transport_id", nullable = false)
	private TransportaionDetails transportaionDetails;

	private String travelDate;
	private StatusType status;
	
	private String bookedDate;
	private String cancelledDate;
	
	public String getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}
	public String getCancelledDate() {
		return cancelledDate;
	}
	public void setCancelledDate(String cancelledDate) {
		this.cancelledDate = cancelledDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TransportaionDetails getTransportaionDetails() {
		return transportaionDetails;
	}
	public void setTransportaionDetails(TransportaionDetails transportaionDetails) {
		this.transportaionDetails = transportaionDetails;
	}
	@Override
	public String toString() {
		return "BookTicket [id=" + id + ", user=" + user + ", transportaionDetails=" + transportaionDetails
				+ ", bookedDate=" + bookedDate + ", cancelledDate=" + cancelledDate
				+ "]";
	}
	public StatusType getStatus() {
		return status;
	}
	public void setStatus(StatusType active) {
		this.status = active;
	}
	public String getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}
	
	
}
