package com.incture.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.incture.demo.model.FileEntity;

public interface FileEntityRepo extends JpaRepository<FileEntity, Integer> {
	
	public FileEntity findByTag(String tagname);
	
	

}