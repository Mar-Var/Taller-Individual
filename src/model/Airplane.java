package model;

public class Airplane {
	
	private String idAirplane;
	private short capacity;
	private short year;
	private ETypeAirplane typeAirplane;
	
	public Airplane(String idAirplane, short capacity, short year, ETypeAirplane typeAirplane) {
		super();
		this.idAirplane = idAirplane;
		this.capacity = capacity;
		this.year = year;
		this.typeAirplane = typeAirplane;
	}
	public String getIdAirplane() {
		return idAirplane;
	}
	public void setIdAirplane(String idAirplane) {
		this.idAirplane = idAirplane;
	}
	public short getCapacity() {
		return capacity;
	}
	public void setCapacity(short capacity) {
		this.capacity = capacity;
	}
	public short getYear() {
		return year;
	}
	public void setYear(short year) {
		this.year = year;
	}
	public ETypeAirplane getTypeAirplane() {
		return typeAirplane;
	}
	public void setTypeAirplane(ETypeAirplane typeAirplane) {
		this.typeAirplane = typeAirplane;
	}
	@Override
	public String toString() {
		return "Airplane [idAirplane=" + idAirplane + ", capacity=" + capacity + ", year=" + year + ", typeAirplane="
				+ typeAirplane + "]";
	}
	
	
	
	

}
