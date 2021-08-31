package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public abstract class Passenger {
	
	protected String idPassenger;
	protected String firstName;
	protected String lastName;
	protected String country;
	protected LocalDate dateBirthday;
	protected ArrayList<Ticket> tickets;
	
	public Passenger(String idPassenger, String firstName, String lastName, String country, LocalDate dateBirthday) {
		this.idPassenger = idPassenger;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.dateBirthday = dateBirthday;
		tickets= new ArrayList<>();
	}
	
	public byte getAge() {
		LocalDate dateNow = LocalDate.now();
        Period period = Period.between(dateBirthday,dateNow);

        return (byte) period.getYears();
	}
	public Ticket findFly(String id) {
		return tickets.stream().filter((flightAux) -> flightAux.getFlights().getNumber().equals(id)).findFirst().map((flightAux -> flightAux) ).orElse(null);
	}
	public Ticket findPassenger(String idPassenger) {
		return tickets.stream().filter((passengerAux) -> passengerAux.getPassengers().getIdPassenger().equals(idPassenger)).findFirst().map((passengerAux -> passengerAux) ).orElse(null);
	}

	public boolean addToFly(Fly fly,short seat){
		if (findFly(fly.getNumber())==null) {
			tickets.add(new Ticket(fly,this,seat));
			if(this.getClass()==Registered.class) {
				this.addMiles();
			}
			return true;
		}
		return false;
		
	}
	public boolean addToFly(Fly fly,short seat,float loadWeight){
		if (findFly(fly.getNumber())==null) {
			tickets.add(new Ticket(fly,this,seat,loadWeight));
			return true;
		}
		return false;
		
	}
	// calcular el sobrepeso
	public double calcOvercrowed() {
		
		if (findPassenger(this.idPassenger)!=null && findPassenger(this.idPassenger).isBaggage()) {
			
			if(findPassenger(this.idPassenger).getWeight()>23 && findPassenger(this.idPassenger).getWeight()<=33 ) {
				
				return findPassenger(this.idPassenger).getFlights().getTarget().getValueTicket()*0.1;
			}
			else if(findPassenger(this.idPassenger).getWeight()>33) {
				
				return findPassenger(this.idPassenger).getFlights().getTarget().getValueTicket()*0.3;
			}
		}
		
		return 0;
	}
	abstract public double  getTicketCost() ;
	abstract public double  addMiles() ;
	
	public String getIdPassenger() {
		return idPassenger;
	}
	public void setIdPassenger(String idPassenger) {
		this.idPassenger = idPassenger;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public LocalDate getDateBirthday() {
		return dateBirthday;
	}
	public void setDateBirthday(LocalDate dateBirthday) {
		this.dateBirthday = dateBirthday;
	}

	public ArrayList<Ticket> getTickets() {
		return (ArrayList<Ticket>) tickets.clone();
	}

	public void setTickets(ArrayList<Ticket> flights) {
		this.tickets = flights;
	}
	
	
	

}
