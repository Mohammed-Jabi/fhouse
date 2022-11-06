package com.jabir.fhouse.module;

public class Plantation {

	private int id;
	private String plantation;
	private String area;
	private String pdate;
	private String ydate;

	


	
	public Plantation(String plantation, String area_no, String pdate, String ydate) {
		super();
		this.plantation = plantation;
		this.area = area_no;
		this.pdate = pdate;
		this.ydate = ydate;
	}



	public Plantation() {
		
	}


	public Plantation(int id, String plantation, String area, String pdate, String ydate) {
		super();
		this.id = id;
		this.plantation = plantation;
		this.area = area;
		this.pdate = pdate;
		this.ydate = ydate;
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlantation() {
		return plantation;
	}

	public void setPlantation(String plantation) {
		this.plantation = plantation;
	}

	public String getArea() {
		return area;
	}

	public void setArea_no(String area) {
		this.area = area;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public String getYdate() {
		return ydate;
	}

	public void setYdate(String ydate) {
		this.ydate = ydate;
	}

}
