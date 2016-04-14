package com.avocent.plugins.generator;

public class ImageDownloadException extends RuntimeException{

	private final String projectName;
	private final String imageName;
	private final String message;
	
	public ImageDownloadException(String projectName,
			String imagename, Throwable cause) {
		 super(cause);
		 this.projectName = projectName;
		 this.imageName = imagename;
		 this.message = cause.getMessage();
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public String getImageName() {
		return imageName;
	}

	public String getMessage(){
		return message;
	}
}
