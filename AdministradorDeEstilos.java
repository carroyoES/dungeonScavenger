package dungeonScavenger;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

public class AdministradorDeEstilos {
	
	private Handler handler;
	private  JTextPane cuadroCentral;
	private Style estiloAzul;
	private Style estiloMuros;
	private Style estiloBlanco;
	private Style estiloCofre;
	private Style estiloGoblin;
	private Style estiloPersonaje;
	
	public AdministradorDeEstilos(Handler handler) {
		this.handler = handler;
		cuadroCentral = handler.getJuego().getVentana().getCuadroCentral();
		inicializarEstilos();
		agnadirEstilosAlCuadroCentral();
	}
	
	private void inicializarEstilos() {
		estiloAzul = cuadroCentral.addStyle("estiloAzul", null);
		estiloMuros = cuadroCentral.addStyle("estiloMuros", null);
		estiloBlanco = cuadroCentral.addStyle("estiloBlanco", null);
		estiloCofre = cuadroCentral.addStyle("estiloCofre", null);
		estiloGoblin = cuadroCentral.addStyle("estiloGoblin", null);
		estiloPersonaje = cuadroCentral.addStyle("estiloPersonaje", null);
	}
	
	private void agnadirEstilosAlCuadroCentral() {
		StyleConstants.setForeground(estiloAzul, Color.blue);
		StyleConstants.setForeground(estiloMuros, new Color(0, 0, 0));
		StyleConstants.setForeground(estiloBlanco, Color.white);
		StyleConstants.setForeground(estiloCofre, Color.yellow);
		StyleConstants.setForeground(estiloGoblin, Color.green);
		StyleConstants.setForeground(estiloPersonaje, Color.cyan);
	}
	
	public Style getEstilo(String estilo) {
		switch(estilo) {
		case "estiloAzul":
			return estiloAzul;
		case "estiloMuros":
			return estiloMuros;
		case "estiloBlanco":
			return estiloBlanco;
		case "estiloCofre":
			return estiloCofre;
		case "estiloGoblin":
			return estiloGoblin;
		case "estiloPersonaje":
			return estiloPersonaje;
		}
		return null;
	}
	
}
