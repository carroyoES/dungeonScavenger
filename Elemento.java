package dungeonScavenger;

import java.util.List;
import java.util.Random;

public abstract class Elemento {
	
	protected Handler handler;
    protected Habitacion habitacion;
    protected Dungeon dungeon;
    protected int fila, columna;
    protected Random random;
    protected Tile tile;

    public Elemento(Handler handler, Dungeon dungeon, Habitacion habitacion) {
        this.dungeon = dungeon;
        this.habitacion = habitacion;
        this.handler = handler;
        random = new Random();
        establecerPosicionInicial();
    }

    protected abstract void establecerPosicionInicial();

    protected Boolean estaEnCasillaOcupada() {
        List<Elemento> elementos = habitacion.getElementos();
        for (int x = 0; x < elementos.size(); x++) {
            if (fila == elementos.get(x).fila && columna == elementos.get(x).columna)
                return true;
        }
        return false;
    }

    public Tile getTile() {
        return tile;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

}

