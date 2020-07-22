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
@Table(name="object_store")
public class FileObjectStore {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id") 
	private int id;
		
	@Lob
	@Column(name = "uploaded_file")
    private byte[] file;
	
	@Column(name = "file_name")
    private String fileName;

	public FileObjectStore() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileObjectStore(int id, byte[] file, String fileName) {
		super();
		this.id = id;
		this.file = file;
		this.fileName = fileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "FileObjectStore [id=" + id + ", file=" + file + ", fileName=" + fileName + "]";
	}
	
	
}
