package com.incture.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Iterator;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.incture.entity.FileObjectStore;
import com.incture.entity.ReadPDF;
import com.incture.excption.TagException;
import com.incture.services.PDFService;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

@RestController
public class PDFController {

	public PDFService pdfService;

	private static String UPLOADED_FOLDER = "F://temp//";

	ReadPDF readPDF = new ReadPDF();
	FileObjectStore objectStore = new FileObjectStore();

	@Autowired
	public PDFController(PDFService pdfService) {
		super();
		this.pdfService = pdfService;
	}

	// @GetMapping("/")
	// public String guest() {
	// return "Welcome to Spring Boot With DB Connection..";
	// }

	@PostMapping("/uploadedFile")
	public String uploadPDF(@RequestParam("file") MultipartFile uploadfile, @RequestParam("tagname") String tagname)
			throws IOException, SerialException, SQLException {

		// ================================
		byte[] bytes = uploadfile.getBytes();
		// SerialBlob image = null;
		// image = new SerialBlob(bytes);
		Path path = Paths.get(UPLOADED_FOLDER + uploadfile.getOriginalFilename());
		Files.write(path, bytes);
		File fileToBeRead = new File(UPLOADED_FOLDER + uploadfile.getOriginalFilename());
		System.out.println(uploadfile.getOriginalFilename() + "    multi file" + fileToBeRead);
		String contentOfPage = "";
		String tag = tagname;
		// String pdfFilePath =
		// "C:/Users/Sarat.Chandra/Desktop/PROJECTHELPFOLDER/Test-pdf.pdf";
		// File filePath = new File(pdfFilePath);
		// int length =(int) ((MultipartFile) uploadfile).length();
		// byte[] image=new byte[length];
		String filename = fileToBeRead.getName();
		System.out.println(fileToBeRead.getAbsolutePath());
		String extension = "";
		int i = filename.lastIndexOf('.');
		if (i >= 0) {
			extension = filename.substring(i + 1);
		}
		System.out.println(extension);
		System.out.println();
		if (extension.equals("pdf")) {
			try {
				// File fileToBeRead = new File(pdfFilePath);
				FileInputStream myInput = new FileInputStream(fileToBeRead);
				// PDFReader
				PdfReader reader = new PdfReader(myInput);
				// get the number of pages in PDF
				int noOfPages = reader.getNumberOfPages();
				System.out.println("Extracted content of PDF---- ");
				for (int j = 1; j <= noOfPages; j++) {
					// Extract content of each page
					contentOfPage = PdfTextExtractor.getTextFromPage(reader, j);
					System.out.println(contentOfPage);
				}
				objectStore.setFileName(filename);
				objectStore.setFile(bytes);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (extension.equals("doc")) {
			System.out.println("Word content Reader!!");
			try {

				// File fileToBeRead = new File(pdfFilePath);
				FileInputStream fis = new FileInputStream(fileToBeRead);
				HWPFDocument document = new HWPFDocument(fis);
				WordExtractor extractor = new WordExtractor(document);
				String[] fileData = extractor.getParagraphText();
				for (int ij = 0; ij < fileData.length; ij++) {
					if (fileData != null)
						contentOfPage = contentOfPage.concat(fileData[ij]);
					System.out.println(contentOfPage);
				}
			} catch (Exception e) {
				System.out.println("We had an error while reading the Word Doc");
			}
		}

		if (extension.equals("txt")) {
			System.out.println("Word content Reader!!");
			// File fileToBeRead = new File(pdfFilePath);
			String line = null;
			FileReader fileReader;
			try {
				fileReader = new FileReader(fileToBeRead);
				// Always wrap FileReader in BufferedReader.
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				while ((line = bufferedReader.readLine()) != null) {
					contentOfPage = contentOfPage.concat(line);
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
		if (extension.equals("xlsx")) {
			System.out.println("XLS content Reader!!");
			try {
				// File fileToBeRead = new File(pdfFilePath);
				FileInputStream myInput = new FileInputStream(fileToBeRead);
				/*
				 * POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
				 * HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
				 */
				Workbook workbook = new XSSFWorkbook(myInput);
				Sheet mySheet = (Sheet) workbook.getSheetAt(0);
				Iterator rowIter = mySheet.rowIterator();
				while (rowIter.hasNext()) {
					Row myRow = (Row) rowIter.next();
					Iterator cellIter = myRow.cellIterator();
					String cellData = "";
					while (cellIter.hasNext()) {
						Cell myCell = (Cell) cellIter.next();
						cellData = cellData.concat(myCell.getStringCellValue() + " - ");
					}
					contentOfPage = contentOfPage.concat(cellData);
					System.out.println(contentOfPage);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// readPDF.setId(0);
		readPDF.setData(contentOfPage);
		readPDF.setTag(tag);
		readPDF.setFileName(filename);
		readPDF.setFile(bytes);

		pdfService.save(readPDF);
		pdfService.saveObject(objectStore);
		return "Data saved Successfully!!";
		// return "Welcome to Spring Boot With DB Connection..";
	}

	@GetMapping("/searchByTag/{tag}")
	public ReadPDF searchByTag(@PathVariable String tag) {
		ReadPDF seacedData = null;
			seacedData = pdfService.searchByTag(tag);
			
		if(seacedData == null)
		{
			throw new TagException("Tag is not Found with this name"+tag.toUpperCase());
		}
		

		return seacedData;
	}

	@GetMapping("/downloadById/{tagName}")
	public ResponseEntity<byte[]> downloadById(@PathVariable String tagName) {
		ReadPDF seacedData = null;

		seacedData = pdfService.searchByTag(tagName);
		byte[] file = seacedData.getFile();
		String filename = seacedData.getFileName();
		HttpHeaders header = new HttpHeaders();
		header.setContentDispositionFormData(filename, filename);
		header.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		// ResponseEntity<byte[]> response= new ResponseEntity<byte[]>(file,
		// HttpStatus.OK);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(file, header, HttpStatus.OK);
		return response;
	}

}
