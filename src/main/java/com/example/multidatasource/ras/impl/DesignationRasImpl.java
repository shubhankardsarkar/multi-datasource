package com.example.multidatasource.ras.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.multidatasource.entity.DesignationEntity;
import com.example.multidatasource.ras.DesignationRas;

@Component
public class DesignationRasImpl {

	
	@PersistenceContext 
	EntityManager entityManager;
	
	
	private DesignationRas designationRas;
	
	public List<DesignationEntity> getDesignations() {
		return designationRas.findAll();
	}
}
