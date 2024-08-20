package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import impl.CarroDaoImpl;
import model.Carro;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		excluirBtnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dataTable.getSelectedRow() != -1) {
					try {
						Carro carro = new Carro();
						CarroDaoImpl carroDao = new CarroDaoImpl();
						carro.setPlaca(((String) dataTable.getValueAt(dataTable.getSelectedRow(), 3)));
						carroDao.deleteCarro(carro);

						JOptionPane.showMessageDialog(frmControleDeEstacionamento,
								"Carro com a placa " + carro.getPlaca() + " foi apagado com sucesso!",
								"Mensagem de aviso", JOptionPane.INFORMATION_MESSAGE);

						loadInitialData();

					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(frmControleDeEstacionamento,
							"Nenhum veículo para exclusão foi selecionado", "Mensagem de aviso", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
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
		placaFormattedTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (Character.isLetter(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
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

		JButton limpartBtnNewButton = new JButton("Limpar");
		limpartBtnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marcaFormattedTextField.setText("");
				placaFormattedTextField.setText("");
				corFormattedTextField.setText("");
				horaEntradaFormattedTextField.setText("");
				horaSaidaFormattedTextField.setText("");
				
				placaFormattedTextField.setEnabled(true);
			}
		});
		limpartBtnNewButton.setToolTipText("Excluir um veículo");
		limpartBtnNewButton.setBounds(1141, 78, 117, 25);
		layeredPane.add(limpartBtnNewButton);

		// Insert new vehicle event
		adicionarBtnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String marcaText = marcaFormattedTextField.getText();
				String placaText = placaFormattedTextField.getText();
				String corText = corFormattedTextField.getText();
				String horaEntradaText = horaEntradaFormattedTextField.getText();
				String horaSaidaText = horaSaidaFormattedTextField.getText();

				if (marcaText != null && !marcaText.trim().isEmpty() && placaText != null && !placaText.trim().isEmpty()
						&& marcaText.length() != 7 && corText != null && !corText.trim().isEmpty()
						&& horaEntradaText != null && !horaEntradaText.trim().isEmpty()) {

					if (horaSaidaText == null || horaSaidaText.trim().isEmpty()) {
						horaSaidaFormattedTextField.setText("0");
						horaSaidaText = "0";
					}
					try {
						int horaEntrada = Integer.parseInt(horaEntradaText);
						int horaSaida = Integer.parseInt(horaSaidaText);

						Carro carro = new Carro();
						CarroDaoImpl carroDao = new CarroDaoImpl();

						carro.setMarca(marcaText);
						carro.setPlaca(placaText);
						carro.setCor(corText);
						carro.setHoraEntrada(horaEntrada);
						carro.setHoraSaida(horaSaida);

						carroDao.createCarro(carro);

						JOptionPane.showMessageDialog(frmControleDeEstacionamento, "Novo carro adicionado com êxito!",
								"Mensagem de aviso", JOptionPane.INFORMATION_MESSAGE);

						loadInitialData();

						marcaFormattedTextField.setText("");
						placaFormattedTextField.setText("");
						corFormattedTextField.setText("");
						horaEntradaFormattedTextField.setText("");
						horaSaidaFormattedTextField.setText("");

					} catch (ClassNotFoundException | SQLException ex) {
						JOptionPane.showMessageDialog(frmControleDeEstacionamento, "Erro ao adicionar o carro!",
								"Mensagem de erro", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(frmControleDeEstacionamento, "Campo vazio detectado!",
							"Mensagem de erro", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		dataTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (dataTable.getSelectedRow() != -1) {
					try {
						int selectedRow = dataTable.getSelectedRow();

						Carro carro = new Carro();
						CarroDaoImpl carroDao = new CarroDaoImpl();

						String placa = (String) dataTable.getValueAt(selectedRow, 3);
						carro.setPlaca(placa);

						Carro carFromDb = carroDao.getCarro(placa);

						marcaFormattedTextField.setText(carFromDb.getMarca());
						placaFormattedTextField.setText(carFromDb.getPlaca());

						// Regra de negócio: não pode-se atualizar a placa.
						// O correto é apagar o registro e
						placaFormattedTextField.setEnabled(false);

						corFormattedTextField.setText(carFromDb.getCor());
						horaEntradaFormattedTextField.setText(String.valueOf(carFromDb.getHoraEntrada()));
						horaSaidaFormattedTextField.setText(String.valueOf(carFromDb.getHoraSaida()));

					} catch (ClassNotFoundException ex) {
						JOptionPane.showMessageDialog(null, "Classe não encontrada: " + ex.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, "Erro de SQL: " + ex.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao carregar dados do carro: " + ex.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum carro selecionado. Selecione um carro na tabela.",
							"Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		atualizarBtnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String marcaText = marcaFormattedTextField.getText();
				String placaText = placaFormattedTextField.getText();
				String corText = corFormattedTextField.getText();
				String horaEntradaText = horaEntradaFormattedTextField.getText();
				String horaSaidaText = horaSaidaFormattedTextField.getText();

				if (marcaText != null && !marcaText.trim().isEmpty() && placaText != null && !placaText.trim().isEmpty()
						&& marcaText.length() != 7 && corText != null && !corText.trim().isEmpty()
						&& horaEntradaText != null && !horaEntradaText.trim().isEmpty()) {

					if (horaSaidaText == null || horaSaidaText.trim().isEmpty()) {
						horaSaidaFormattedTextField.setText("0");
						horaSaidaText = "0";
					}
					try {
						int horaEntrada = Integer.parseInt(horaEntradaText);
						int horaSaida = Integer.parseInt(horaSaidaText);

						Carro carro = new Carro();
						CarroDaoImpl carroDao = new CarroDaoImpl();

						carro.setMarca(marcaText);
						carro.setPlaca(placaText);
						carro.setCor(corText);
						carro.setHoraEntrada(horaEntrada);
						carro.setHoraSaida(horaSaida);

						carroDao.updateCarro(carro);

						JOptionPane.showMessageDialog(frmControleDeEstacionamento, "Carro atualizado com êxito!",
								"Mensagem de aviso", JOptionPane.INFORMATION_MESSAGE);

						loadInitialData();

						marcaFormattedTextField.setText("");
						placaFormattedTextField.setText("");
						corFormattedTextField.setText("");
						horaEntradaFormattedTextField.setText("");
						horaSaidaFormattedTextField.setText("");

					} catch (ClassNotFoundException | SQLException ex) {
						JOptionPane.showMessageDialog(frmControleDeEstacionamento, "Erro ao atualizar carro!",
								"Mensagem de erro", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(frmControleDeEstacionamento, "Campo vazio detectado!",
							"Mensagem de erro", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		dataTable.getColumnModel().getColumn(0).setPreferredWidth(105);
		dataTable.getColumnModel().getColumn(1).setPreferredWidth(98);

		loadInitialData();

	}

	public void loadInitialData() {
		Thread t1 = new Thread(() -> {
			DefaultTableModel tableModel = (DefaultTableModel) dataTable.getModel();
			tableModel.setRowCount(0);

			try {
				CarroDaoImpl carroDao = new CarroDaoImpl();
				List<Carro> carros = carroDao.getAllCarros();

				carros.sort(Comparator.comparing(Carro::getId).reversed());

				for (Carro carro : carros) {
					Object[] rowData = { carro.getId(), carro.getMarca(), carro.getCor(), carro.getPlaca(),
							carro.getHoraEntrada(), carro.getHoraSaida() };
					tableModel.addRow(rowData);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(frmControleDeEstacionamento, "Algo de errado ocorreu!",
						"Mensagem de erro", JOptionPane.ERROR_MESSAGE);
			}

		});

		t1.start();
	}
}
