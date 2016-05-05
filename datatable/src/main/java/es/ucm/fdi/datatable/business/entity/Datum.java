package es.ucm.fdi.datatable.business.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Datum {

	@Id
	@GeneratedValue
	private Long id;
	
	private String field1;
	
	private String field2;
	
	private long field3;
	
	Datum() {
		
	}
	
	public Datum(String field1, String field2, long field3) {
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public long getField3() {
		return field3;
	}

	public void setField3(long field3) {
		this.field3 = field3;
	}

	public Long getId() {
		return id;
	}
	
	
}
