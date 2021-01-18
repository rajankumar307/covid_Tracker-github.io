package com.tracker.covidtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.covidtracker.model.LocationStats;
import com.tracker.covidtracker.repository.InitialRecord;


@RestController
public class CovidTrackerController {
	
	@Autowired
	private InitialRecord initialRecord;
	
	public CovidTrackerController(InitialRecord initialRecord) {
		this.initialRecord = initialRecord;
	}
	
	@GetMapping("/home")
	public String getStat(Model model) {
		 List<LocationStats> allStats = initialRecord.getStat();
		
		 int totalReportedCases = allStats.stream().mapToInt(stat ->stat.getLatestTotalCases()).sum(); 
		 int totalNewCases =allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		  model.addAttribute("locationStats", initialRecord.getStat());
		  model.addAttribute("totalReportedCases", totalReportedCases);
		  model.addAttribute("totalNewCases", totalNewCases);
		  
		  return "home";
		
	}

}
