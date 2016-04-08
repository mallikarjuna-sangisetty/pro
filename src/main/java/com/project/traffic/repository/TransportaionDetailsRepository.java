package com.project.traffic.repository;

import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.project.traffic.model.TransportaionDetails;

public interface TransportaionDetailsRepository {
	public boolean feed(List<TransportaionDetails> details);
	public DefaultPieDataset getData(String query,int top);
	public DefaultCategoryDataset getDataBarChart();
	public DefaultCategoryDataset getDataForLineChart(String query,int top);
}
