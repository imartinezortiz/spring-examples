package es.ucm.fdi.datatable.web;

import java.util.List;

public class DataTablePage<E> {

	private long draw;
	
	private List<E> data;
	
	private long recordsTotal;
	
	public DataTablePage(Integer draw) {
		this.draw = draw;
	}

	public long getDraw() {
		return draw;
	}

	public void setDraw(long draw) {
		this.draw = draw;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	
}
