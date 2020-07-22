package com.incture.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.incture.demo.model.EmployeeEntity;
import com.incture.demo.model.FileEntity;

@Repository
public interface EmployeeRepository 
			extends CrudRepository<EmployeeEntity, Long> {
	
	public void save(FileEntity file);
//	public FileEntity findbytag(String tagname);
	
	

}
