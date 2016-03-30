package com.project.traffic.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.general.DefaultPieDataset;

import com.project.traffic.model.TransportaionDetails;
import com.project.traffic.service.TransportaionDetailsService;
import com.project.traffic.serviceImpl.TransportaionDetailsServiceImpl;
import com.project.traffic.session.AppSession;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TransportaionDetailsController {
	
	private TransportaionDetailsService detailsService;
    
	public TransportaionDetailsController(){
		detailsService = new TransportaionDetailsServiceImpl();
	}
    public List<TransportaionDetails> prepareForFeed(String fileName){
        try {
            List<TransportaionDetails> list = new ArrayList<>();
            Workbook wrk1 =  Workbook.getWorkbook(new File(fileName));
             
            Sheet sheet1 = wrk1.getSheet(0);
             
            int rows = sheet1.getRows();
            for(int i=1;i<rows;i++){
                TransportaionDetails details = new TransportaionDetails();
                    details.setSource(sheet1.getCell(0,i).getContents());
                    details.setDestination(sheet1.getCell(1,i).getContents());
                    details.setDistance(Float.parseFloat(sheet1.getCell(2,i).getContents()));
                    details.setJourneyTime(sheet1.getCell(3,i).getContents());
                    details.setArrival(sheet1.getCell(4,i).getContents());
                    details.setDeparture(sheet1.getCell(5,i).getContents());
                    details.setProvider(sheet1.getCell(6,i).getContents());
                    details.setSeats(Integer.parseInt(sheet1.getCell(7,i).getContents()));
                    details.setPrice(Float.parseFloat(sheet1.getCell(8,i).getContents()));
                    list.add(details);
            }
           
            return list;
             
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    public boolean feedData(String fileName){
    	List<TransportaionDetails> details = prepareForFeed(fileName);
    	AppSession.session.put("count",details.size());
    	if(details == null)
    		return false;
    	return detailsService.feed(details);
    	
    }
    
    public DefaultPieDataset generateReportByAirlinesVsFlights(){
    	return detailsService.getData();
    }
    
}
