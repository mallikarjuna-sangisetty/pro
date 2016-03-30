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

import com.project.traffic.enums.EnumUtils.GenderEnum;
@Entity
@Table(name="user",catalog="trafficanalysis")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id", unique = true, nullable = false)
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private GenderEnum gender;
    private String email;
    private String phone;
    private int userType;
    private String createdDate;
    private String modifiedDate;
    private int age;
    
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
    private List<BookTicket> tickets;
    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }
    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    private int status;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the gender
     */
    public GenderEnum getGender() {
        return gender;
    }
    /**
     * @param gender the gender to set
     */
    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * @return the userType
     */
    public int getUserType() {
        return userType;
    }
    /**
     * @param userType the userType to set
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }
    /**
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }
    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    /**
     * @return the modifiedDate
     */
    public String getModifiedDate() {
        return modifiedDate;
    }
    /**
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public List<BookTicket> getTickets() {
		return tickets;
	}
	public void setTickets(List<BookTicket> tickets) {
		this.tickets = tickets;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", gender=" + gender + ", email=" + email + ", phone=" + phone
				+ ", userType=" + userType + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", age=" + age + ", tickets=" + tickets + ", status=" + status + "]";
	}







}
