package model;

import java.time.LocalDate;

public class Occasional extends Passenger {
	private LocalDate dateLastFly;

	public Occasional(String idPassenger, String firstName, String lastName, String country, LocalDate dateBirthday,LocalDate dateLastFly) {
		super(idPassenger, firstName, lastName, country, dateBirthday);
		
		this.dateLastFly= dateLastFly;
	}

	public LocalDate getDateLastFly() {
		return dateLastFly;
	}

	public void setDateLastFly(LocalDate dateLastFly) {
		this.dateLastFly = dateLastFly;
	}

	@Override
	public double getTicketCost() {
		LocalDate dateNow = LocalDate.now();
		double discount=findPassenger(this.idPassenger).getFlights().getTarget().getValueTicket()*0.1;
		return dateNow.getYear()-findPassenger(this.idPassenger).getFlights().getAirplane().getYear()>10 
				?findPassenger(this.idPassenger).getFlights().getTarget().getValueTicket()+calcOvercrowed()-discount
						:findPassenger(this.idPassenger).getFlights().getTarget().getValueTicket()+calcOvercrowed();
	}

	@Override
	public double addMiles() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
