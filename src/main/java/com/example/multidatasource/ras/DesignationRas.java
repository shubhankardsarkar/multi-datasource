package com.example.multidatasource.ras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.multidatasource.entity.DesignationEntity;

@Repository
public interface DesignationRas extends JpaRepository<DesignationEntity, Integer> {
	
//	@Query(nativeQuery = true, value = "select d from designation d")
//	public ResponseEntity<DesignationEntity> getDesignations();
}
