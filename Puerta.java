package dungeonScavenger;
public class Puerta extends Elemento{
    public Puerta(Handler handler, Dungeon dungeon, Habitacion habitacion, int fila, int columna) {
    	super(handler, dungeon, habitacion);
        tile = new Tile("/", handler.getJuego().getAdministradorDeEstilos().getEstilo("estiloCofre"));
        this.fila = fila;
        this.columna = columna;
    }

    protected void establecerPosicionInicial() {

    }
}

