package dungeonScavenger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class Ventana extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Container container;
	private Document document;
	private JTextPane cuadroCentral;
	private int anchoCuadroCentral, altoCuadroCentral;
	private JTextPane cuadroDescripcion;
	private Handler handler;
	private Juego juego;
	
	public Ventana(String titulo, Handler handler) {
		super(titulo);
		this.handler = handler;
		juego = handler.getJuego();
		inicializarComponentes();
		agnadirComponentes();
		setSize(1920, 1080);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void inicializarComponentes() {
		container = getContentPane();
		container.setLayout(new BorderLayout());
		inicializarCuadroCentral();
		inicializarCuadroDescripcion();
	}
	
	private void inicializarCuadroCentral() {
		cuadroCentral = new JTextPane();
		cuadroCentral.setBackground(Color.darkGray);
		cuadroCentral.setFont(new Font("Square", Font.PLAIN, 20));
		cuadroCentral.setEditable(false);
		anchoCuadroCentral = 50;
		altoCuadroCentral = 40;
		cuadroCentral.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {							
			}

			@Override
			public void keyPressed(KeyEvent e) {
				try {
	                if (e.getKeyCode() == KeyEvent.VK_W) {
	                    juego.getPersonaje().mover("arriba");
	                }
	                else if (e.getKeyCode() == KeyEvent.VK_D) {
	                	juego.getPersonaje().mover("derecha");
	                }
	                else if (e.getKeyCode() == KeyEvent.VK_S) {
	                	juego.getPersonaje().mover("abajo");
	                }
	                else if (e.getKeyCode() == KeyEvent.VK_A) {
	                	juego.getPersonaje().mover("izquierda");
	                }
	                else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	                    System.exit(0);
	                }
	            }
	            catch (Exception ex) { }			
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}});
		document = cuadroCentral.getDocument();
	}
	
	private void inicializarCuadroDescripcion() {
		cuadroDescripcion = new JTextPane();
		cuadroDescripcion.setBackground(Color.gray);
		cuadroDescripcion.setFont(new Font("Square", Font.PLAIN, 20));
		cuadroDescripcion.setEditable(false);
		cuadroDescripcion.setText("                             ");
	}
	
	private void agnadirComponentes() {
		container.add(cuadroCentral, BorderLayout.CENTER);
		container.add(cuadroDescripcion, BorderLayout.EAST);
	}
	
	public void mostrarDungeonVisible() throws BadLocationException {
		int filaInicioDungeon = obtenerFilaDeInicio();
		int columnaInicioDungeon = obtenerColumnaDeInicio();
        limpiarCuadroCentral();
        Tile[][] matrizMapa = handler.getJuego().getDungeon().getMatrizMapa();       
        for (int filaDungeon = filaInicioDungeon, filaCuadroCentral = 0; filaDungeon < matrizMapa.length && filaCuadroCentral < altoCuadroCentral; filaDungeon++, filaCuadroCentral++) {
            for (int columnaDungeon = columnaInicioDungeon, columnaCuadroCentral = 0; columnaDungeon < matrizMapa[0].length && columnaCuadroCentral < anchoCuadroCentral; columnaDungeon++, columnaCuadroCentral++) {          	           	
            	agnadirCasillaAlCuadroCentral(matrizMapa[filaDungeon][columnaDungeon]);
            }
            document.insertString(document.getLength(), "\n", null);
        }      
    }
	
	private int obtenerFilaDeInicio() {
		int filaDeInicio = handler.getJuego().getPersonaje().getFilaActual() - (altoCuadroCentral/2);
		if(filaDeInicio<0)
			return 0;
		return filaDeInicio;
	}
	
	private int obtenerColumnaDeInicio() {
		int columnaDeInicio = handler.getJuego().getPersonaje().getColumnaActual() - (anchoCuadroCentral/2);
		if(columnaDeInicio<0)
			return 0;
		return columnaDeInicio;
	}

    private void limpiarCuadroCentral() {
        cuadroCentral.setText("");
    }
	
	private void agnadirCasillaAlCuadroCentral(Tile tile) throws BadLocationException {	
		document.insertString(document.getLength(), tile.getSimbolo(), tile.getEstilo());		
	}
	
	public JTextPane getCuadroCentral() {
		return cuadroCentral;
	}

}
