package com.fastpetsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.fastpetsystem.model.dao.AcessoBD;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ConsultaFuncionarioView extends JFrame {

	private JPanel contentPane;
	private JTextField nome;
	private JTable table;
	private JTextField nomep;
	private JTextField cpfp;
	private JTextField emailp;
	private JTextField telefonep;
	private String codfun ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaFuncionarioView frame = new ConsultaFuncionarioView();
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
	public ConsultaFuncionarioView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\Logo_FastPet.png"));
		setTitle("Fast Pet");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		setBounds(100, 100, 1003, 542);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Consulta de Funcion\u00E1rio");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel lblInsiraONome = new JLabel("Insira o nome:");
		lblInsiraONome.setFont(new Font("Arial", Font.PLAIN, 13));
		
		nome = new JTextField();
		nome.setColumns(10);
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setNumRows(0);
				Connection conexao = AcessoBD.ObterConexao();
		        Statement statement = null;
		        ResultSet rs = null;
		        
		        try {
					statement = conexao.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        String nome1 = nome.getText();
		        String query = "SELECT * FROM FUNCIONARIO WHERE NOME_FUNCIONARIO = '"+nome1+"'"; 
		        
		        String[] valores = new String[6];
		        
		        try {
					rs = statement.executeQuery(query);
					
                      while (rs.next()){
						
						valores[0] = rs.getString("COD_FUNCIONARIO");
                        valores[1]= rs.getString("NOME_FUNCIONARIO");
                        valores[2] = rs.getString("cpf");
                        valores[3] = rs.getString("EMAIL");
                        valores[4] = rs.getString("FONE");
                        valores[5] = rs.getString("DT_CADASTRO");                      
                        
                        
                        dtm.addRow(valores);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        nome.setText(null);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setVisible(false);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				carregarCampos(null);
				
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarCampos (null);
				Connection conexao = AcessoBD.ObterConexao();
		        Statement statement = null;
		        ResultSet rs = null;
		        
		        try {
					statement = conexao.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        String query = "DELETE FROM FUNCIONARIO WHERE COD_FUNCIONARIO = "+codfun+"";
		        
		        try {
					rs = statement.executeQuery(query);
					JOptionPane.showMessageDialog(null,"Funcionário excluido com sucesso");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Não foi possível excluir funcionário");
					e1.printStackTrace();
				}
			}
		});
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblInsiraONome)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nome, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnNewButton))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAlterar)
									.addGap(42)
									.addComponent(btnExcluir))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 620, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 319, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(339)
							.addComponent(lblNewLabel)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel)
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInsiraONome)
						.addComponent(nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(34)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir))
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(110, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addGap(121))
		);
		
		JLabel lblNewLabel_1 = new JLabel("Alterar dados do Funcion\u00E1rio");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 13));
		
		nomep = new JTextField();
		nomep.setColumns(10);
		
		cpfp = new JTextField();
		cpfp.setColumns(10);
		
		emailp = new JTextField();
		emailp.setColumns(10);
		
		telefonep = new JTextField();
		telefonep.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Concluir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conexao = AcessoBD.ObterConexao();
		        Statement statement = null;
		        
		        try {
					statement = conexao.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        String query = "UPDATE FUNCIONARIO SET nome_funcionario = '"+nomep.getText()+"', cpf = "+cpfp.getText()+", fone = '"+telefonep.getText()+"',  email = '"+emailp.getText()+"' WHERE COD_FUNCIONARIO = "+codfun+" ";
		        try {
					statement.executeQuery(query);
					JOptionPane.showMessageDialog(null,"Alteração efetuada com sucesso");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Não foi possível realizar a alteração");
					e1.printStackTrace();
				}
		        
		        
			}
		});
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				nomep.setText(null);
				cpfp.setText(null);
				telefonep.setText(null);
				emailp.setText(null);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNome)
								.addComponent(lblCpf)
								.addComponent(lblEmail)
								.addComponent(lblTelefone))
							.addPreferredGap(ComponentPlacement.RELATED, 2, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(emailp)
									.addComponent(lblNewLabel_1)
									.addComponent(nomep, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
									.addComponent(cpfp, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnNewButton_2)
									.addComponent(telefonep, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
							.addGap(47))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addContainerGap(224, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(nomep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(cpfp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(emailp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(telefonep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "CPF", "EMAIL", "Telefone", "Data de Cadastro"
			}
		));
		table.getColumnModel().getColumn(5).setMinWidth(27);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	private void carregarCampos(java.awt.event.MouseEvent evt) {
		// Método para carregar automaticamente os campos de texto a partir da seleção de uma linha na Jtable.
		 if (table.getSelectedRow() != -1) {
		nomep.setText(table.getValueAt(table.getSelectedRow(),1).toString());
		cpfp.setText(table.getValueAt(table.getSelectedRow(),2).toString());
		emailp.setText(table.getValueAt(table.getSelectedRow(),3).toString());
		telefonep.setText(table.getValueAt(table.getSelectedRow(),4).toString());		
		codfun = (table.getValueAt(table.getSelectedRow(),0).toString());
		
		 }
		 }
}
