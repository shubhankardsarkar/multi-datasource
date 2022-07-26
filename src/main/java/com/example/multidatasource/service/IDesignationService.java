package com.example.multidatasource.service;

import java.util.List;
import java.util.Optional;

import com.example.multidatasource.entity.DesignationEntity;

public interface IDesignationService {

	public Optional<List<DesignationEntity>> getDesignations();
}
