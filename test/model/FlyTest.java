package model;

import exceptions.ExceptionDate;
import static  org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;


/**
 * Clase de Pruebas de la Clase Fly.
 */
class FlyTest {
    private Fly fly;
    private Airplane  plane;
    private Target madrid;
    Passenger fabio;
    Passenger victor;
    Passenger alejandra;

    /**
     * Metodo para crear el escenario con el estado de los objetos.
     */
    private void setup() throws ExceptionDate {
        plane = new Airplane("AB-45",(short)120,(short)2015, ETypeAirplane.AirbusA330 );
        madrid = new Target("TG-01","Madrid",2500000,7890f);
        fly = new Fly("Fl01",madrid,plane, LocalDate.of(2021,Month.DECEMBER,12),LocalTime.of(16,30));

        //Pasajero Registrado
        fabio = new Registered("74898394","Fabio Enrique","Lozano Yepes","Colombia",LocalDate.of(1970,Month.JANUARY,11),LocalDate.of(2017,Month.FEBRUARY,12));
        //El Pasajero ocasional
        victor = new Occasional( "PAS8375","Victor","Putin","Rusia",LocalDate.of(1996,Month.JULY,20),null);
        //Pasajero Registrado
        alejandra = new Registered("46892374","Alejandra","Ascencio","Chile",LocalDate.of(2000,Month.DECEMBER,4),LocalDate.of(2020,Month.JANUARY,30));
    }

    @Test
    /**
     * Caso de prueba para validar que la fecha del vuelo es mayor a la fecha actual
     */
    public void testNewFly(){
    	
        //Si La fecha del vuelo es menor a la fecha actual, se debe lanzar la excepcion
        assertThrows(ExceptionDate.class, ()->{
            Fly fly = new Fly("Fl000",madrid,plane,LocalDate.of(2020,Month.DECEMBER,22), LocalTime.of( 15,23));
        });
        
        assertAll(()->{
           Fly fly = new Fly("Fl000", madrid,plane,LocalDate.of(2025, Month.APRIL,22), LocalTime.of( 15,23));
           fly.setDate( LocalDate.of(2030,Month.AUGUST,12));
        });
    }

    @Test
    /**
     * Caso de prueba que valida el metodo de agregar pasajeros (uno a uno)
     */
    void testAddPassenger() throws ExceptionDate {
        setup();
        //Se agrega el pasajero Alejandra al Vuelo, el metodo deberia retornar verdadero
        assertTrue( fly.addPassenger( alejandra,(short)10 ) );
        
        //El Pasajero Alejandra  se agrega al vuelo,  el metodo deberia retornar Falso al agregar de nuevo
        //El mismo pasagero al mismo vuelo
        assertFalse( fly.addPassenger( alejandra,(short)10 ) );
        assertTrue( fly.addPassenger( fabio,(short)10 ) );
        assertTrue( fly.addPassenger( victor,(short)10 ) );
        //El tamaño del arreglo de pasajeros deberia ser 3.
        assertEquals(3, fly.getTickets().size());

        //Crear un aeroplano de dos pasajeros para validar que no permita agregar mas pasajeros de su capacidad
        Airplane cesna = new Airplane("LT63",(short) 2, (short)2019,ETypeAirplane.Cesna);

        //Se crea un nuevo vuelo
        Fly auxFly = new Fly( "Fl1111",new Target("7464","Medellin",120000,500f ),cesna,LocalDate.of(2021,Month.DECEMBER,18), LocalTime.of(19,20) );
        //Se agregan dos nuevos pasajeros
        auxFly.addPassenger(fabio,(short)10);
        auxFly.addPassenger(victor,(short)25);
        //No se debe permitir agregar un tercer pasajero por que la capacidad del cesna es 2, entonces retorna Falso
        assertFalse( auxFly.addPassenger( alejandra ,(short)10));
    }

    @Test
    void getPassengers() throws ExceptionDate {
        setup();
        fly.addPassenger( victor ,(short)10);
        fly.addPassenger( alejandra,(short)10 );
        fly.addPassenger( fabio ,(short)10);
        assertEquals( 3, fly.getTickets().size());
        assertEquals("Alejandra",fly.getTickets().get(1).getPassengers().getFirstName());
    }

    @Test
    /**
     * Caso de Prueba que valida el total del costo de los tickets de todos los pasajeros del vuelo.
     */
    public void testCalcTotal() throws ExceptionDate {
        setup();
        //Agregar los tres pasajeros al Vuelo de Madrid, el avion es de 2015, no aplica descuento
        fly.addPassenger( alejandra,(short)10); //Pasajero Registrado - Valor Tiquete 2.500.000 - 15% = 2.125.000
        alejandra.addToFly( fly ,(short)10);
        fly.addPassenger( fabio ,(short)20); //Pasajero Registrado - Valor Tiquete 2.500.000 - 15% = 2.125.000
        fabio.addToFly( fly,(short)20 );
        fly.addPassenger( victor,(short)15 ); //Pasajero no Registrado, tiene descuento - valor tiquete 2.500.000.
        victor.addToFly( fly,(short)15 );
        //El valor del vuelo es la suma del valor de ticket de cada pasajero.
        assertEquals(6_750_000,fly.calcTotal(),0.1);

        //Cambiando el año del avion, a mas de 10 años, se resta el 10% del valor del Ticket (250.000)
        
        plane.setYear((short)2000);
        
        //Fabio, valor Ticket - 250.000 = 1.875.000
        //Alejandra, valor Ticket - 250.000 = 1.875.000
        //Victor, valor Ticket - 250.000 = 2.250.000

        assertEquals(6_000_000,fly.calcTotal(),0.1);
    }

    @Test
    /**
     * Caso de prueba que valida el metodo que retorna el Pasajero de menor edad en el vuelo.
     */
    public void testGetLessAge() throws ExceptionDate {
        setup();
        //Agregar los tres pasajeros al Vuelo de Madrid, el avion es de 2015, no aplica descuento
        fly.addPassenger( alejandra,(short)10); //Pasajero Registrado - Valor Tiquete 2.500.000 - 15% = 2.125.000
        alejandra.addToFly( fly ,(short)20);
        fly.addPassenger( fabio,(short)30 ); //Pasajero Registrado - Valor Tiquete 2.500.000 - 15% = 2.125.000
        fabio.addToFly( fly,(short)10 );
        fly.addPassenger( victor,(short)20 ); //Pasajero no Registrado, tiene descuento - valor tiquete 2.500.000.
        victor.addToFly( fly ,(short)30);
        assertEquals("Alejandra", fly.getLessAge().getFirstName());
        assertEquals(20, fly.getLessAge().getAge());
    }
    
    @Test
    public void testGetGreaterAge() throws ExceptionDate{
        setup();
        //Agregar los tres pasajeros al Vuelo de Madrid, el avion es de 2015, no aplica descuento
        fly.addPassenger( alejandra,(short)10); //Pasajero Registrado - Valor Tiquete 2.500.000 - 15% = 2.125.000
        alejandra.addToFly( fly ,(short)20);
        fly.addPassenger( fabio,(short)30 ); //Pasajero Registrado - Valor Tiquete 2.500.000 - 15% = 2.125.000
        fabio.addToFly( fly,(short)10 );
        fly.addPassenger( victor,(short)20 ); //Pasajero no Registrado, tiene descuento - valor tiquete 2.500.000.
        victor.addToFly( fly ,(short)30);
        assertEquals("Fabio Enrique", fly.getGreaterAge().getFirstName());
        assertEquals(51, fly.getGreaterAge().getAge());
    	
    }
}