package model;

import java.time.LocalDate;

public class Registered extends Passenger {
	private LocalDate dateRegistered;
	private double milles ;
	private static float discount =15;

	public Registered(String idPassenger, String firstName, String lastName, String country, LocalDate dateBirthday,LocalDate dateRegistered) {
		super(idPassenger, firstName, lastName, country, dateBirthday);
		this.dateRegistered= dateRegistered;
	}

	public LocalDate getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(LocalDate dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public double getMilles() {
		return milles;
	}

	public void setMilles(double milles) {
		this.milles = milles;
	}

	@Override
	public double getTicketCost() {	
		double discnt=findFly(this.idPassenger).getFlights().getTarget().getValueTicket()*0.1;
		return findFly(this.idPassenger).getFlights().getAirplane().getYear()>10 
				?findFly(this.idPassenger).getFlights().getTarget().getValueTicket()+calcOvercrowed()-discnt-discount
						:findFly(this.idPassenger).getFlights().getTarget().getValueTicket()+calcOvercrowed();
	}
	
	


	
	
	
	

}
