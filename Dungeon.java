package dungeonScavenger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.text.BadLocationException;

public class Dungeon
{    

    private Tile[][] matrizMapa;   
    private Tile muro, suelo, puerta;
    private int numeroFilas;
    private int numeroColumnas;
    private int numeroDeHabitaciones;
    private List<Habitacion> habitaciones;
    private List<Pasillo> pasillos;
    private List<Enemigo> enemigos;
    private Handler handler;

    public Dungeon(Handler handler){
        this.handler = handler;
        inicializarCasillasBasicas();
        //TODO: sacar numero de habitaciones según un algoritmo en base al tamaño de la dungeon //10 bien para 80x80
        numeroDeHabitaciones = 10;
        habitaciones = new ArrayList<Habitacion>();
        pasillos = new ArrayList<Pasillo>();
        enemigos = new ArrayList<Enemigo>();
        darValorAleatorioAFilasYColumnas();          
        crearMatrizMapa();           
    }
    
    private void inicializarCasillasBasicas() {
    	muro = new Tile("#", handler.getJuego().getAdministradorDeEstilos().getEstilo("estiloMuros"));
    	suelo = new Tile(".", handler.getJuego().getAdministradorDeEstilos().getEstilo("estiloSuelo"));
    	puerta = new Tile("/", handler.getJuego().getAdministradorDeEstilos().getEstilo("estiloAzul"));
    }

    private void darValorAleatorioAFilasYColumnas(){
        //TODO: dar valores aleatorios //80 80 muy bien
        numeroFilas = 80;
        numeroColumnas = 80;
    }


    private void crearMatrizMapa(){
        matrizMapa = new Tile[numeroFilas][numeroColumnas];
        crearBase();
        crearHabitaciones();
        crearPasillos();
        crearEnemigos();
    }

    private void crearBase() {
    	try {
        for (int fila = 0; fila < matrizMapa.length; fila++)
            for (int columna = 0; columna < matrizMapa[0].length; columna++)
                matrizMapa[fila][columna] = muro;
    	}catch(Exception e) {}
    }
   
    private void crearHabitaciones() {
        for (int x = 0; x < numeroDeHabitaciones; x++) {
            Habitacion habitacion = new Habitacion(handler, this);
            habitaciones.add(habitacion);
            colocarTilesHabitacion(habitacion);
        }
    }

    private void colocarTilesHabitacion(Habitacion habitacion) {
        int[] origen = habitacion.getOrigen();
        int ancho = habitacion.getAncho();
        int alto = habitacion.getAlto();
        try {
            for (int fila = origen[0]; fila < origen[0] + ancho; fila++)
                for (int columna = origen[1]; columna < origen[1] + alto; columna++)
                    matrizMapa[fila][columna] = suelo;
            colocarTilesElementos(habitacion);
        }
        catch (Exception e) {}
    }

    private void colocarTilesElementos(Habitacion habitacion) {
        List<Elemento> elementos = habitacion.getElementos();
        for (int x = 0; x < elementos.size(); x++)
            matrizMapa[elementos.get(x).getFila()][elementos.get(x).getColumna()] = elementos.get(x).getTile();
    }

    public void colocarEntidad(int fila, int columna, String direccion, Tile tile) {
        recolocarSuelo(fila, columna, direccion);
        try {
            matrizMapa[fila][columna] = tile;
        }
        catch (Exception e) { }
    }

    private void recolocarSuelo(int fila, int columna, String direccion) {
        if (direccion != null) {
            try {
                if (direccion.equals("arriba"))
                    matrizMapa[fila + 1][columna] = suelo;
                else if (direccion.equals("derecha"))
                    matrizMapa[fila][columna - 1] = suelo;
                else if (direccion.equals("abajo"))
                    matrizMapa[fila - 1][columna] = suelo;
                else if (direccion.equals("izquierda"))
                    matrizMapa[fila][columna + 1] = suelo;
            }
            catch (Exception e) { }
        }
    }

    private void crearPasillos() {
        for (int indiceHabitacion = 0; indiceHabitacion < numeroDeHabitaciones - 1; indiceHabitacion++) {
            Pasillo pasillo = new Pasillo(this, habitaciones.get(indiceHabitacion), habitaciones.get(indiceHabitacion+1));
            pasillos.add(pasillo);
            colocarTilesPasillo(pasillo);
        }      
    }

    private void colocarTilesPasillo(Pasillo pasillo) {
        List<int[]> ruta = pasillo.getRuta();
        try {
            for (int x = 0; x < ruta.size(); x++) {
            	matrizMapa[ruta.get(x)[0]][ruta.get(x)[1]] = suelo;
            }
        }
        catch (Exception e) { }
    }

    private void crearEnemigos() {
        for (int x=0; x<20; x++) {
            int fila;
            int columna;
            do {
                fila = ThreadLocalRandom.current().nextInt(0, numeroFilas);
                columna = ThreadLocalRandom.current().nextInt(0, numeroColumnas);
            } while (!esCasillaOcupada(fila, columna));
            Enemigo enemigo = new Goblin(handler, 100, 100, 5, 3, fila, columna, this, handler.getJuego().getVentana());
            enemigos.add(enemigo);
        }
        colocarTilesEnemigos();
    }

    public Boolean esCasillaOcupada(int fila, int columna) {
        try {
            if (matrizMapa[fila][columna] == suelo)
                return true;
        }
        catch (Exception e) { return true; }
        return false;
    }

    private void colocarTilesEnemigos() {
        for (int x = 0; x < enemigos.size(); x++)
            matrizMapa[enemigos.get(x).getFilaActual()][enemigos.get(x).getColumnaActual()] = enemigos.get(x).getTile();
    }
    
    public void hacerTurnoDungeon() throws BadLocationException {
    	ejecutarTurnoEnemigos();
    }
    
    private void ejecutarTurnoEnemigos() throws BadLocationException {
    	for(Enemigo enemigo : enemigos)
    		enemigo.actuar();
    }

    public Tile[][] getMatrizMapa() {
        return matrizMapa;
    }

    public int getNumeroColumnas() {
        return numeroColumnas;
    }

    public int getNumeroFilas() {
        return numeroFilas;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public List<Pasillo> getPasillos() {
        return pasillos;
    }
}

