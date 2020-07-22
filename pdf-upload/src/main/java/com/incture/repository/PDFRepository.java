package com.incture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incture.entity.FileObjectStore;
import com.incture.entity.ReadPDF;

@Repository
public interface PDFRepository extends JpaRepository<ReadPDF, Integer> {
	
	public ReadPDF searchByTag(String tag);

	public void saveAndFlush(FileObjectStore file);

}
