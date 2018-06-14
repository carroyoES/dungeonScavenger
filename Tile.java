package dungeonScavenger;

import javax.swing.text.Style;

public class Tile {
	
	private String simbolo;
	private Style estilo;
	
	public Tile(String simbolo, Style estilo) {
		this.simbolo = simbolo;
		this.estilo = estilo;
	}
	
	public String getSimbolo() {
		return simbolo;
	}
	
	public Style getEstilo() {
		return estilo;
	}

}
