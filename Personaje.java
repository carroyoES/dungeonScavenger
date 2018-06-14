package dungeonScavenger;


public class Personaje extends Entidad{

    public Personaje(Handler handler, int vidaMaxima, int vidaRestante, int ataque, int defensa,
                     int filaActual, int columnaActual, Dungeon dungeon, Ventana ventana)  {
    	super(handler, vidaMaxima, vidaRestante, ataque, defensa, filaActual, columnaActual,
                dungeon, ventana);
        tile = new Tile("@", handler.getJuego().getAdministradorDeEstilos().getEstilo("estiloPersonaje"));
    }

}

