package com.fastpetsystem.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.fastpetsystem.model.dao.AcessoBD;

public class MenuView extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView frame = new MenuView();
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
	public MenuView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\Logo_FastPet.png"));
		setTitle("Fast Pet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 486);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroClienteView cliente = new CadastroClienteView();
				cliente.setVisible(true);

			}
		});
		mnCadastro.add(mntmCliente);

		JMenuItem mntmAnimal = new JMenuItem("Animal");
		mntmAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroAnimalView animal = new CadastroAnimalView();
				animal.setVisible(true);
			}
		});
		mnCadastro.add(mntmAnimal);

		JMenuItem mntmFuncionrio = new JMenuItem("Funcion\u00E1rio");
		mntmFuncionrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFuncionarioView funcionario = new CadastroFuncionarioView();
				funcionario.setVisible(true);
			}
		});
		mnCadastro.add(mntmFuncionrio);

		JMenuItem mntmEspcies = new JMenuItem("Esp\u00E9cies");
		mntmEspcies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroTipoAnimal tipo = new CadastroTipoAnimal();
				tipo.setVisible(true);
			}
		});

		mnCadastro.add(mntmEspcies);

		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);

		JMenuItem mntmCliente_1 = new JMenuItem("Cliente");
		mntmCliente_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaClienteView consultaCliente = new ConsultaClienteView();
				consultaCliente.setVisible(true);

			}
		});

		JMenuItem mntmAgendamentos = new JMenuItem("Agendamentos");
		mntmAgendamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaAgendamento consulta = new ConsultaAgendamento();
				consulta.setVisible(true);
			}
		});
		mnConsulta.add(mntmAgendamentos);
		mnConsulta.add(mntmCliente_1);

		JMenuItem mntmFuncionrio_1 = new JMenuItem("Funcion\u00E1rio");
		mnConsulta.add(mntmFuncionrio_1);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNovoAgendamento = new JButton("Novo Agendamento");
		btnNovoAgendamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgendamentoView agendamento = new AgendamentoView();
				agendamento.setVisible(true);
			}
		});

		JLabel lblAgendamentos = new JLabel("Agendamentos ");
		lblAgendamentos.setFont(new Font("Arial", Font.BOLD, 20));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.WHITE);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarAgendamento();
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(615, Short.MAX_VALUE)
					.addComponent(btnNovoAgendamento, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addGap(42))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 761, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAgendamentos)
							.addGap(18)
							.addComponent(btnAtualizar)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNovoAgendamento)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAgendamentos)
						.addComponent(btnAtualizar))
					.addGap(22)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(65, Short.MAX_VALUE))
		);

		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Tipo", "Descri\u00E7\u00E3o", "Pet", "Respons\u00E1vel", "Cliente",
						"Data", "Hora In\u00EDcio", "Hora T\u00E9rmino", "Status" }));
		table.getColumnModel().getColumn(2).setPreferredWidth(112);
		table.getColumnModel().getColumn(3).setPreferredWidth(81);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(20);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setMinWidth(20);
		table.getColumnModel().getColumn(6).setPreferredWidth(90);
		table.getColumnModel().getColumn(7).setMinWidth(20);
		table.getColumnModel().getColumn(8).setPreferredWidth(87);
		table.getColumnModel().getColumn(8).setMinWidth(20);
		table.getColumnModel().getColumn(9).setPreferredWidth(85);
		table.getColumnModel().getColumn(9).setMinWidth(20);

		scrollPane.setViewportView(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
		contentPane.setLayout(gl_contentPane);
		atualizarAgendamento();
	}

	public void atualizarAgendamento() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setNumRows(0);
		Connection conexao = AcessoBD.ObterConexao();
		Statement statement = null;
		ResultSet rs = null;

		try {
			statement = conexao.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "SELECT * FROM AGENDAMENTO WHERE STATUS = 'Agendado' ORDER BY COD_AGENDAMENTO";

		String[] valores = new String[10];

		try {

			rs = statement.executeQuery(query);

			while (rs.next()) {

				valores[0] = rs.getString("COD_AGENDAMENTO");
				valores[1] = rs.getString("TIPO");
				valores[2] = rs.getString("DESCRICAO");
				valores[2] = rs.getString("DESCRICAO");
				valores[3] = rs.getString("COD_FUNCIONARIO");
				valores[4] = rs.getString("COD_CLIENTE");
				valores[5] = rs.getString("DT_INICIO");
				valores[6] = rs.getString("HORA_INICIO");
				valores[7] = rs.getString("HORA_FIM");
				valores[8] = rs.getString("STATUS");

				dtm.addRow(valores);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
