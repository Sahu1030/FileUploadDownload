package com.incture.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.entity.FileObjectStore;
import com.incture.entity.ReadPDF;
import com.incture.repository.PDFRepository;

@Service
public class PDFService {
	
	public PDFRepository pdfRepository;
	
	@Autowired
	public PDFService(PDFRepository pdfRepository) {
		super();
		this.pdfRepository = pdfRepository;
	}
	
	public void save(ReadPDF file) {
		// TODO Auto-generated method stub
		pdfRepository.saveAndFlush(file);
	}
	
	public void saveObject(FileObjectStore file) {
		// TODO Auto-generated method stub
		pdfRepository.saveAndFlush(file);
	}
	
	public ReadPDF searchByTag(String tag) {
		return pdfRepository.searchByTag(tag);
	}

}
