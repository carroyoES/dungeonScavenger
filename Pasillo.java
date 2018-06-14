package dungeonScavenger;

import java.util.ArrayList;
import java.util.List;

class Pasillo {

    private Dungeon dungeon;
    private Habitacion habitacionActual, habitacionSiguiente;
    private List<int[]> ruta;
    private int columnaInterseccion;

    public Pasillo(Dungeon dungeon, Habitacion habitacionActual, Habitacion habitacionSiguiente) {
        this.dungeon = dungeon;
        ruta = new ArrayList<int[]>();
        this.habitacionActual = habitacionActual;
        this.habitacionSiguiente = habitacionSiguiente;
        trazarRutaASiguienteHabitacion();
    }

    private void trazarRutaASiguienteHabitacion() {
        int[] diferencia = obtenerDiferenciaXYaSiguienteHabitacion();
        establecerRuta(diferencia);
    }

    private int[] obtenerDiferenciaXYaSiguienteHabitacion() {         
        int diferenciaY = habitacionSiguiente.getOrigen()[0] - habitacionActual.getOrigen()[0];
        int diferenciaX = habitacionSiguiente.getOrigen()[1] - habitacionActual.getOrigen()[1];
        return new int[] { diferenciaX, diferenciaY };
    }

    private void establecerRuta(int[] diferencia) {
        recorrerDiferenciaX(diferencia[0]);
        recorrerDiferenciaY(diferencia[1]);
    }

    private void recorrerDiferenciaX(int diferencia) {
        int xObjetivo = habitacionActual.getOrigen()[1] + diferencia;
        if (diferencia < 0) {
            for(int x=habitacionActual.getOrigen()[1]; x>=xObjetivo; x--) {
                ruta.add(new int[]{habitacionActual.getOrigen()[0], x});
                if (x == xObjetivo)
                    columnaInterseccion = x;
            }
        }
        else {
            for (int x = habitacionActual.getOrigen()[1]; x <= xObjetivo; x++) {
                ruta.add(new int[] { habitacionActual.getOrigen()[0], x });
                if (x == xObjetivo)
                    columnaInterseccion = x;
            }
        }
    }

    private void recorrerDiferenciaY(int diferencia) {
        int yObjetivo = habitacionActual.getOrigen()[0] + diferencia;
        if (diferencia < 0) {
            for (int y = habitacionActual.getOrigen()[0]; 
            	 y > yObjetivo && !esContiguoALaHabitacionDestino(y); y--) {
                ruta.add(new int[] { y, columnaInterseccion });
            }
        }
        else {
            for (int y = habitacionActual.getOrigen()[0]; y < yObjetivo; y++) {
                ruta.add(new int[] { y, columnaInterseccion });
            }
        }
    }
    
    private boolean esContiguoALaHabitacionDestino(int fila) {
    	boolean esContiguo = false;   	
    	//compruebo arriba    	
		if(habitacionSiguiente.incluyeEstaCasilla(new int[] {fila-1, columnaInterseccion}))
			esContiguo = true;    	
    	//compruebo derecha    	
		if(habitacionSiguiente.incluyeEstaCasilla(new int[] {fila, columnaInterseccion+1}))
			esContiguo = true;    	
    	//compruebo abajo    	
		if(habitacionSiguiente.incluyeEstaCasilla(new int[] {fila+1, columnaInterseccion}))
			esContiguo = true;   	
    	//compruebo izquierda    	
		if(habitacionSiguiente.incluyeEstaCasilla(new int[] {fila, columnaInterseccion-1}))
			esContiguo = true;   	
    	return esContiguo;
    }

    public Boolean contieneEntidad(Entidad entidad) {
        if (ruta.contains(new int[] {entidad.getFilaActual(), entidad.getColumnaActual()}))
            return true;
        return false;
    }
    
    public List<int[]> getRuta() {
        return ruta;
    }
}

