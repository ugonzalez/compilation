package py.una.compilation;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Interfaz gráfica del programa.
 * 
 * @author Uriel González
 * 
 */
public class Ventana {

	private JFrame ventana;
	private JPanel panelSuperior;
	private JPanel panelSuperiorIzq;
	private JPanel panelSuperiorDer;
	private JPanel panelCentro;
	private JPanel panelInferior;
	private JLabel expresionTitulo;
	private JTextField expresionTexto;
	private JComboBox<String> alfabetoCombo;
	private JButton procesarBoton;
	private JLabel alfabetoTitulo1;
	private JLabel alfabetoTitulo2;
	private JTextField alfabetoNombre;
	private JTextField alfabetoSimbolos;
	private JButton alfabetoBoton;
	private JList<Alfabeto> alfabetoLista;
	private DefaultListModel<Alfabeto> modeloLista;
	private DefaultComboBoxModel<String> modeloCombo;
	private List<Alfabeto> alfabetos;

	private JTextArea nfaArea;
	private JTextArea dfaArea;
	private JTextArea dfaMinArea;

	private JTabbedPane tabbedPane;
	private JScrollPane scroll1;
	private JScrollPane scroll2;
	private JScrollPane scroll3;

	/**
	 * Construye la interfaz gráfica especificando una lista de alfabetos.
	 * 
	 * @param alfabetoList
	 *            Alfabetos predeterminados.
	 */
	public Ventana(final List<Alfabeto> alfabetoList) {

		this.alfabetos = alfabetoList;

		this.ventana = new JFrame("Compiladores - Uriel Gonzalez");
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.ventana.setLocationRelativeTo(null); // centra la ventana
		this.ventana.setLayout(new GridBagLayout());
		this.panelSuperior = new JPanel();
		this.panelSuperior.setLayout(new FlowLayout());
		this.panelSuperior.setBorder(BorderFactory
				.createTitledBorder("Editor de alfabetos"));
		this.panelSuperiorIzq = new JPanel();
		this.panelSuperiorIzq.setLayout(new GridLayout(0, 2));
		this.panelSuperiorDer = new JPanel();
		this.panelSuperiorDer.setLayout(new FlowLayout());
		this.panelSuperior.add(this.panelSuperiorIzq);
		this.panelSuperior.add(this.panelSuperiorDer);
		this.panelCentro = new JPanel();
		this.panelCentro.setLayout(new FlowLayout());
		this.panelInferior = new JPanel();
		this.panelInferior.setLayout(new FlowLayout());

		this.alfabetoTitulo1 = new JLabel("Nombre del alfabeto:");
		this.panelSuperiorIzq.add(this.alfabetoTitulo1);

		this.alfabetoNombre = new JTextField(10);
		this.panelSuperiorIzq.add(this.alfabetoNombre);

		this.alfabetoTitulo2 = new JLabel("Simbolos:");
		this.panelSuperiorIzq.add(this.alfabetoTitulo2);

		this.alfabetoSimbolos = new JTextField(10);
		this.panelSuperiorIzq.add(this.alfabetoSimbolos);

		this.alfabetoBoton = new JButton("Crear");
		this.alfabetoBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final Alfabeto alfabeto = new Alfabeto();
				if (Ventana.this.alfabetoNombre.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Se debe especificar un nombre para el alfabeto",
							"Compiladores", JOptionPane.ERROR_MESSAGE);
					return;
				}
				alfabeto.setNombre(Ventana.this.alfabetoNombre.getText());
				for (final Alfabeto alfabetoExistente : Ventana.this.alfabetos) {
					if (alfabetoExistente.getNombre().equals(
							alfabeto.getNombre())) {
						JOptionPane
								.showMessageDialog(
										null,
										"Existe un alfabeto con el mismo nombre creado previamente",
										"Compiladores",
										JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (Ventana.this.alfabetoSimbolos.getText().isEmpty()) {
					JOptionPane
							.showMessageDialog(
									null,
									"Se debe especificar por lo menos un símbolo para el alfabeto",
									"Compiladores", JOptionPane.ERROR_MESSAGE);
					return;
				}
				final String simbolos = Ventana.this.alfabetoSimbolos.getText();
				for (int i = 0; i < simbolos.length(); i++) {
					alfabeto.addSimbolo(new Simbolo(String.valueOf(simbolos
							.charAt(i))));
				}
				Ventana.this.modeloLista.addElement(alfabeto);
				Ventana.this.modeloCombo.addElement(alfabeto.getNombre());
				Ventana.this.alfabetos.add(alfabeto);
				Ventana.this.alfabetoNombre.setText("");
				Ventana.this.alfabetoSimbolos.setText("");
				JOptionPane.showMessageDialog(null,
						"Alfabeto " + alfabeto.getNombre() + " creado!",
						"Compiladores", JOptionPane.INFORMATION_MESSAGE);
			}

		});
		this.panelSuperiorIzq.add(this.alfabetoBoton);

		this.modeloLista = new DefaultListModel<Alfabeto>();
		for (final Alfabeto alfabeto : this.alfabetos) {
			this.modeloLista.addElement(alfabeto);
		}

		this.alfabetoLista = new JList<Alfabeto>(this.modeloLista);
		this.panelSuperiorDer.add(this.alfabetoLista);

		this.expresionTitulo = new JLabel("Expresión regular:");
		this.panelCentro.add(this.expresionTitulo);
		this.expresionTexto = new JTextField(30);
		this.panelCentro.add(this.expresionTexto);
		this.modeloCombo = new DefaultComboBoxModel<String>();
		for (final Alfabeto alfabeto : this.alfabetos) {
			this.modeloCombo.addElement(alfabeto.getNombre());
		}
		this.alfabetoCombo = new JComboBox<String>(this.modeloCombo);
		this.panelCentro.add(this.alfabetoCombo);

		this.procesarBoton = new JButton("Procesar");
		this.procesarBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {

				final String nombreAlfabeto = Ventana.this.alfabetoCombo
						.getSelectedItem().toString();
				Alfabeto alfabeto = new Alfabeto();
				for (final Alfabeto temp : Ventana.this.alfabetos) {
					if (nombreAlfabeto.equals(temp.getNombre())) {
						alfabeto = temp;
						break;
					}

				}
				final String expresion = Ventana.this.expresionTexto.getText();

				final ExpresionRegular expr = new ExpresionRegular(expresion,
						alfabeto);
				if (expr.isValido()) {
					final AFN afn = expr.getAFN();
					Ventana.this.nfaArea.setText(afn.toString());
					final AFD afd = expr.getAFD();
					Ventana.this.dfaArea.setText(afd.toString());
					final AFD afdMin = expr.getAFDMin();
					Ventana.this.dfaMinArea.setText(afdMin.toString());
					final Generador generador = new Generador(expr);
					generador.generarValidador();
					final Grafico grafico = new Grafico(expr.getAFDMin());
					grafico.generarGrafico();
					JOptionPane.showMessageDialog(null,
							"AFN, AFD y AFD mínimo creados!", "Compiladores",
							JOptionPane.INFORMATION_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(null,
							"Expresión regular no válida", "Compiladores",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		this.panelCentro.add(this.procesarBoton);

		this.nfaArea = new JTextArea(50, 80);
		this.dfaArea = new JTextArea(50, 80);
		this.dfaMinArea = new JTextArea(50, 80);

		this.tabbedPane = new JTabbedPane();
		this.scroll1 = new JScrollPane(this.nfaArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.tabbedPane.add("NFA", this.scroll1);
		this.scroll2 = new JScrollPane(this.dfaArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.tabbedPane.add("DFA", this.scroll2);
		this.scroll3 = new JScrollPane(this.dfaMinArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.tabbedPane.add("DFA Mínimo", this.scroll3);

		this.panelInferior.add(this.tabbedPane);

		this.ventana.add(this.panelSuperior);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		this.ventana.add(this.panelCentro, constraints);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		this.ventana.add(this.panelInferior, constraints);

		this.ventana.setVisible(true);

	}

}
