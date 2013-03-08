package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import algoritmoGenetico.Cromosoma;
import controlador.Controlador;

public class InterfazGrafica extends JFrame{
	private static final long serialVersionUID = 1L;

	/* Controlador & Parametros */
	private Controlador controlador;
	private Parametros parametros;
	
	/* Constants definition */
	private static final int FRAME_WEIGHT = 600;
	private static final int FRAME_HEIGHT = 480;
	
	/* Graphic components */
	private JPanel panelPrincipal;
	private JButton boton;
	private JLabel labelPoblacion;
	private JTextField formPoblacion;
	
	public InterfazGrafica(Controlador c) 
	{
		this.controlador = c;
		propiedadesBasicas();
		this.setContentPane(obtenerPanelPrincipal());
		
		this.parametros = new Parametros(-1, -1, -1, -1, -1);
	}
	
	private JPanel obtenerPanelPrincipal()
	{
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		
		labelPoblacion = new JLabel("Poblacion: ");
		panelPrincipal.add(labelPoblacion);
		formPoblacion = new JTextField("100");
		panelPrincipal.add(formPoblacion);
		
		boton = new JButton("OK");
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recogerParametros();
				controlador.lanzaGenetico(parametros);
			}
		});
		panelPrincipal.add(boton);
		
		return panelPrincipal;
	}
	
	private void recogerParametros()
	{
		this.parametros.setTamPoblacion(Integer.parseInt(formPoblacion.getText()));
	}
	
	private void propiedadesBasicas()
	{
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, FRAME_WEIGHT, FRAME_HEIGHT);
		setResizable(false);
	}
	
	public Parametros getParametros(){
		//TODO
		return new Parametros(100, 100, 0.7, 0.01, 0.01);
	}
	
	/**
	 * 
	 * @param cromosoma a imprimir
	 */
	public void mostrar(Cromosoma cromosoma){
		// TODO
		System.out.println("Solucion => " + cromosoma.toString() + " Fenotipo =>" + cromosoma.getFenotipo() +
				" Aptitud =>" + cromosoma.getAptitud());
	}
}
