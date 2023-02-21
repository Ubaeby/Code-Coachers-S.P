package com.codecoachers.project.message;

public class ResponseFile {

	private String message;
	private String url;
	private String type;
	private long size;
	
	public ResponseFile(String message, String url, String type, long size) {
		this.message = message;
		this.url = url;
		this.type = type;
		this.size = size;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
	
}
