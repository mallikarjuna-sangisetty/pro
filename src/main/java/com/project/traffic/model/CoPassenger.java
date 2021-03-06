package com.project.traffic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.traffic.enums.EnumUtils.StatusType;

@Entity
@Table(name="coPassenger",catalog="trafficAnalysis")
public class CoPassenger {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "copass_id", unique = true, nullable = false)
	private int id;
	private String name;
	private int age;
	private StatusType status;
	
	private int book_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public StatusType getStatus() {
		return status;
	}
	public void setStatus(StatusType status) {
		this.status = status;
	}
	
}
