package es.ucm.fdi.fileupload.business.boundary;

import org.springframework.web.multipart.MultipartFile;

public class NewFileCommand {

	private String name;
	
	private MultipartFile attachment;
	
	public NewFileCommand() {
		this.name = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getAttachment() {
		return attachment;
	}

	public void setAttachment(MultipartFile attachment) {
		this.attachment = attachment;
	}

}
