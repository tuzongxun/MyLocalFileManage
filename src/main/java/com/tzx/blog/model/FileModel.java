package com.tzx.blog.model;

/**
 * 自定义文件类
 * 
 * @author tzx
 * @date 2018年3月23日 下午3:03:22
 *
 */
public class FileModel {
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件保存位置
	 */
	private String filePath;
	/**
	 * 文件内容
	 */
	private String fileContent;
	/**
	 * 文件类别
	 */
	private String fileType;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public String toString() {
		return "FileModel [fileName=" + fileName + ", filePath=" + filePath
				+ ", fileContent=" + fileContent + ", fileType=" + fileType
				+ "]";
	}

}
