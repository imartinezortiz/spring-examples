package es.ucm.fdi.datatable.business.boundary;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.ucm.fdi.datatable.business.control.DatumRepository;
import es.ucm.fdi.datatable.business.entity.Datum;

@Service
@Transactional
public class DataManager {

	private DatumRepository data;

	@Autowired
	public DataManager(DatumRepository data) {
		this.data = data;
	}
	
	public DataPage getData(int start, int length) {
		Page<Datum> page = data.findAll(new PageRequest(start, length));
		DataPage dataPage = new DataPage();
		dataPage.setRecordsTotal(page.getTotalElements());
		dataPage.setData(page.getContent());
		return dataPage;
	}
	
}
