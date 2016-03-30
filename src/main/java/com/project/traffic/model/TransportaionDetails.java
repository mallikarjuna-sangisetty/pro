
package com.project.traffic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="transportaionDetails",catalog="trafficAnalysis")
public class TransportaionDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "transport_id", unique = true, nullable = false)
    private Integer id;
    private String source;
    private String destination;
    private Float distance;
    private String journeyTime;
    private String arrival;
    private String departure;
    private String provider;
    private Integer seats;
    private Float price;
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="transportaionDetails")
    private List<BookTicket> tickets;
    
	/**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }
    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }
    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }
    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }
    /**
     * @return the distance
     */
    public Float getDistance() {
        return distance;
    }
    /**
     * @param distance the distance to set
     */
    public void setDistance(Float distance) {
        this.distance = distance;
    }
    /**
     * @return the journeyTime
     */
    public String getJourneyTime() {
        return journeyTime;
    }
    /**
     * @param journeyTime the journeyTime to set
     */
    public void setJourneyTime(String journeyTime) {
        this.journeyTime = journeyTime;
    }
    /**
     * @return the arrival
     */
    public String getArrival() {
        return arrival;
    }
    /**
     * @param arrival the arrival to set
     */
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
    /**
     * @return the departure
     */
    public String getDeparture() {
        return departure;
    }
    /**
     * @param departure the departure to set
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }
    /**
     * @return the seats
     */
    public Integer getSeats() {
        return seats;
    }
    /**
     * @param seats the seats to set
     */
    public void setSeats(Integer seats) {
        this.seats = seats;
    }
    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }
    
    public List<BookTicket> getTickets() {
		return tickets;
	}
	public void setTickets(List<BookTicket> tickets) {
		this.tickets = tickets;
	}
    @Override
	public String toString() {
		return "TransportaionDetails [id=" + id + ", source=" + source + ", destination=" + destination + ", distance="
				+ distance + ", journeyTime=" + journeyTime + ", arrival=" + arrival + ", departure=" + departure
				+ ", provider=" + provider + ", seats=" + seats + ", price=" + price + ", tickets=" + tickets + "]";
	}
    /**
     * @return the provider
     */
    public String getProvider() {
        return provider;
    }
    /**
     * @param provider the provider to set
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }
   
    

}
