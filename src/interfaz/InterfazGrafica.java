package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import algoritmoGenetico.Cromosoma;
import controlador.Controlador;
import es.ucm.fdi.pe.ConfigPanel.ChoiceOption;
import es.ucm.fdi.pe.ConfigPanel.DoubleOption;
import es.ucm.fdi.pe.ConfigPanel.InnerOption;
import es.ucm.fdi.pe.ConfigPanel.IntegerOption;
import es.ucm.fdi.pe.ConfigPanel.StrategyOption;
import es.ucm.fdi.pe.Demo.Circulo;
import es.ucm.fdi.pe.Demo.Figura;
import es.ucm.fdi.pe.Demo.Forma;
import es.ucm.fdi.pe.Demo.Punto;
import es.ucm.fdi.pe.Demo.Rectangulo;
import interfaz.ConfigPanel;

/**
 * Interfaz grafica.
 * 
 * librerias utilizadas: ConfigPanel
 */
public class InterfazGrafica extends JFrame
{
	private static final long serialVersionUID = 1L;

	/* Controlador & Parametros */
	private Controlador controlador;
	private Parametros parametros;
	
	/* Definicion de constantes */
	private static final int FRAME_WEIGHT = 600;
	private static final int FRAME_HEIGHT = 480;
	
	/* Graphic components */
	private JPanel panelPrincipal; //FIXME: renombrar, no es "principal"
	private final ConfigPanel<Parametros> panelFormulario; //FIXME: por que el final
	private JButton boton;
	private JLabel labelPoblacion;
	private JTextField formPoblacion;
	
	public InterfazGrafica(Controlador c) 
	{
		super("Programación Evolutiva - Practica 1");
		this.controlador = c;
		this.parametros = new Parametros();
		propiedadesBasicas();
		
		setLayout(new BorderLayout());
			// 1) Panel derecho
			panelPrincipal = obtenerPanelPrincipal();
			add(panelPrincipal, BorderLayout.EAST);
		
			// 2) Panel izquierdo: crear un formulario y asociarlo con el objeto <parametros>
			final ConfigPanel<Parametros> formulario = crearFormulario();
			formulario.setTarget(parametros);
			formulario.initialize();		
			add(formulario, BorderLayout.WEST);
		
	}
	
	private JPanel obtenerPanelPrincipal()
	{
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(2,2));
		
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
	
	public ConfigPanel<Parametros> creaPanelConfiguracion() 
	{
		ConfigPanel<Parametros> config = new ConfigPanel<Parametros>();
		
		// new IntegerOption<T>(String texto, String tooltip, String campo, MIN_VALUE, MAX_VALUE)
		config.addOption(new IntegerOption<Parametros>()                   
				// elecciones (deben implementar Cloneable)
				
			  // para cada clase de objeto interno, hay que definir sus opciones entre un beginInner y un endInner 
			  .beginInner(new InnerOption<Figura,Forma>(  
			  	"circulo",							 // titulo del sub-panel
			  	"opciones del circulo",				 // tooltip asociado
			  	"forma",							 // campo
			  	Circulo.class))						 // tipo que debe de tener ese campo para que se active el sub-panel
		  		  .addInner(new DoubleOption<Forma>(
		  		     "radio", "radio del circulo", "radio", 0, Integer.MAX_VALUE))
		  		  .endInner()						 // cierra este sub-panel
		  	  // igual, pero opciones para el caso 'forma de tipo rectangulo'  
              .beginInner(new InnerOption<Figura,Forma>( 
			  	"rectangulo", "opciones del rectangulo", "forma", Rectangulo.class))
		  		  .addInner(new DoubleOption<Forma>(
		  		     "ancho", "ancho del rectangulo", "ancho", 0, Double.POSITIVE_INFINITY))
		  		  .addInner(new DoubleOption<Forma>(
		  		     "alto", "alto del rectangulo", "alto", 0, Double.POSITIVE_INFINITY))
		  		  .endInner()

			  // y por ultimo, el punto (siempre estara visible)
			  .beginInner(new InnerOption<Figura,Punto>(
			  	"coordenadas", "coordenadas de la figura", "coordenadas", Punto.class))
		  		  .addInner(new DoubleOption<Forma>(
		  		     "x", "coordenada x", "x", Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY))
		  		  .addInner(new DoubleOption<Forma>(
		  		     "y", "coordenada y", "y", Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY))
		  		  .endInner()
		  		  
			  // y ahora ya cerramos el formulario
		  	  .endOptions();
		
		return config;
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
		return parametros;
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
