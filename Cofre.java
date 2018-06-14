package dungeonScavenger;
import java.util.concurrent.ThreadLocalRandom;

public class Cofre extends Elemento {
    public Cofre(Handler handler, Dungeon dungeon, Habitacion habitacion) {
    	super(handler, dungeon, habitacion);
        tile = new Tile("C", handler.getJuego().getAdministradorDeEstilos().getEstilo("estiloCofre"));
    }
  
    protected void establecerPosicionInicial() {
        do {
            fila = ThreadLocalRandom.current().nextInt(habitacion.getOrigen()[0], habitacion.getOrigen()[0] + habitacion.getAlto());
            columna = ThreadLocalRandom.current().nextInt(habitacion.getOrigen()[1], habitacion.getOrigen()[1] + habitacion.getAncho());
        } while (estaEnCasillaOcupada());
    }

}

