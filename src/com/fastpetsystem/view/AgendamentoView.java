package com.fastpetsystem.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.fastpetsystem.model.dao.AcessoBD;

public class AgendamentoView extends JFrame {

	private JPanel contentPane;
	static JComboBox<String> cliente = null;
	static JComboBox<String> animal = null;
	static JComboBox<String> funcionario = null;
	static JList<String> requisicao = null;
	private DefaultListModel model;	
	private JTextField descricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendamentoView frame = new AgendamentoView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgendamentoView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\Logo_FastPet.png"));
		setTitle("Fast Pet");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 736, 514);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNovoAgendamento = new JLabel("Novo Agendamento");
		lblNovoAgendamento.setFont(new Font("Arial", Font.BOLD, 18));

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Arial", Font.BOLD, 13));

		JLabel lblTipo = new JLabel("Tipo da requisi\u00E7\u00E3o:");
		lblTipo.setFont(new Font("Arial", Font.BOLD, 13));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

		JComboBox tipo = new JComboBox();
		tipo.setBackground(Color.WHITE);

		tipo.setFont(new Font("Arial", Font.PLAIN, 12));
		tipo.setModel(
				new DefaultComboBoxModel(new String[] { "", "Produto", "Servi\u00E7o", "Produto e Servi\u00E7o" }));

		JLabel lblRequisio = new JLabel("Descri\u00E7\u00E3o:");
		lblRequisio.setFont(new Font("Arial", Font.BOLD, 13));

		

		JLabel lblNewLabel = new JLabel("Data: ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));

		JFormattedTextField data = new JFormattedTextField();

		JLabel lblHora = new JLabel("Hora in\u00EDcio:");
		lblHora.setFont(new Font("Arial", Font.BOLD, 13));

		JComboBox hora1 = new JComboBox();
		hora1.setEditable(true);
		hora1.setModel(new DefaultComboBoxModel(new String[] { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00",
				"15:00", "16:00", "17:00", "18:00", "19:00" }));

		JLabel lblHoraTrmino = new JLabel("Hora t\u00E9rmino:");
		lblHoraTrmino.setFont(new Font("Arial", Font.BOLD, 13));

		JComboBox hora2 = new JComboBox();
		hora2.setEditable(true);
		hora2.setModel(new DefaultComboBoxModel(new String[] { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00",
				"15:00", "16:00", "17:00", "18:00", "19:00" }));

		cliente = new JComboBox();
		cliente.setForeground(Color.BLACK);
		cliente.setBackground(Color.WHITE);
		cliente.setFont(new Font("Arial", Font.PLAIN, 12));
		preencherCmbCliente();

		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.setBackground(SystemColor.inactiveCaption);
		btnAgendar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conexao = AcessoBD.ObterConexao();
				Statement statement = null;

				try {
					statement = conexao.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String nome = (String) cliente.getSelectedItem();
				String ani = (String) animal.getSelectedItem();
				String fun = (String) funcionario.getSelectedItem();

				String query = "INSERT INTO AGENDAMENTO (DT_INICIO,COD_FUNCIONARIO,STATUS,DESCRICAO,COD_ANIMAL,COD_CLIENTE,HORA_INICIO, HORA_FIM,TIPO ) VALUES ('"
						+ data.getText() + "','" + fun + "','Agendado','" + descricao.getText() + "','" + ani + "','"
						+ nome + "','" + hora1.getSelectedItem() + "','" + hora2.getSelectedItem() + "','"
						+ tipo.getSelectedItem() + "')";
				try {
					statement.executeQuery(query);
					JOptionPane.showMessageDialog(null, "Agendamento efetuado com sucesso");
					setVisible(false);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao efetuar agendamento");
					e.printStackTrace();

				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBackground(SystemColor.inactiveCaption);
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblAtribuirPara = new JLabel("Atribuir para:");
		lblAtribuirPara.setFont(new Font("Arial", Font.BOLD, 13));

		funcionario = new JComboBox();
		funcionario.setBackground(Color.WHITE);
		funcionario.setFont(new Font("Arial", Font.PLAIN, 12));
		preencherCmbFuncionario();

		descricao = new JTextField();
		descricao.setColumns(10);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING, false).addGroup(gl_contentPane
										.createSequentialGroup().addGroup(gl_contentPane
												.createParallelGroup(Alignment.LEADING).addGroup(
														gl_contentPane.createSequentialGroup().addGap(233).addComponent(
																lblNovoAgendamento))
												.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
														.addComponent(lblCliente))
												.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(lblTipo).addComponent(lblRequisio))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_contentPane
																.createParallelGroup(Alignment.LEADING)
																.addComponent(descricao, GroupLayout.DEFAULT_SIZE, 280,
																		Short.MAX_VALUE)
																.addComponent(tipo, GroupLayout.PREFERRED_SIZE, 138,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(cliente, 0, 225, Short.MAX_VALUE))
														.addGap(23))
												.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPane.createSequentialGroup()
																		.addComponent(lblNewLabel)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(
																				data,
																				GroupLayout.PREFERRED_SIZE,
																				74, GroupLayout.PREFERRED_SIZE))
																.addComponent(btnAgendar)
																.addComponent(lblAtribuirPara))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPane.createSequentialGroup()
																		.addGroup(gl_contentPane
																				.createParallelGroup(Alignment.LEADING,
																						false)
																				.addComponent(funcionario, 0,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addGroup(gl_contentPane
																						.createSequentialGroup()
																						.addComponent(lblHora)
																						.addPreferredGap(
																								ComponentPlacement.RELATED)
																						.addComponent(hora1,
																								GroupLayout.PREFERRED_SIZE,
																								67,
																								GroupLayout.PREFERRED_SIZE)))
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addComponent(lblHoraTrmino).addGap(4)
																		.addComponent(hora2, GroupLayout.PREFERRED_SIZE,
																				59, GroupLayout.PREFERRED_SIZE))
																.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE,
																		96, GroupLayout.PREFERRED_SIZE))))
										.addGap(21))
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(260, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap().addComponent(lblNovoAgendamento).addGap(45)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(cliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCliente))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblTipo).addComponent(
						tipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblRequisio).addComponent(
						descricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(21).addComponent(panel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
						.addComponent(data, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHora)
						.addComponent(hora1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHoraTrmino).addComponent(hora2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblAtribuirPara)
						.addComponent(funcionario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnAgendar)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGap(41)));

		JLabel lblAnimal = new JLabel("Animal:");
		lblAnimal.setFont(new Font("Arial", Font.BOLD, 13));

		animal = new JComboBox();
		animal.setBackground(Color.WHITE);
		animal.setFont(new Font("Arial", Font.PLAIN, 12));
		preencherCmbAnimal();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addComponent(lblAnimal).addGap(77)
						.addComponent(animal, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(104, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(animal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAnimal))
						.addContainerGap(31, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	public void preencherCmbCliente() {
		Connection conexao = AcessoBD.ObterConexao();
		Statement statement = null;
		try {
			statement = conexao.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "SELECT * FROM CLIENTE ORDER BY NOME_CLIENTE";
		ResultSet rs = null;

		try {
			cliente.addItem(null);
			rs = statement.executeQuery(query);
			while (rs.next()) {

				cliente.addItem(rs.getString("NOME_CLIENTE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void preencherCmbAnimal() {
		Connection conexao = AcessoBD.ObterConexao();
		Statement statement = null;
		try {
			statement = conexao.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "SELECT * FROM ANIMAL ORDER BY NOME_ANIMAL";
		ResultSet rs = null;

		try {
			animal.addItem(null);
			rs = statement.executeQuery(query);
			while (rs.next()) {

				animal.addItem(rs.getString("NOME_ANIMAL"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void preencherCmbFuncionario() {
		Connection conexao = AcessoBD.ObterConexao();
		Statement statement = null;
		try {
			statement = conexao.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String query = "SELECT * FROM FUNCIONARIO ORDER BY NOME_FUNCIONARIO";

		try {
			funcionario.addItem(null);
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				funcionario.addItem(rs.getString("NOME_FUNCIONARIO"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
