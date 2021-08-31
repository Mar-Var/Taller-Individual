package model;

public class Target {
	private String idTarget;
	private String nameTarget;
	private double valueTicket;
	private float distance;
	public Target(String idTarget, String nameTarget, double valueTicket, float distance) {
		super();
		this.idTarget = idTarget;
		this.nameTarget = nameTarget;
		this.valueTicket = valueTicket;
		this.distance = distance;
	}
	public String getIdTarget() {
		return idTarget;
	}
	public void setIdTarget(String idTarget) {
		this.idTarget = idTarget;
	}
	public String getNameTarget() {
		return nameTarget;
	}
	public void setNameTarget(String nameTarget) {
		this.nameTarget = nameTarget;
	}
	public double getValueTicket() {
		return valueTicket;
	}
	public void setValueTicket(double valueTicket) {
		this.valueTicket = valueTicket;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	@Override
	public String toString() {
		return "Target [idTarget=" + idTarget + ", nameTarget=" + nameTarget + ", valueTicket=" + valueTicket
				+ ", distance=" + distance + "]";
	}
	
	
	

}
