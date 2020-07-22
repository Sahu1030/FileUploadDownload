package com.incture.demo.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.incture.demo.exception.RecordNotFoundException;
import com.incture.demo.model.EmployeeEntity;
import com.incture.demo.model.FileEntity;
import com.incture.demo.service.EmployeeService;
import com.microsoft.schemas.office.visio.x2012.main.CellType;

@Controller
@RequestMapping("/")
public class EmployeeMvcController 
{
	@Autowired
	EmployeeService service;
	
	FileEntity fileinfo;

	@RequestMapping
	public String getAllEmployees(Model model) 
	{
		List<EmployeeEntity> list = service.getAllEmployees();

		model.addAttribute("employees", list);
		return "list-employees";
	}

	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		if (id.isPresent()) {
			EmployeeEntity entity = service.getEmployeeById(id.get());
			model.addAttribute("employee", entity);
		} else {
			model.addAttribute("employee", new EmployeeEntity());
		}
		return "add-edit-employee";
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException 
	{
		service.deleteEmployeeById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	public String createOrUpdateEmployee(EmployeeEntity employee) 
	{
		service.createOrUpdateEmployee(employee);
		return "redirect:/";
	}
	
	@RequestMapping(path = "/uploadFile")
	public String uploadFile(Model model) {
		model.addAttribute("uploadFile", new FileEntity());
		return "uploadFile";
	}
	@RequestMapping(path = "/searchFile")
	public String searchFile(Model model) {
		model.addAttribute("data", fileinfo);
		return "searchFile";
	}
	
	/*@RequestMapping(path = "/uploadedFile", method = RequestMethod.POST)
	public String uploadedFile(FileEntity employee) 
	{
		
//		service.createOrUpdateEmployee(employee);
		System.out.println(employee.getFileName().getOriginalFilename());
		System.out.println(employee.getTag());
		return "redirect:/";
	}*/
	
	@RequestMapping(path = "/uploadedFile", method = RequestMethod.POST)
	public String uploadedFile(@RequestParam("tag") String tagName,@RequestParam("file") MultipartFile file) throws IOException 
	{
		
//		service.createOrUpdateEmployee(employee);
		String tag=tagName;
		String fileName=file.getOriginalFilename();
		byte[] b =file.getBytes();
		System.out.println(b);
		FileEntity fileinfo=new FileEntity();
		fileinfo.setTag(tag);
		fileinfo.setFileName(fileName);
		fileinfo.setFile(b);
		service.saveFile(fileinfo);
		return "redirect:/uploadFile";
	}
	
	@RequestMapping(path = "/saearchUploadedFile" , method = RequestMethod.POST)
	public String uploadedFile(@RequestParam("tagName") String tagName,Model model) throws IOException 
	{
		
		System.out.println(""+tagName);

		
//		int a=1;
//		Optional<FileEntity> fileinfo1=service.searchbyid(tagName);
//		fileinfo=fileinfo1.get();
		fileinfo=service.searchbytag(tagName);
		System.out.println(fileinfo+""+tagName);
		model.addAttribute("data", fileinfo);
		return "redirect:/searchFile";
	}
	
	@GetMapping("/downloadById/{id}")
	public ResponseEntity<byte[]> downloadById(@PathVariable int id) {
		FileEntity seacedData = null;
		Optional<FileEntity> fileinfo1=service.searchbyid(id);
		seacedData = fileinfo1.get();
		byte[] file=seacedData.getFile();
		String filename=seacedData.getFileName();
		HttpHeaders header=new HttpHeaders();
		header.setContentDispositionFormData(filename, filename);
		header.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//		ResponseEntity<byte[]> response= new ResponseEntity<byte[]>(file, HttpStatus.OK);
		ResponseEntity<byte[]> response= new ResponseEntity<byte[]>(file,header,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/download")
	public ResponseEntity<?> downloadData(
			HttpServletResponse response) {
		try {
			String s="sarat";
			List<EmployeeEntity> list=service.getAllEmployees();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet();
			int rowIdx = 0;
			for (EmployeeEntity EmployeeEntity : list) {
				XSSFRow row = sheet.createRow(++rowIdx);
				row.createCell(1).setCellValue(EmployeeEntity.getFirstName());
				row.createCell(2).setCellValue(EmployeeEntity.getLastName());
				row.createCell(3).setCellValue(EmployeeEntity.getEmail());
			}
			workbook.write(out);
			
			ByteArrayInputStream in= new ByteArrayInputStream(out.toByteArray());
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Content-Disposition", "attachment; filename=parts.xlsx");
//			headers.add("Content-Type", "application/octet-stream");
//			 InputStream is = new ByteArrayInputStream(in);
			response.setHeader("Content-Disposition", "attachment; filename=parts.xls");
			response.setContentType("application/octet-stream; charset=utf-8");
//			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			IOUtils.copy(in, response.getOutputStream());
			return ResponseEntity.ok().build();
		} catch (IOException e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
