package dungeonScavenger;

import javax.swing.text.BadLocationException;

public class Entidad {

	protected Handler handler;
    protected int vidaMaxima, vidaRestante, ataque, defensa, filaActual, columnaActual;
    protected Dungeon dungeon;
    protected Tile[][] matrizMapa;
    protected Ventana ventana;
    protected Tile tile;
    protected Habitacion habitacionActual;
    protected Pasillo pasilloActual;

    public Entidad(Handler handler, int vidaMaxima, int vidaRestante, int ataque, int defensa,
                   int filaActual, int columnaActual, Dungeon dungeon, Ventana ventana) {
        establecerAtributos(vidaMaxima, vidaRestante, ataque, defensa);
        setPosicionActual(filaActual, columnaActual);
        //setHabitacionActual();
        //setPasilloActual();
        this.handler = handler;
        this.dungeon = dungeon;
        this.ventana = ventana;
        this.matrizMapa = dungeon.getMatrizMapa();      
    }

    private void establecerAtributos(int vidaMaxima, int vidaRestante, int ataque, int defensa) {
        this.vidaMaxima = vidaMaxima;
        this.vidaRestante = vidaRestante;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public void mover(String direccion) throws BadLocationException {
        Boolean puedeMover = false;
        if (direccion.equals("arriba")) {
            if (casillaLibre(direccion)) {
                puedeMover = true;
                filaActual--;
            }
        }
        else if (direccion.equals("derecha")) {
            if (casillaLibre(direccion)) {
                puedeMover = true;
                columnaActual++;
            }
        }
        else if (direccion.equals("abajo")) {
            if (casillaLibre(direccion)) {
                puedeMover = true;
                filaActual++;
            }
        }
        else if (direccion.equals("izquierda")) {
            if (casillaLibre(direccion)) {
                puedeMover = true;
                columnaActual--;
            }
        }
        if (puedeMover) {
            dungeon.colocarEntidad(filaActual, columnaActual, direccion, tile);
            if(this.equals(handler.getJuego().getPersonaje())) {
            	setHabitacionActual();
            	setPasilloActual();
            	dungeon.hacerTurnoDungeon();
            	ventana.mostrarDungeonVisible();
            }       
        }
    }

    private Boolean casillaLibre(String direccion) {
        if (direccion.equals("arriba")) {
            if (matrizMapa[filaActual-1][columnaActual].getSimbolo().equals(".")) {
                return true;
            }
        }
        else if (direccion.equals("derecha")) {
            if (matrizMapa[filaActual][columnaActual+1].getSimbolo().equals(".")) {
                return true;
            }
        }
        else if (direccion.equals("abajo")) {
            if (matrizMapa[filaActual+1][columnaActual].getSimbolo().equals(".")) {
                return true;
            }
        }
        else if (direccion.equals("izquierda")) {
            if (matrizMapa[filaActual][columnaActual-1].getSimbolo().equals(".")) {
                return true;
            }
        }
        return false;
    }
    
    public Habitacion getHabitacionActual() {
		return habitacionActual;
	}

	public Pasillo getPasilloActual() {
		return pasilloActual;
	}

	protected void setHabitacionActual() {
    	habitacionActual = null;
    	for(Habitacion habitacion : dungeon.getHabitaciones())
    		if(habitacion.contieneEntidad(this))
    			habitacionActual = habitacion;
    }
    
    protected void setPasilloActual() {
    	pasilloActual = null;
    	for(Pasillo pasillo : dungeon.getPasillos())
    		if(pasillo.contieneEntidad(this))
    			pasilloActual = pasillo;
    }
    
    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVidaRestante() {
        return vidaRestante;
    }

    public int getFilaActual() {
        return filaActual;
    }

    public int getColumnaActual() {
        return columnaActual;
    }

    public Tile getTile() {
        return tile;
    }

    public void setPosicionActual(int fila, int columna) {          
        filaActual = fila;
        columnaActual = columna;
    }
}

