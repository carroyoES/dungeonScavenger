package dungeonScavenger;


public class Goblin extends Enemigo{
    public Goblin(Handler handler, int vidaMaxima, int vidaRestante, int ataque, int defensa,
                     int filaActual, int columnaActual, Dungeon dungeon, Ventana ventana) {
    	super(handler, vidaMaxima, vidaRestante, ataque, defensa, filaActual, columnaActual,
                dungeon, ventana);
    	tile = new Tile("g", handler.getJuego().getAdministradorDeEstilos().getEstilo("estiloGoblin"));
    }
}

