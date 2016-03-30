package com.project.traffic.repositoryImpl;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.project.traffic.model.TransportaionDetails;
import com.project.traffic.uiImpl.UserMenuHandler;

public class TransportaionDetailsRepositoryImplTest{
	static TransportaionDetailsRepositoryImpl impl;
	static BookingRepositoryImpl bookingImpl;
	@BeforeClass
	public static void setup(){
		impl = new TransportaionDetailsRepositoryImpl();
		bookingImpl = new BookingRepositoryImpl();
	}
	@Test
	public void getData(){
		impl.getData();

	}

	@Test
	public void getAvailableTransport(){
		List<TransportaionDetails> list ;
		list = bookingImpl.getAvailableTransport("", "");
		
		UserMenuHandler.displayList(list);
	}

}
