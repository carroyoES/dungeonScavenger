package dungeonScavenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Habitacion {

    private int[] origen = new int[2];
    private int ancho, alto;
    private List<Elemento> elementos;
    private Dungeon dungeon;
    private Handler handler;

    public Habitacion(Handler handler, Dungeon dungeon) {
    	this.handler = handler;
        this.dungeon = dungeon;
        elementos = new ArrayList<Elemento>();
        darValores();
    }

    private void darValores() {
        do {
            crearOrigenAleatorio();
            crearAnchoAleatorio();
            crearAltoAleatorio();               
        } while (!cabe() || contiguaAOtraHabitacionOPasillo());
        crearElementosAleatorios();
    }

    private void crearOrigenAleatorio() {
        origen[0] = ThreadLocalRandom.current().nextInt(1, dungeon.getNumeroColumnas());
        origen[1] = ThreadLocalRandom.current().nextInt(1, dungeon.getNumeroFilas());
    }

    private void crearAnchoAleatorio() {
        //TODO el ancho máximo dependerá de las dimensiones de la dungeon
    	ancho = ThreadLocalRandom.current().nextInt(3, 21);
	}
	
	private void crearAltoAleatorio() {
	    //TODO el alto máximo dependerá de las dimensiones de la dungeon
	    alto = ThreadLocalRandom.current().nextInt(3, 21);
	}
	
	//si no hay '.' en la zona y no pilla casillas del borde
	private Boolean cabe() {
	    Boolean cabe = true;
	    try {
	        for (int fila = origen[0]; fila < origen[0] + ancho; fila++)
	            for (int columna = origen[1]; columna < origen[1] + alto; columna++)
	                if (dungeon.getMatrizMapa()[fila][columna].getSimbolo().equals(".") || esCasillaDelBorde(fila, columna))
	                        cabe = false;
	        }
	        catch (Exception e) { cabe = false; }
	        return cabe;
	}

    private Boolean esCasillaDelBorde(int fila, int columna) {
        if (fila == dungeon.getNumeroFilas()-1 || columna == dungeon.getNumeroColumnas()-1)
            return true;
        return false;
    }

    private boolean contiguaAOtraHabitacionOPasillo() {
    	//porque si no, el avistamiento del personaje no funciona
    	boolean contiguaAOtraHabitacionOPasillo = false;
    	//comprobación hacia arriba:
    	for(int columna=origen[1]; columna<origen[1]+ancho; columna++) {
    		if(dungeon.esCasillaOcupada(origen[0]-1, columna))
    			contiguaAOtraHabitacionOPasillo = true;
    	}
    	//comprobación hacia la derecha:
    	for(int fila=origen[0]; fila<origen[0]+alto; fila++) {
    		if(dungeon.esCasillaOcupada(fila, origen[1]+ancho))
    			contiguaAOtraHabitacionOPasillo = true;
    	}
    	//comprobación hacia abajo:
    	for(int columna=origen[1]; columna<origen[1]+ancho; columna++) {
    		if(dungeon.esCasillaOcupada(origen[0]+alto, columna))
    			contiguaAOtraHabitacionOPasillo = true;
    	}
    	//comprobación hacia la izquierda:
    	for(int fila=origen[0]; fila<origen[0]+alto; fila++) {
    		if(dungeon.esCasillaOcupada(fila, origen[1]-1))
    			contiguaAOtraHabitacionOPasillo = true;
    	}
    	return contiguaAOtraHabitacionOPasillo;
    }
    
    private void crearElementosAleatorios() {
        crearCofres();
        crearMobiliario();
    }

    private void crearCofres() {
        if (tieneCofre()) 
            elementos.add(new Cofre(handler, dungeon, this));        
    }

    private void crearMobiliario() {

    }

    private Boolean tieneCofre() {
        if (ThreadLocalRandom.current().nextInt(1, 11) == 1)
            return true;
        return false;
    }
    
    public Boolean contieneEntidad(Entidad entidad) {
        if (incluyeEstaCasilla(new int[] {entidad.getFilaActual(), entidad.getColumnaActual()}))
            return true;
        return false;
    }

    public int[] getOrigen() {
        return origen;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public List<Elemento> getElementos() {
        return elementos;
    }

    public Boolean incluyeEstaCasilla(int[] casilla) {
        if (casilla[0] >= origen[0] && casilla[0] < (origen[0] + ancho)-1 &&
            casilla[1] >= origen[1] && casilla[1] < (origen[1] + alto)-1)
            return true;
        return false;
    }

}

