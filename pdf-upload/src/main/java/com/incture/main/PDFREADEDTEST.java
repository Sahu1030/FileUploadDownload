package com.incture.main;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PDFREADEDTEST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// File convFile = new File(file.getOriginalFilename());
		String contentOfPage = "";
		 String pdfFilePath ="C:/Users/Sarat.Chandra/Desktop/PROJECTHELPFOLDER/Test-pdf.pdf";
//		 String pdfFilePath = "C:/Users/Sarat.Chandra/Desktop/PROJECTHELPFOLDER/Lookup.xlsx";
//		 String pdfFilePath = "C:/Users/Sarat.Chandra/Desktop/PROJECTHELPFOLDER/Lookup.xlsx";
		String extension = "";
		int i = pdfFilePath.lastIndexOf('.');
		if (i >= 0) {
			extension = pdfFilePath.substring(i + 1);
		}
		System.out.println(extension);
		System.out.println();
		if (extension.equals("pdf")) {
			System.out.println("PDF content Reader!!");
			try {
				File fileToBeRead = new File(pdfFilePath);
				FileInputStream myInput = new FileInputStream(fileToBeRead);
				System.out.println(fileToBeRead.getAbsolutePath());
				// PDFReader
				PdfReader reader = new PdfReader(myInput);
				// get the number of pages in PDF
				int noOfPages = reader.getNumberOfPages();
				System.out.println("Extracted content of PDF---- ");
				for (int ii = 1; ii <= noOfPages; ii++) {
					// Extract content of each page
					contentOfPage = PdfTextExtractor.getTextFromPage(reader, ii);
					System.out.println(contentOfPage);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		 
		}
		if (extension.equals("xlsx")) {
			System.out.println("XLS content Reader!!");
			try {
				File fileToBeRead = new File(pdfFilePath);
				FileInputStream myInput = new FileInputStream(fileToBeRead);
	   	    	/*POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
	            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);*/
				Workbook workbook = new XSSFWorkbook(myInput);
	            Sheet mySheet = (Sheet) workbook.getSheetAt(0);
	           Iterator rowIter = mySheet.rowIterator(); 
	           while(rowIter.hasNext()){
	        	  Row myRow =  (Row) rowIter.next();
	        	  Iterator cellIter = myRow.cellIterator();
	        	  String cellData = "";
	        	  while(cellIter.hasNext()){
	        		  Cell myCell =  (Cell) cellIter.next();
	        		  cellData=cellData.concat(myCell.getStringCellValue()+  " - ") ;
	        	  }
	        	  contentOfPage = cellData;
	        	  System.out.println(contentOfPage);
	          }

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		 
		}
		if (extension.equals("txt")) {
			System.out.println("Word content Reader!!");
			File fileToBeRead = new File(pdfFilePath);
			String line = null;
			FileReader fileReader;
			try {
				fileReader = new FileReader(fileToBeRead);
				// Always wrap FileReader in BufferedReader.
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				while ((line = bufferedReader.readLine()) != null) {
					contentOfPage = line;
					System.out.println(contentOfPage);
				}
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else {
			System.out.println("other file." + extension);

	}
	}

}
