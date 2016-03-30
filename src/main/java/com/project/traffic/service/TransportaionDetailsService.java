package com.project.traffic.service;

import java.util.List;

import org.jfree.data.general.DefaultPieDataset;

import com.project.traffic.model.TransportaionDetails;

public interface TransportaionDetailsService {

	public boolean feed(List<TransportaionDetails> details);
	public DefaultPieDataset getData();
}
