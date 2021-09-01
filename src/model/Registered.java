package model;

import java.time.LocalDate;
import java.time.Period;

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
		LocalDate dateNow = LocalDate.now();
		double discountOld=findPassenger(this.idPassenger).getFlights().getTarget().getValueTicket()*0.1;
		double discountRegistered= findPassenger(this.idPassenger).getFlights().getTarget().getValueTicket()*(discount/100);
		return dateNow.getYear()-findPassenger(this.idPassenger).getFlights().getAirplane().getYear()>10 
				?findPassenger(this.idPassenger)
						.getFlights()
						.getTarget().
						getValueTicket()+calcOvercrowed()-discountOld-discountRegistered+calcOvercrowed()
						:findPassenger(this.idPassenger).getFlights().getTarget().getValueTicket()+calcOvercrowed()-discountRegistered;
	}

	@Override
	public double addMiles(float milles) {
		this.milles+= milles;
		return milles;
	}

	


	
	
	
	

}
