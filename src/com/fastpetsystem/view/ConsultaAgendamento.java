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

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.fastpetsystem.model.dao.AcessoBD;

public class ConsultaAgendamento extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblResponsvel;
	private JLabel lblNewLabel_1;
	private JButton btnAlterar;
	private JComboBox responsavelp;
	private JComboBox statusp;
	private String codagd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaAgendamento frame = new ConsultaAgendamento();
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
	public ConsultaAgendamento() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\Logo_FastPet.png"));
		setTitle("Fast Pet");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1003, 542);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblAcompanhamentoDosAgendamentos = new JLabel("Acompanhamento dos Agendamentos");
		lblAcompanhamentoDosAgendamentos.setFont(new Font("Arial", Font.BOLD, 18));

		JScrollPane scrollPane = new JScrollPane();

		panel = new JPanel();
		panel.setVisible(false);
		panel.setBackground(Color.WHITE);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				carregarCampos(null);
				preencherCmbFuncionario();

			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(330)
								.addComponent(lblAcompanhamentoDosAgendamentos))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(92).addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 814, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAlterar))))
				.addContainerGap(71, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(35)
						.addComponent(lblAcompanhamentoDosAgendamentos).addGap(55)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE).addGap(2)
						.addComponent(btnAlterar).addGap(18)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE).addContainerGap()));

		lblNewLabel = new JLabel("Alterar dados Agendamento");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));

		lblResponsvel = new JLabel("Respons\u00E1vel:");
		lblResponsvel.setFont(new Font("Arial", Font.BOLD, 12));

		lblNewLabel_1 = new JLabel("Status:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));

		responsavelp = new JComboBox();

		statusp = new JComboBox();
		statusp.setModel(new DefaultComboBoxModel(new String[] { "", "Agendado", "Finalizado", "Cancelado" }));

		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conexao = AcessoBD.ObterConexao();
				Statement statement = null;

				try {
					statement = conexao.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String query = "UPDATE AGENDAMENTO SET cod_funcionario = '" + responsavelp.getSelectedItem()
						+ "',  status = '" + statusp.getSelectedItem() + "' WHERE COD_AGENDAMENTO = " + codagd + " ";
				try {
					statement.executeQuery(query);
					JOptionPane.showMessageDialog(null, "Alteração efetuada com sucesso");
					panel.setVisible(false);
					atualizarAgendamento();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Não foi possível realizar a alteração");
					e1.printStackTrace();
				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(false);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblResponsvel).addComponent(lblNewLabel_1))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(statusp, 0, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(responsavelp, 0, 119, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup().addComponent(btnConcluir).addGap(18)
										.addComponent(btnCancelar)))
						.addContainerGap(250, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblResponsvel).addComponent(
						responsavelp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1).addComponent(
						statusp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnConcluir)
						.addComponent(btnCancelar))
				.addContainerGap(20, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Tipo", "Descri\u00E7\u00E3o", "Animal", "Respons\u00E1vel", "Cliente",
						"Data", "Hora In\u00EDcio", "Hora T\u00E9rmino", "Status" }));
		table.getColumnModel().getColumn(2).setPreferredWidth(91);
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
		atualizarAgendamento();
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
		contentPane.setLayout(gl_contentPane);
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
		String query = "SELECT * FROM AGENDAMENTO  ORDER BY COD_AGENDAMENTO";

		String[] valores = new String[10];

		try {

			rs = statement.executeQuery(query);

			while (rs.next()) {

				valores[0] = rs.getString("COD_AGENDAMENTO");
				valores[1] = rs.getString("TIPO");
				valores[2] = rs.getString("DESCRICAO");
				valores[3] = rs.getString("COD_ANIMAL");
				valores[4] = rs.getString("COD_FUNCIONARIO");
				valores[5] = rs.getString("COD_CLIENTE");
				valores[6] = rs.getString("DT_INICIO");
				valores[7] = rs.getString("HORA_INICIO");
				valores[8] = rs.getString("HORA_FIM");
				valores[9] = rs.getString("STATUS");

				dtm.addRow(valores);
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
			responsavelp.addItem(null);
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				responsavelp.addItem(rs.getString("NOME_FUNCIONARIO"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void carregarCampos(java.awt.event.MouseEvent evt) {
		// Método para carregar automaticamente os campos de texto a partir da
		// seleção de uma linha na Jtable.
		if (table.getSelectedRow() != -1) {
			responsavelp.setSelectedItem(table.getValueAt(table.getSelectedRow(), 2).toString());
			statusp.setSelectedItem(table.getValueAt(table.getSelectedRow(), 7).toString());
			codagd = (table.getValueAt(table.getSelectedRow(), 0).toString());

		}
	}
}
