package com.incture.demo.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;
/**
 * @author Sarat.Chandra
 *
 */
@Entity
@Table(name="file_Store")
public class FileEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id") 
	private int id;
	
	@Column(name = "tag", columnDefinition = "text")
    private String tag;
	
	@Column(name = "filename", columnDefinition = "text")
    private String fileName;
	
	@Lob
	@Column(name = "file")
    private byte[] file;

	public FileEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileEntity(int id, String tag, String fileName, byte[] file) {
		super();
		this.id = id;
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
		return "FileEntity [id=" + id + ", tag=" + tag + ", fileName=" + fileName + ", file=" + Arrays.toString(file)
				+ "]";
	}
	
	
	

}
