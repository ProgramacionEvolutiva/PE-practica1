package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.math.plot.Plot2DPanel;

import controlador.Controlador;


/**
 * Interfaz grafica.
 * 
 */
public class InterfazGrafica extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static final int LINEAS_CABECERA = 2;
	
	/* Controlador & Parametros  + Info de las tablas */
	private Controlador controlador;
	private Parametros parametros;
	private Info[] info;
	
	/* Definicion de constantes */
	private static final int FRAME_WEIGHT = 800;
	private static final int FRAME_HEIGHT = 480;
	
	/* Componentes graficos */
	
	// menu
	private JMenuBar menu;
	private JMenu menuArchivo;
		private JMenuItem itemNuevo;
		private JMenuItem itemSalir;
	private JMenuItem menuAlgoritmo;
		
	// paneles
	private JPanel panelPrincipal; 
	// Izquierda
	private JTabbedPane panelSelector;	
	private JPanel panelFormulario;
		
		private JPanel panelPoblacion;
			private JLabel labelPoblacion;
			private JTextField formPoblacion;
		private JPanel panelPoblacionInvalido;
			private JLabel labelPoblacionInvalido;
		
		private JPanel panelGeneraciones;
			private JLabel labelGeneraciones;
			private JTextField formGeneraciones;
		private JPanel panelGeneracionesInvalido;
			private JLabel labelgeneracionesInvalido;
		
		private JPanel panelCruce;
			private JLabel labelCruce;
			private JTextField formCruce;
		private JPanel panelCruceInvalido;
			private JLabel labelCruceInvalido;
		
		private JPanel panelMutacion;
			private JLabel labelMutacion;
			private JTextField formMutacion;
		private JPanel panelMutacionesInvalido;
			private JLabel labelMutacionesInvalido;
		
		private JPanel panelTolerancia;
			private JLabel labelTolerancia;
			private JTextField formTolerancia;
		private JPanel panelToleranciaInvalido;
			private JLabel labelToleranciaInvalido;
		
		private JPanel panelFuncion;
			private JLabel labelFuncion;
			private JComboBox formFuncion;
		private JPanel panelSeleccion;
			private JLabel labelSeleccion;
			private JComboBox formSeleccion;
		/*
		private JPanel panelCargar;
			private JButton botonCargar;
			private JButton botonLimpiar;
		*/	
		private JPanel panelInfoCromosomas;
			private JPanel panelLista; // FIXME: JScrollPane
			private JList listaPicos;
			private JPanel panelDetalles;
			private JTextArea areaInfo;
				
	// Derecha	
	private Plot2DPanel panelResultados; 

		
		
			
	public InterfazGrafica(Controlador c) 
	{
		this.controlador = c;
		this.parametros = new Parametros();
		this.info = null;
		
		this.setContentPane(obtenerPanelPrincipal());
		crearBarraMenu();
		propiedadesBasicas();
	}
	
	private void crearBarraMenu()
	{
		menu = new JMenuBar();
		setJMenuBar(menu);

		menuArchivo = new JMenu("Archivo");
		itemNuevo = new JMenuItem("Nuevo");
		itemNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetearCampos();	
			}
		});
		menuArchivo.add(itemNuevo);
		itemSalir = new JMenuItem("Salir");
		itemSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuArchivo.add(itemSalir);
		
		menuAlgoritmo = new JMenuItem("Algoritmo genético");
		menuAlgoritmo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//XXX
				if (validarCampos()) {
					recogerParametros();
					controlador.lanzaGenetico(); 
				} else {
					JOptionPane.showMessageDialog(null, "Algunos campos no son válidos");
				}
			}
		});
		
		menu.add(menuArchivo);
		menu.add(menuAlgoritmo);
	}
	
	public JPanel obtenerPanelPrincipal()
	{
		panelPrincipal = new JPanel();
		
		// 1) panel izquierdo: tabbed pane
		panelSelector = new JTabbedPane(JTabbedPane.TOP);
		obtenerFormulario();
		obtenerInfoCromosomas();
		
		// 2) Panel derecho: resultados
		panelResultados = new Plot2DPanel();
		
		// 3) insertar los paneles
		panelPrincipal.setLayout(new GridLayout(0, 2, 0, 0));	
		panelPrincipal.add(panelSelector);
		panelPrincipal.add(panelResultados);
		
		return panelPrincipal;
	}

	/**
	 * Crea el panel formulario usando como layout GridLayout(7 filas)
	 *  
	 * @return panel
	 */
	private void obtenerFormulario()
	{
		panelFormulario = new JPanel();
		panelSelector.addTab("Parametros", null, panelFormulario, null);
		panelFormulario.setLayout(new GridLayout(12, 1, 0, 0));
			
		// 1) panelPoblacion
			panelPoblacion = new JPanel();
			panelPoblacion.setLayout(new GridLayout(1,0,0,0)); 
			labelPoblacion = new JLabel("  Poblacion: ");
			panelPoblacion.add(labelPoblacion);
			panelPoblacion.add(new JPanel()); // hueco intermedio
			formPoblacion = new JTextField(String.valueOf(Parametros.TAM_POBLACION_DEFECTO));
			panelPoblacion.add(formPoblacion);
		panelFormulario.add(panelPoblacion);
		
		// 2) panelPoblacionInvalido
			panelPoblacionInvalido = new JPanel();
			panelPoblacionInvalido.setLayout(new GridLayout(1,2,0,0));
			panelPoblacionInvalido.add(new JPanel()); //hueco intermedio
			labelPoblacionInvalido = new JLabel("Parámetro población incorrecto");
			labelPoblacionInvalido.setForeground(Color.RED);
			labelPoblacionInvalido.setFont(new Font("Lucida Grande", Font.BOLD, 11));
			labelPoblacionInvalido.setVisible(false);
			panelPoblacionInvalido.add(labelPoblacionInvalido);
		panelFormulario.add(panelPoblacionInvalido);
			
			
		// 3) panelGeneraciones
			panelGeneraciones = new JPanel();
			panelGeneraciones.setLayout(new GridLayout(1, 0, 0, 0));
			labelGeneraciones = new JLabel("  Generaciones: ");
			panelGeneraciones.add(labelGeneraciones);
			panelGeneraciones.add(new JPanel()); // hueco intermedio
			formGeneraciones = new JTextField(String.valueOf(Parametros.NUM_GENERACIONES_DEFECTO));
			panelGeneraciones.add(formGeneraciones);
		panelFormulario.add(panelGeneraciones);
		
		// 4) panelGeneracionesInvalido
			panelGeneracionesInvalido = new JPanel();
			panelGeneracionesInvalido.setLayout(new GridLayout(1,2,0,0));
			panelGeneracionesInvalido.add(new JPanel()); // hueco intermedio
			labelgeneracionesInvalido = new JLabel("Parámetro generaciones inválido");
			labelgeneracionesInvalido.setForeground(Color.RED);
			labelgeneracionesInvalido.setFont(new Font("Lucida Grande", Font.BOLD, 11));
			labelgeneracionesInvalido.setVisible(false);
			panelGeneracionesInvalido.add(labelgeneracionesInvalido);
		panelFormulario.add(panelGeneracionesInvalido);
		
		// 5) panelCruce
			panelCruce = new JPanel();
			panelCruce.setLayout(new GridLayout(1, 0, 0, 0));
			labelCruce = new JLabel("  Prob. Cruce: ");
			panelCruce.add(labelCruce);
			panelCruce.add(new JPanel()); // hueco intermedio
			formCruce = new JTextField(String.valueOf(Parametros.PROB_CRUCE_DEFECTO));
			panelCruce.add(formCruce);
		panelFormulario.add(panelCruce);
		
		// 6) panelCruceInvalido
			panelCruceInvalido = new JPanel();
			panelCruceInvalido.setLayout(new GridLayout(1,2,0,0));
			panelCruceInvalido.add(new JPanel()); // hueco intermedio
			labelCruceInvalido = new JLabel("Parámetro Cruce inválido");
			labelCruceInvalido.setForeground(Color.RED);
			labelCruceInvalido.setFont(new Font("Lucida Grande", Font.BOLD, 11));
			labelCruceInvalido.setVisible(false);
			panelCruceInvalido.add(labelCruceInvalido);
		panelFormulario.add(panelCruceInvalido);
		
		// 7) panelMutacion
			panelMutacion = new JPanel();
			panelMutacion.setLayout(new GridLayout(1, 0, 0, 0));
			labelMutacion = new JLabel("  Prob. Mutacion: ");
			panelMutacion.add(labelMutacion);
			panelMutacion.add(new JPanel()); // hueco intermedio
			formMutacion = new JTextField(String.valueOf(Parametros.PROB_MUTACION_DEFECTO));
			panelMutacion.add(formMutacion);
		panelFormulario.add(panelMutacion);
		
		// 8) panelMutacionesInvalido
			panelMutacionesInvalido = new JPanel();
			panelMutacionesInvalido.setLayout(new GridLayout(1,2,0,0));
			panelMutacionesInvalido.add(new JPanel()); // hueco intermedio
			labelMutacionesInvalido = new JLabel("Parámetro mutaciones inválido");
			labelMutacionesInvalido.setForeground(Color.RED);
			labelMutacionesInvalido.setFont(new Font("Lucida Grande", Font.BOLD, 11));
			labelMutacionesInvalido.setVisible(false);
			panelMutacionesInvalido.add(labelMutacionesInvalido);
		panelFormulario.add(panelMutacionesInvalido);
		
		// 9) panelTolerancia
			panelTolerancia = new JPanel();
			panelTolerancia.setLayout(new GridLayout(1, 0, 0, 0));
			labelTolerancia = new JLabel("  Tolerancia: ");
			panelTolerancia.add(labelTolerancia);
			panelTolerancia.add(new JPanel()); // hueco intermedio
			formTolerancia = new JTextField(String.valueOf(Parametros.TOLERANCIA_DEFECTO));
			panelTolerancia.add(formTolerancia);
		panelFormulario.add(panelTolerancia);
		
		// 10) panelToleranciaInvalido
			panelToleranciaInvalido = new JPanel();
			panelToleranciaInvalido.setLayout(new GridLayout(1,2,0,0));
			panelToleranciaInvalido.add(new JPanel()); // hueco intermedio
			labelToleranciaInvalido = new JLabel("Parámetro Tolerancia inválido");
			labelToleranciaInvalido.setForeground(Color.RED);
			labelToleranciaInvalido.setFont(new Font("Lucida Grande", Font.BOLD, 11));
			labelToleranciaInvalido.setVisible(false);
			panelToleranciaInvalido.add(labelToleranciaInvalido);
		panelFormulario.add(panelToleranciaInvalido);
		
		// 11) panelFuncion
			panelFuncion = new JPanel();
			panelFuncion.setLayout(new GridLayout(1, 0, 0, 0));
			labelFuncion = new JLabel("  Función: ");
			panelFuncion.add(labelFuncion);
			panelFuncion.add(new JPanel()); // hueco intermedio
			formFuncion = new JComboBox();
			formFuncion.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
			panelFuncion.add(formFuncion);
		panelFormulario.add(panelFuncion);
		
		// 12) panelSeleccion
			panelSeleccion = new JPanel();
			panelSeleccion.setLayout(new GridLayout(1, 0, 0, 0));
			labelSeleccion = new JLabel("  Selección: ");
			panelSeleccion.add(labelSeleccion);
			panelSeleccion.add(new JPanel()); // hueco intermedio
			formSeleccion = new JComboBox();
			formSeleccion.setModel(new DefaultComboBoxModel(new String[] {"Torneo", "Ruleta"}));
			formSeleccion.setSelectedIndex(1);
			panelSeleccion.add(formSeleccion);
		panelFormulario.add(panelSeleccion);
		
		/*
		// 13) panelCargar
			panelCargar = new JPanel();
			panelCargar.setLayout(new GridLayout(1, 3, 0, 0));
				botonLimpiar = new JButton("Limpiar");
				botonLimpiar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						resetearCampos();
					}
				});
			panelCargar.add(botonLimpiar);
			panelCargar.add(new JPanel()); // hueco intermedio
				botonCargar = new JButton("OK");
				botonCargar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (validarCampos()) {
							recogerParametros();
							controlador.lanzaGenetico();
						} else {
							JOptionPane.showMessageDialog(null, "Algunos campos no son válidos");
						}
					}
				});
			panelCargar.add(botonCargar);
		panelFormulario.add(panelCargar);
		*/
	}
	
	private void obtenerInfoCromosomas()
	{
		panelInfoCromosomas = new JPanel();
		panelInfoCromosomas.setLayout(new GridLayout(2, 1, 0, 0));
		
		// 1) Lista
		panelLista = new JPanel();
		panelLista.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panelLista.setLayout(new GridLayout(0, 1, 0, 0));
		listaPicos = new JList();
		listaPicos.setSelectedIndex(2); //FIXME: No funciona
		listaPicos.setLayoutOrientation(JList.VERTICAL);
		listaPicos.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mostrarDetallesCromosoma(e.getFirstIndex(), e.getLastIndex());
			}
		});
		panelLista.add(listaPicos);
		
		// 2) Detalles
		panelDetalles = new JPanel();
		panelDetalles.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panelDetalles.setLayout(new GridLayout(1, 0, 0, 0));
		areaInfo = new JTextArea();
		areaInfo.setEditable(false);
		panelDetalles.add(areaInfo);
		
		// 3) add
		panelInfoCromosomas.add(panelLista);
		panelInfoCromosomas.add(panelDetalles);
		panelSelector.addTab("Detalles", null, panelInfoCromosomas, null);
	}
	
	private void obtenerResultados(double[] aptitudesMejores, double[] gokusMejores, double[] medias)
	{
		// borrar la grafica anterior
		panelResultados.removeAllPlots();
		// definir los datos
		double[] x = new double[parametros.getNumGeneraciones()];
		for(int i=0;i<parametros.getNumGeneraciones();i++){
			x[i]=i;
		}
		// definir la layenda de datos
		panelResultados.addLegend("SOUTH");
		// dibujar la grafica
		panelResultados.addLinePlot("Cromosoma mejor de cada generaciÛn", x, aptitudesMejores);
		panelResultados.addLinePlot("Cromosoma mejor encontrado", x, gokusMejores);
		panelResultados.addLinePlot("Media de cada generaciÛn", x, medias);
	}
	
	private void obtenerTabla()
	{
		String[] datos = new String[info.length + LINEAS_CABECERA];
		datos[0] = new String("Lista de picos");
		datos[1] = new String("==============");
		
		for(int i = 0; i < info.length; i++) {
			datos[i + LINEAS_CABECERA] = new String(
					"generacion: " + info[i].generacion +
					"\t\t fenotipo: " + String.valueOf(info[i].aptitud)
					);
		}
		
		listaPicos.setListData(datos);
	}
	
	private void recogerParametros()
	{
		this.parametros.setTamPoblacion(Integer.parseInt(formPoblacion.getText()));
		this.parametros.setNumGeneraciones(Integer.parseInt(formGeneraciones.getText()));
		this.parametros.setProbCruce(Double.parseDouble(formCruce.getText()));
		this.parametros.setProbMutacion(Double.parseDouble(formMutacion.getText()));
		this.parametros.setTolerancia(Double.parseDouble(formTolerancia.getText()));
		this.parametros.setFuncion(formFuncion.getSelectedIndex() + 1);
		this.parametros.setSeleccion(formSeleccion.getSelectedIndex());
	}
	
	private void resetearCampos()
	{
		this.formPoblacion.setText(String.valueOf(Parametros.TAM_POBLACION_DEFECTO));
		this.formGeneraciones.setText(String.valueOf(Parametros.NUM_GENERACIONES_DEFECTO));
		this.formCruce.setText(String.valueOf(Parametros.PROB_CRUCE_DEFECTO));
		this.formMutacion.setText(String.valueOf(Parametros.PROB_MUTACION_DEFECTO));
		this.formTolerancia.setText(String.valueOf(Parametros.TOLERANCIA_DEFECTO));
		this.formFuncion.setSelectedIndex(0);
		this.formSeleccion.setSelectedIndex(1);
		
		this.panelResultados.removeAllPlots();
	}
	
	private void mostrarDetallesCromosoma(int primerIndice, int ultimoIndice)
	{
		//TODO: Captar las lineas de cabecera
		// FIXME: no funcionan bien los indices. 
		String s = "";
		for (int i = primerIndice; i <= ultimoIndice; i++) {
			s += 	"Cromosoma: " + info[i-LINEAS_CABECERA].cadena + "\n" + 
					"Representa: " + info[i-LINEAS_CABECERA].fenotipo + "\n" + 
					"Aptitud: " + info[i-LINEAS_CABECERA].aptitud + "\n" +
					"----------------------------------------------------- \n";
		}
		areaInfo.setText(s);
	}
	
	private void propiedadesBasicas()
	{
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, FRAME_WEIGHT, FRAME_HEIGHT);
		setResizable(false);
	}
	
	private boolean validarCampos()
	{
		boolean camposValidos = true;
		
		if ( !esNumeroNatural(formPoblacion.getText()) ){
			labelPoblacionInvalido.setVisible(true);
			camposValidos = false;
		} else {
			labelPoblacionInvalido.setVisible(false);
		}
		
		if ( !esNumeroNatural(formGeneraciones.getText()) ){
			labelgeneracionesInvalido.setVisible(true);
			camposValidos = false;
		} else {
			labelgeneracionesInvalido.setVisible(false);
		}
		
		if ( !esNumeroReal(formCruce.getText()) ){
			labelCruceInvalido.setVisible(true);
			camposValidos = false;
		} else {
			labelCruceInvalido.setVisible(false);
		}
		
		if ( !esNumeroReal(formMutacion.getText()) ){
			labelMutacionesInvalido.setVisible(true);
			camposValidos = false;
		} else {
			labelMutacionesInvalido.setVisible(false);
		}
		
		if ( !esNumeroReal(formTolerancia.getText()) ){
			labelToleranciaInvalido.setVisible(true);
			camposValidos = false;
		} else {
			labelToleranciaInvalido.setVisible(false);
		}
		
		return camposValidos;
	}
	
	private boolean esNumeroNatural(String s)
	{
	    for (char c : s.toCharArray()){
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	/*
	 * Comprueba que es un numero real y con el formato 0.X
	 */
	private boolean esNumeroReal(String s)  
	{  
	  char[] cadena = s.toCharArray();
	  // Formato 0.X
	  if ( (cadena[0] != '0') || (cadena[1] != '.') ) {
		  return false;
	  }
	  // digitos
	  for (int i = 2; i < cadena.length; i++){
	        if (!Character.isDigit(cadena[i])) {
	        	return false;
	        }
	  }
	  return true;  
	}
	
	public Parametros getParametros(){ return parametros; }
	
	/**
	 * TODO: hacer el javadoc
	 * @param aptitudesMejores
	 * @param gokusMejores
	 * @param picos
	 */
	public void mostrar( double[] aptitudesMejores, double[] gokusMejores, double[] medias, Info[] infoMostrar)
	{
		// XXX
		this.info = infoMostrar;
		obtenerResultados(aptitudesMejores,gokusMejores, medias);
		obtenerTabla();
	}
}
