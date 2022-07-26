package com.example.multidatasource.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.multidatasource.entity.DesignationEntity;
import com.example.multidatasource.ras.DesignationRas;
import com.example.multidatasource.service.IDesignationService;

@Service
public class DesignationServiceImpl implements IDesignationService {

	@Autowired
	DesignationRas designationRas;
	
	@Override
	public Optional<List<DesignationEntity>> getDesignations() {
		List<DesignationEntity> list = designationRas.findAll();
		return Optional.ofNullable(list);
	}

}
