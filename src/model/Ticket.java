package model;

public class Ticket {
	
	private short chairNumber;
	private boolean baggage;
	private float weight;
	private Fly flights;
	private Passenger passengers;
	
	public Ticket( Fly flights, Passenger passengers,short chairNumber) {

		this.chairNumber = chairNumber;
		this.flights = flights;
		this.passengers = passengers;
		this.baggage=false;
	}
	
	public Ticket( Fly flights, Passenger passengers,short chairNumber,float weight) {

		this.chairNumber = chairNumber;
		this.flights = flights;
		this.passengers = passengers;
		this.weight=weight;
		this.baggage=true;
	}

	public short getChairNumber() {
		return chairNumber;
	}

	public void setChairNumber(short chairNumber) {
		this.chairNumber = chairNumber;
	}

	public boolean isBaggage() {
		return baggage;
	}

	public void setBaggage(boolean baggage) {
		this.baggage = baggage;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Fly getFlights() {
		return flights;
	}

	public void setFlights(Fly flights) {
		this.flights = flights;
	}

	public Passenger getPassengers() {
		return passengers;
	}

	public void setPassengers(Passenger passengers) {
		this.passengers = passengers;
	}
	
	
	
	
	
	

}
