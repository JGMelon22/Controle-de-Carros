package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import impl.CarroDaoImpl;
import model.Carro;

public class App {

	private JFrame frmControleDeEstacionamento;
	private JTable dataTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					App window = new App();
					window.frmControleDeEstacionamento.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 *
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public App() throws ClassNotFoundException, SQLException {
		initialize();

		// Coloca um ícone costumizado para a aplicação
		Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/logo.png"));
		frmControleDeEstacionamento.setIconImage(img);
	}

	/**
	 * Initialize the contents of the frame.
	 *
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

		frmControleDeEstacionamento = new JFrame();
		frmControleDeEstacionamento.setTitle("Controle de Estacionamento");
		frmControleDeEstacionamento.setBounds(100, 100, 1280, 720);
		frmControleDeEstacionamento.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frmControleDeEstacionamento.setResizable(false);
		frmControleDeEstacionamento.setLocationRelativeTo(null);

		JLayeredPane layeredPane = new JLayeredPane();
		frmControleDeEstacionamento.getContentPane().add(layeredPane, BorderLayout.CENTER);

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

		JFormattedTextField marcaFormattedTextField = new JFormattedTextField();

		marcaFormattedTextField.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		marcaFormattedTextField.setBounds(12, 31, 104, 27);
		layeredPane.add(marcaFormattedTextField);

		JFormattedTextField placaFormattedTextField = new JFormattedTextField();
		placaFormattedTextField.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		placaFormattedTextField.setBounds(138, 31, 104, 27);
		layeredPane.add(placaFormattedTextField);

		JFormattedTextField corFormattedTextField = new JFormattedTextField();
		corFormattedTextField.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		corFormattedTextField.setBounds(264, 31, 104, 27);
		layeredPane.add(corFormattedTextField);

		JFormattedTextField horaEntradaFormattedTextField = new JFormattedTextField();
		horaEntradaFormattedTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		horaEntradaFormattedTextField.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		horaEntradaFormattedTextField.setBounds(385, 31, 104, 27);
		layeredPane.add(horaEntradaFormattedTextField);

		JFormattedTextField horaSaidaFormattedTextField = new JFormattedTextField();
		horaSaidaFormattedTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		horaSaidaFormattedTextField.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		horaSaidaFormattedTextField.setBounds(511, 31, 104, 27);
		layeredPane.add(horaSaidaFormattedTextField);
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
