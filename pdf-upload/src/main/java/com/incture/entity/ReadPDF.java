package com.incture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;

/**
 * @author Sarat.Chandra
 *
 */
@Entity
@Table(name="pdfdata")
public class ReadPDF {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id") 
	private int id;
		
	@Column(name = "file_data", columnDefinition = "text")
    private String data;
	
	@Column(name = "tag_name", columnDefinition = "text")
    private String tag;
	
	@Column(name = "file_name", columnDefinition = "text")
    private String fileName;
	
	@Lob
	@Column(name = "uploaded_file")
    private byte[] file;
		
	
	public ReadPDF() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ReadPDF(int id, String data, String tag, String fileName, byte[] file) {
		super();
		this.id = id;
		this.data = data;
		this.tag = tag;
		this.fileName = fileName;
		this.file = file;
	}





	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	

	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public byte[] getFile() {
		return file;
	}



	public void setFile(byte[] file) {
		this.file = file;
	}



	@Override
	public String toString() {
		return "ReadPDF [id=" + id + ", fileData=" + data + "]";
	}
	
	
	

}
