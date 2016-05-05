package es.ucm.fdi.datatable.business.boundary;

import java.util.List;

import es.ucm.fdi.datatable.business.entity.Datum;

public class DataPage {

	private List<Datum> data;
	
	private long recordsTotal;
	
	public DataPage() {
	}

	public List<Datum> getData() {
		return data;
	}

	public void setData(List<Datum> data) {
		this.data = data;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	
}
