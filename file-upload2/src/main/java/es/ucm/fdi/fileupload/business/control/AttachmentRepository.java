package es.ucm.fdi.fileupload.business.control;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ucm.fdi.fileupload.business.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}