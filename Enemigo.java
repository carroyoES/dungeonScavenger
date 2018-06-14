package dungeonScavenger;

import java.util.concurrent.ThreadLocalRandom;

import javax.swing.text.BadLocationException;

public class Enemigo extends Entidad{
	private boolean personajeAvistado;
	
    public Enemigo(Handler handler, int vidaMaxima, int vidaRestante, int ataque, int defensa,
                     int filaActual, int columnaActual, Dungeon dungeon, Ventana ventana) {
    	super(handler, vidaMaxima, vidaRestante, ataque, defensa, filaActual, columnaActual,
        dungeon, ventana);
    	personajeAvistado = false;
    }
    
    public void actuar() throws BadLocationException {
    	if(personajeAvistado)
    		moverHaciaElPersonaje();
    	else {
    		deambular();
    		setHabitacionActual();
    		setPasilloActual();
    		comprobarSiVeAlPersonaje();
    	}
    }
    
    private void deambular() throws BadLocationException {
    	int direccion = ThreadLocalRandom.current().nextInt(1, 5);
    	switch(direccion) {
    	case 1:
    		mover("arriba");
    		break;
    	case 2:
    		mover("derecha");
    		break;
    	case 3:
    		mover("abajo");
    		break;
    	case 4:
    		mover("izquierda");
    		break;
    	}
    }
    
    private void comprobarSiVeAlPersonaje() {
    	//http://www.roguebasin.com/index.php?title=Extremely_fast_simplified_LOS
    	//distancia vision de cada entidad
    	if(habitacionActual == null && pasilloActual == null)
    		return;
    	if(habitacionActual!=null) {
    		if(habitacionActual == handler.getJuego().getPersonaje().getHabitacionActual()) {
    			personajeAvistado = true;
    			System.out.println("Personaje avistado habitación");
    		}
    	}
    	else {
    		//TODO: aunque esté en el mismo pasillo, hay que comprobar si lo ve en linea recta
    		if(pasilloActual == handler.getJuego().getPersonaje().getPasilloActual()) {
    			personajeAvistado = true;
    			System.out.println("Personaje avistado pasillo");
    		}
    	}
    }
    
    private void moverHaciaElPersonaje() {
    	//si la diferencia en x es más pequeña, mover en x, y viceversa
    }
}

