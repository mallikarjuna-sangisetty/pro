package com.project.traffic.serviceImpl;

import java.util.List;

import org.jfree.data.general.DefaultPieDataset;

import com.project.traffic.model.TransportaionDetails;
import com.project.traffic.repository.TransportaionDetailsRepository;
import com.project.traffic.repositoryImpl.TransportaionDetailsRepositoryImpl;
import com.project.traffic.service.TransportaionDetailsService;

public class TransportaionDetailsServiceImpl implements TransportaionDetailsService {

	TransportaionDetailsRepository repository;
	
	public TransportaionDetailsServiceImpl() {
		repository = new TransportaionDetailsRepositoryImpl();
	}
	@Override
	public boolean feed(List<TransportaionDetails> details) {
		return repository.feed(details);
	}
	@Override
	public DefaultPieDataset getData() {
		return repository.getData();
	}

}
