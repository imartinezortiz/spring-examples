package es.ucm.fdi.storage.business.control;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ucm.fdi.storage.business.entity.StorageObject;
import es.ucm.fdi.storage.business.entity.StorageObjectId;

public interface StorageObjectRepository extends JpaRepository<StorageObject, StorageObjectId> {

}
