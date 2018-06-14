package dungeonScavenger;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class Juego {
	
	private Ventana ventana;
    private Personaje personaje;
    private Dungeon dungeon;
    private Handler handler;
    private AdministradorDeEstilos administradorDeEstilos;
	
	public Juego() throws BadLocationException {	
		handler = new Handler(this);
		iniciar();			
	}
	
	private void iniciar() throws BadLocationException{
        inicializarComponentes();
        situarPersonajeEnZonaInicial();
        ventana.mostrarDungeonVisible();
    }

    private void inicializarComponentes(){    	
		ventana = new Ventana("Dungeon Scavenger", handler);
		administradorDeEstilos = new AdministradorDeEstilos(handler);
		dungeon = new Dungeon(handler);
        personaje = new Personaje(handler, 100, 100, 5, 3, primeraHabitacionFilaOrigen(), 
                                  primeraHabitacionColumnaOrigen(), dungeon, ventana);        
    }

    private int primeraHabitacionFilaOrigen() {
        return dungeon.getHabitaciones().get(0).getOrigen()[0];
    }

    private int primeraHabitacionColumnaOrigen() {
        return dungeon.getHabitaciones().get(0).getOrigen()[1];
    }

    private void situarPersonajeEnZonaInicial() {
        dungeon.colocarEntidad(personaje.getFilaActual(), personaje.getColumnaActual(), null, personaje.getTile());
    } 
    
    public Dungeon getDungeon() {
        return dungeon;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public Ventana getVentana() {
        return ventana;
    }
    
    public AdministradorDeEstilos getAdministradorDeEstilos() {
        return administradorDeEstilos;
    }
	
}
