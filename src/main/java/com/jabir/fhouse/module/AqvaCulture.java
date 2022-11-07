package com.jabir.fhouse.module;

public class AqvaCulture {

	
	private int id;
	private String species;
	private String area;
	private String startdate;
	private String eydate;
	
	public AqvaCulture(String species, String area, String startdate, String eydate) {
		super();
		this.species = species;
		this.area = area;
		this.startdate = startdate;
		this.eydate = eydate;
	}
	public AqvaCulture(int id, String species, String area, String startdate, String eydate) {
		super();
		this.id = id;
		this.species = species;
		this.area = area;
		this.startdate = startdate;
		this.eydate = eydate;
	}
	public AqvaCulture() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEydate() {
		return eydate;
	}
	public void setEydate(String eydate) {
		this.eydate = eydate;
	}
}
