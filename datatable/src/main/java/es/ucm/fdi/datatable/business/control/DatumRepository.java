package es.ucm.fdi.datatable.business.control;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ucm.fdi.datatable.business.entity.Datum;

public interface DatumRepository extends JpaRepository<Datum, Long> {

}
