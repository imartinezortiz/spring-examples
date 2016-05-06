package es.ucm.fdi.fileupload.business.boundary;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.ucm.fdi.fileupload.business.control.AttachmentRepository;
import es.ucm.fdi.fileupload.business.entity.Attachment;
import es.ucm.fdi.storage.business.boundary.StorageManager;

@Service
public class AttachmentManager {

	private StorageManager storageManager;

	private AttachmentRepository attachments;
	
	private String bucket;

	@Autowired
	public AttachmentManager(StorageManager storageManager, AttachmentRepository attachments, @Value("#{examplePrefs[bucket]}") String bucket) {
		this.storageManager = storageManager;
		this.attachments = attachments;
		this.bucket = bucket;
	}

	public Attachment addAttachment(NewFileCommand command) throws IOException {

		Attachment archivo = attachments.save(new Attachment(command.getDescription()));
		
		MultipartFile attachment = command.getAttachment();
		if (attachment != null && !attachment.isEmpty()) {
			String key = getStorageKey(archivo.getId());
			String mimeType = attachment.getContentType();
			storageManager.putObject(bucket, key, mimeType, attachment.getInputStream());
			archivo.setAttachmentUrl(storageManager.getUrl(bucket, key));
			attachments.save(archivo);
		}
		return archivo;
	}
	
	private String getStorageKey(Long id) {
		return "attachment/"+Long.toString(id);
	}

	public Collection<Attachment> getAttachments() {
		return attachments.findAll();
	}

}