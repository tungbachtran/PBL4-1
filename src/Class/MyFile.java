package Class;

import java.io.InputStream;

public class MyFile {
	
	private int id;
	private String fileName;
	private InputStream contentfile; //byte[]
	private String fileExtension;
	public MyFile(int id, String fileName, InputStream contentfile, String fileExtension) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.contentfile = contentfile;
		this.fileExtension = fileExtension;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public InputStream getContentfile() {
		return contentfile;
	}
	public void setContentfile(InputStream contentfile) {
		this.contentfile = contentfile;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	

}

