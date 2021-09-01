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
		Target madrid = new Target("TG-01","Madrid",2500000,7890f);
		Fly fly = new Fly("Fl01",madrid,plane, LocalDate.of(2021,Month.DECEMBER,12),LocalTime.of(16,30));

        //En el constructor de Pasajero Registrado se debe envíar la fecha de registro
		Registered fabio = new Registered("74898394","Fabio Enrique","Lozano Yepes","Colombia",LocalDate.of(1970,Month.JANUARY,11),LocalDate.of(2017,Month.FEBRUARY,12));
        //El Pasajero ocasional no requiere la fecha de registro
		Occasional victor = new Occasional( "PAS8375","Victor","Putin","Rusia",LocalDate.of(1996,Month.JULY,20),null);
        //Pasajero Registrado
		Registered alejandra = new Registered("46892374","Alejandra","Ascencio","Chile",LocalDate.of(2000,Month.DECEMBER,4),LocalDate.of(2020,Month.JANUARY,30));
		
		fly.addPassenger( alejandra,(short)10); //Pasajero Registrado - Valor Tiquete 2.500.000 - 15% = 2.125.000
        alejandra.addToFly( fly ,(short)20);
        fly.addPassenger( fabio,(short)30 ); //Pasajero Registrado - Valor Tiquete 2.500.000 - 15% = 2.125.000
        fabio.addToFly( fly,(short)10 );
        fly.addPassenger( victor,(short)20 ); //Pasajero no Registrado, tiene descuento - valor tiquete 2.500.000.
        victor.addToFly( fly ,(short)30);
        
        System.out.println(fly.getTickets()); ;
        System.out.println(fly.getGreaterAge().getFirstName());
        Airplane cesna = new Airplane("LT63",(short) 2, (short)2019,ETypeAirplane.Cesna);

        //Crar un nuevo vuelo
        Fly auxFly = new Fly( "Fl1111",new Target("7464","Medellin",120000,500f ),cesna,LocalDate.of(2021,Month.DECEMBER,18), LocalTime.of(19,20) );
        System.out.println(auxFly.addPassenger(fabio,(short)10));
        System.out.println(auxFly.addPassenger(victor,(short)25));
        System.out.println( auxFly.addPassenger(alejandra,(short)25));
        System.out.println(auxFly.getTickets());
        
		        
	}

}
