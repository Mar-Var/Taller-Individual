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
		double discount=findFly(this.idPassenger).getFlights().getTarget().getValueTicket()*0.1;
		return findFly(this.idPassenger).getFlights().getAirplane().getYear()>10
				?findFly(this.idPassenger).getFlights().getTarget().getValueTicket()+calcOvercrowed()-discount
						:findFly(this.idPassenger).getFlights().getTarget().getValueTicket()+calcOvercrowed();
	}
	
}
