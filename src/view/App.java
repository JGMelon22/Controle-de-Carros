package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import impl.CarroDaoImpl;
import model.Carro;

public class App {

	private JFrame frame;
	private JTextField marcaTextField;
	private JTextField placaTextField;
	private JTextField corTextField;
	private JTextField horaEntradaTextField;
	private JTextField horaSaidaTextField;
	private JTable dataTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public App() throws ClassNotFoundException, SQLException {
		initialize();

		// Coloca um ícone costumizado para a aplicação
		Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/logo.png"));
		frame.setIconImage(img);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, SQLException {
		try {

			// Obtem o nome do SO e ajusta o tema para um melhor look and feel
			String os = System.getProperty("os.name").toLowerCase();

			if (os.contains("win")) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} else if (os.contains("nix") || os.contains("nux")) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			} else if (os.contains("mac")) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			}

		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);

		marcaTextField = new JTextField();
		marcaTextField.setBounds(12, 27, 114, 19);
		layeredPane.add(marcaTextField);
		marcaTextField.setColumns(10);

		placaTextField = new JTextField();
		placaTextField.setColumns(10);
		placaTextField.setBounds(138, 27, 114, 19);
		layeredPane.add(placaTextField);

		corTextField = new JTextField();
		corTextField.setColumns(10);
		corTextField.setBounds(264, 27, 114, 19);
		layeredPane.add(corTextField);

		horaEntradaTextField = new JTextField();
		horaEntradaTextField.setColumns(10);
		horaEntradaTextField.setBounds(385, 27, 114, 19);
		layeredPane.add(horaEntradaTextField);

		horaSaidaTextField = new JTextField();
		horaSaidaTextField.setColumns(10);
		horaSaidaTextField.setBounds(511, 28, 114, 19);
		layeredPane.add(horaSaidaTextField);

		JLabel lblNewLabel = new JLabel("Marca");
		lblNewLabel.setBounds(12, 12, 70, 15);
		layeredPane.add(lblNewLabel);

		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(138, 12, 70, 15);
		layeredPane.add(lblPlaca);

		JLabel lblCor = new JLabel("Cor");
		lblCor.setBounds(264, 12, 70, 15);
		layeredPane.add(lblCor);

		JLabel lblHoraEntrada = new JLabel("Hora Entrada");
		lblHoraEntrada.setBounds(385, 12, 114, 15);
		layeredPane.add(lblHoraEntrada);

		JLabel lblHoraSada = new JLabel("Hora Saída");
		lblHoraSada.setBounds(511, 12, 114, 15);
		layeredPane.add(lblHoraSada);

		JButton adicionarBtnNewButton = new JButton("Adicionar");
		adicionarBtnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		adicionarBtnNewButton.setToolTipText("Adicionar um novo veículo");
		adicionarBtnNewButton.setBounds(9, 83, 117, 25);
		layeredPane.add(adicionarBtnNewButton);

		JButton atualizarBtnNewButton = new JButton("Atualizar");
		atualizarBtnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		atualizarBtnNewButton.setToolTipText("Atualizar um veículo");
		atualizarBtnNewButton.setBounds(138, 83, 117, 25);
		layeredPane.add(atualizarBtnNewButton);

		JButton excluirBtnNewButton = new JButton("Excluir");
		excluirBtnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		excluirBtnNewButton.setToolTipText("Excluir um veículo");
		excluirBtnNewButton.setBounds(264, 83, 117, 25);
		layeredPane.add(excluirBtnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 120, 1246, 523);
		layeredPane.add(scrollPane);

		dataTable = new JTable();
		scrollPane.setViewportView(dataTable);
		dataTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Marca", "Cor", "Placa", "Hora Entrada", "Hora Saída" }));
		dataTable.getColumnModel().getColumn(0).setPreferredWidth(105);
		dataTable.getColumnModel().getColumn(1).setPreferredWidth(98);
		
		loadInitialData();
	}
	
	public void loadInitialData() throws ClassNotFoundException, SQLException {
		DefaultTableModel tableModel = (DefaultTableModel) dataTable.getModel();
		tableModel.setRowCount(0); 

		CarroDaoImpl carroDao = new CarroDaoImpl();
		List<Carro> carros = carroDao.getAllCarros();

		for (Carro carro : carros) {
			Object[] rowData = { carro.getId(), carro.getMarca(), carro.getCor(), carro.getPlaca(),
					carro.getHoraEntrada(), carro.getHoraSaida() };
			tableModel.addRow(rowData);
		}
	}
}
