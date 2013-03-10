package interfaz;

import interfaz.ConfigPanel.IntegerOption;

import java.awt.BorderLayout;
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
	private JPanel panelPrincipal; 
	private JPanel panelResutlados;
	private ConfigPanel<Parametros> panelFormulario; //FIXME: en el ejemplo es final
	
	private JButton boton;
	private JLabel labelPoblacion;
	private JTextField formPoblacion;
	
	public InterfazGrafica(Controlador c) 
	{
		super("Programacion Evolutiva - Practica 1");
		this.controlador = c;
		this.parametros = new Parametros();
		propiedadesBasicas();
		this.setContentPane(obtenerPanelPrincipal());
	}
	
	public JPanel obtenerPanelPrincipal()
	{
		panelPrincipal = new JPanel(new GridLayout(2,2));
		
		// 1) Panel derecho
		panelResutlados = obtenerPanelResultados();
		// 2) Panel izquierdo: crear un formulario y asociarlo con el objeto <parametros>
		panelFormulario = crearFormulario();
		panelFormulario.setTarget(parametros);
		panelFormulario.initialize();		
		
		panelPrincipal.add(panelFormulario);
		panelPrincipal.add(panelResutlados);
		return panelPrincipal;
	}

	private JPanel obtenerPanelResultados()
	{
		panelResutlados = new JPanel();
		panelResutlados.setLayout(new BorderLayout());
		
		labelPoblacion = new JLabel("Poblacion: ");
		panelResutlados.add(labelPoblacion, "North");
		formPoblacion = new JTextField("100");
		panelResutlados.add(formPoblacion, "Center");
		
		boton = new JButton("OK");
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recogerParametros();
				controlador.lanzaGenetico();
			}
		});
		panelResutlados.add(boton, "South");
		
		return panelResutlados;
	}
	
	private ConfigPanel<Parametros> crearFormulario()
	{
		ConfigPanel<Parametros> formulario = new ConfigPanel<Parametros>();
		
		/* argumentos del metodo <addOption>
		 *  etiqueta del campo
		 *  texto al pasar el raton
		 *  campo de Parametros
		 *  min y max (se puede usar Integer.MIN_VALUE/MAX_VALUE)
		 */
		formulario.addOption(new IntegerOption<Parametros>(
				"Tam de la poblacion", "poblacion", "tamPoblacion", 1, 200));
		formulario.endOptions();
		
		return formulario;
		
	}
	
	private void recogerParametros()
	{
		this.parametros.setTamPoblacion(Integer.parseInt(formPoblacion.getText()));
		// TODO: recoger todos los parametros
	}
	
	private void propiedadesBasicas()
	{
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, FRAME_WEIGHT, FRAME_HEIGHT);
		setResizable(false);
	}
	
	public Parametros getParametros(){ return parametros; }
	
	/**
	 * 
	 * @param cromosoma a imprimir
	 */
	public void mostrar(Cromosoma cromosoma){
		// TODO: mostrar por ventana y no por consola
		System.out.println("Solucion => " + cromosoma.toString() + " Fenotipo =>" + cromosoma.getFenotipo() +
				" Aptitud =>" + cromosoma.getAptitud());
	}
}
