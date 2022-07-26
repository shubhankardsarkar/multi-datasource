package com.example.multidatasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multidatasource.service.IDesignationService;

@RestController(value = "/api/v1")
public class DesignationController {

	@Autowired
	private IDesignationService iDesignationService; 
	
	@GetMapping(name = "/getDesignation")
	public ResponseEntity<?> getDesignation(){
		
		return ResponseEntity.ok(iDesignationService.getDesignations());
	}
}
