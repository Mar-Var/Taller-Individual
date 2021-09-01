package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import exceptions.ExceptionDate;

class TestPassenger {

	 	private Fly fly;
	 	private Fly fly1;
	    private Airplane  plane;
	    private Airplane  plane2;
	    private Target madrid;
	    private Target buenaventura;
	    Registered fabio;
	    Occasional victor;

	    /**
	     * Metodo para crear el escenario con el estado de los objetos.
	     */
	    private void setup() throws ExceptionDate {
	        plane = new Airplane("AB-45",(short)120,(short)2015, ETypeAirplane.AirbusA330 );
	        plane2 = new Airplane("AB",(short)120,(short)2015, ETypeAirplane.Boeing763 );
	        madrid = new Target("TG-01","Madrid",2_500_000,7890f);
	        buenaventura = new Target("TG_ASD", "Buenaventura", 2_800_00, 1000f);
	        fly = new Fly("Fl874",madrid,plane, LocalDate.of(2021, Month.DECEMBER,12), LocalTime.of(16,30));
	        fly1 = new Fly("Fl875",buenaventura,plane, LocalDate.of(2021, Month.SEPTEMBER,12), LocalTime.of(16,30));

	        fabio = new Registered("74898394","Fabio Enrique","Lozano Yepes","Colombia",LocalDate.of(1970,Month.JANUARY,11),LocalDate.of(2017,Month.FEBRUARY,12));

	        victor = new Occasional( "PAS8375","Victor","Putin","Rusia",LocalDate.of(1996,Month.JULY,20),null);
	    }

	    @Test
	    /**
	     * Caso de Prueba para Pasajeros registrados
	     */
	    public void testRegistered() throws ExceptionDate {
	        setup( );
	        //Se agrega al vuelo de Madrid al pasajero Fabio (Pasajero Registrado). Valor del Tiquete 2.500.000
	        fly.addPassenger( fabio,(short)5 );
	        //Es necesario al Pasajero asignarle el vuelo
	        fabio.addToFly(fly, (short)5);
	        //El año del Avion es 2015, por lo tanto no aplica el 10% de descuento.
	        //Se aplica un descuento del 15% por ser Pasajero Registrado
	        assertEquals(2_125_000,fabio.getTicketCost(),0.1);

	        //Cambiamos el año del avion para que aplique un 10% de descuento adicional sobre el valor del Ticket
	        plane.setYear((short)2005);
	        assertEquals(1_875_000,fabio.getTicketCost(),0.1);
	    }

	    @Test
	    /**
	     * Caso de Prueba que valida que se sumen las millas de la distancia del destino al pasajero Registrado
	     */
	    public void testMilles() throws ExceptionDate {

	        setup();

	        fly.addPassenger( fabio,(short)30 ); //Pasajero Registrado 
	        fabio.addToFly( fly,(short)10 ); 
	        fly.addPassenger( victor,(short)20 ); //Pasajero no Registrado,
	        victor.addToFly( fly ,(short)30); 
	        // Añadimos a otro vuelo a el pasajero fabio para sumar millas
	        fly1.addPassenger(fabio, (short)5);
	        fabio.addToFly(fly1, (short)5 );
	        
	        assertEquals(8890,fabio.getMilles());
	    }

	    @Test
	    public void testOccasional() throws ExceptionDate {
	        setup( );

	        //Se agrega al vuelo de Madrid al pasajero Victor (Pasajero Ocasional).Con sobreequipaje
	        fly.addPassenger( victor,(short)10,35f );
	        //Es necesario al Pasajero asignarle el vuelo.Con sobreequipaje
	        victor.addToFly( fly ,(short)10,35f );
	        
	        assertEquals( 3_250_000,victor.getTicketCost());

	        fly.getAirplane().setYear( (short) 2000 );
	        assertEquals( 3_000_000,victor.getTicketCost());

	    }

	    @Test
	    /**
	     * Caso de prueba que valida el metodo de calcular la edad de un Pasajero
	     */
	    public void testGetAge() throws ExceptionDate {
	        setup();
	        assertEquals(51, fabio.getAge());
	        assertEquals(25, victor.getAge());
	    }
}
