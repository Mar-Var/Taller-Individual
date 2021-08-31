package run;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import exceptions.ExceptionDate;
import model.Airplane;
import model.ETypeAirplane;
import model.Fly;
import model.Occasional;
import model.Registered;
import model.Target;

public class test {
	public static void main(String ...args) throws ExceptionDate {
		
		 	Airplane plane = new Airplane("AB-45",(short)120,(short)2015, ETypeAirplane.AirbusA330 );
		 	Target madrid = new Target("TG-01","Madrid",2_500_000,7890f);
		 	
		 	Fly fly = new Fly("Fl874",madrid,plane, LocalDate.of(2021, Month.APRIL,12), LocalTime.of(16,30));
		 	
		 	Registered fabio = new Registered("74898394","Fabio Enrique","Lozano Yepes","Colombia",LocalDate.of(1970,Month.JANUARY,11),LocalDate.of(2017,Month.FEBRUARY,12));

		 	Occasional victor = new Occasional( "PAS8375","Victor","Putin","Rusia",LocalDate.of(1996,Month.JULY,20),null);
		
		 	
		 	fly.addPassenger( victor,(short)10,35f );
		 	System.out.println("-------------------------------------");
		 	victor.addToFly( fly ,(short)10,35f );
		 	
		 	System.out.println(victor.getTicketCost());
		 	
		    ;
		        
	}

}
