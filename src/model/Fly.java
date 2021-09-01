package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;

import exceptions.ExceptionDate;

public class Fly{
	private String number ;
	private Target target;
	private Airplane airplane;
	private LocalDate date;
	private LocalTime time;
	ArrayList<Ticket> tickets;
	
	public Fly(String number, Target target, Airplane airplane, LocalDate date, LocalTime time) throws ExceptionDate {	
			if(date.isAfter(LocalDate.now())) {
				this.number = number;
				this.target = target;
				this.airplane = airplane;
				this.date = date;
				this.time = time;
				tickets= new ArrayList<>();
			}else {
			
			throw new ExceptionDate("La fecha del vuelo es menor a la fecha actual");

		}


	}
	public Ticket findPassenger(String idPassenger) {
		return tickets.stream().filter((passengerAux) -> passengerAux.getPassengers().getIdPassenger().equals(idPassenger)).findFirst().map((passengerAux -> passengerAux) ).orElse(null);
	}
	
	public boolean addPassenger(Passenger passenger,short seatNumber) {
		if (findPassenger(passenger.getIdPassenger())==null && airplane.getCapacity()>=tickets.size()+1) {
			tickets.add(new Ticket(this,passenger,seatNumber));
			return true;
		}
		return false;
	}
	public boolean addPassenger(Passenger passenger,short seatNumber, float loadWeight) {
		if (findPassenger(passenger.getIdPassenger())==null && airplane.getCapacity()>=tickets.size()+1) {
			tickets.add(new Ticket(this,passenger,seatNumber,loadWeight));
			return true;
		}
	
		return false;
	}
	public double calcTotal () {
		float total=0;
		for (Ticket ticket : tickets) {
			total+= ticket.getPassengers().getTicketCost();
		}
		return total;
	}
	public Passenger getLessAge() {
		int menor=tickets.get(0).getPassengers().getAge();
		Ticket lessAgeTicket=null;
		for (Ticket passenger : tickets) {
			if(passenger.getPassengers().getAge()<menor) {
				menor = passenger.getPassengers().getAge();
				lessAgeTicket=passenger;
			}else {
				lessAgeTicket=tickets.get(0);
			}
		}
		return lessAgeTicket.getPassengers();
	}
	public Passenger getGreaterAge() {
		int great=tickets.get(0).getPassengers().getAge();
		Ticket greatAgeTicket=tickets.get(0);
		for (Ticket passenger : tickets) {
			if(passenger.getPassengers().getAge()>great) {
				great = passenger.getPassengers().getAge();
				greatAgeTicket=passenger;
			}
		}
		return greatAgeTicket.getPassengers();
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Target getTarget() {
		return target;
	}
	public void setTarget(Target target) {
		this.target = target;
	}
	public Airplane getAirplane() {
		return airplane;
	}
	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public ArrayList<Ticket> getTickets() {
		return (ArrayList<Ticket>)tickets.clone();
	}
	public void setTickets(ArrayList<Ticket> ticket) {
		this.tickets = ticket;
	}

	
	
	
	
	


}
