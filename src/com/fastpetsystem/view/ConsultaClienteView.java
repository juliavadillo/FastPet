package com.fastpetsystem.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.fastpetsystem.model.dao.AcessoBD;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Toolkit;
import java.awt.Color;

public class ConsultaClienteView extends JFrame {

	private JPanel contentPane;
	private JTextField nome;
	private JTable table;
	private JTextField nomep;
	private JTextField cpfp;
	private JTextField enderecop;
	private JTextField telefonep;
	private JTextField celularp;
	private JTextField emailp;
	private String codcli ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaClienteView frame = new ConsultaClienteView();
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
	public ConsultaClienteView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\Logo_FastPet.png"));
		setTitle("Fast Pet");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1003, 542);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		nome = new JTextField();
		nome.setColumns(10);
		
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		        String query = "SELECT * FROM CLIENTE WHERE NOME_CLIENTE = '"+nome1+"'"; 
		       
		        String[] valores = new String[8];
		        try {
					 rs = statement.executeQuery(query);
					
					 
					
					 while (rs.next()){
						
						valores[0] = rs.getString("COD_CLIENTE");
                        valores[1]= rs.getString("NOME_CLIENTE");
                        valores[2] = rs.getString("cpf");
                        valores[3] = rs.getString("endereco");
                        valores[4] = rs.getString("FONE_FIXO");
                        valores[5] = rs.getString("FONE_CELULAR");
                        valores[6]= rs.getString("EMAIL");
                        valores[7] = rs.getString("DT_CADASTRO");
                        
                        dtm.addRow(valores);

						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        nome.setText(null);
			}
		});
		
		JLabel lblConsultaDeCliente = new JLabel("Consulta de Cliente");
		lblConsultaDeCliente.setFont(new Font("Arial", Font.BOLD, 18));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setVisible(false);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(true);
				carregarCampos(null);
				
				
				
			}
		});
		
		JLabel lblInsiraONome = new JLabel("Insira o nome:");
		lblInsiraONome.setFont(new Font("Arial", Font.PLAIN, 13));
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
									.addGap(18)
									.addComponent(nome, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnPesquisar))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 642, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAlterar))
							.addGap(18)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(295)
							.addComponent(lblConsultaDeCliente)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblConsultaDeCliente)
							.addGap(48)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInsiraONome)
								.addComponent(nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPesquisar))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnAlterar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(98)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 13));
		
		nomep = new JTextField();
		nomep.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial", Font.BOLD, 13));
		
		cpfp = new JTextField();
		cpfp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Endere\u00E7o: ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		
		enderecop = new JTextField();
		enderecop.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 13));
		
		telefonep = new JTextField();
		telefonep.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Arial", Font.BOLD, 13));
		
		celularp = new JTextField();
		celularp.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 13));
		
		emailp = new JTextField();
		emailp.setColumns(10);
		
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
		        
		        String query = "UPDATE CLIENTE SET nome_cliente = '"+nomep.getText()+"', cpf = "+cpfp.getText()+", endereco= '"+enderecop.getText()+"', fone_fixo = '"+telefonep.getText()+"', fone_celular = '"+celularp.getText()+"', email = '"+emailp.getText()+"' WHERE COD_CLIENTE = "+codcli+" ";
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
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				panel.setVisible(false);
				nomep.setText(null);
				cpfp.setText(null);
				telefonep.setText(null);
				celularp.setText(null);
				enderecop.setText(null);
				emailp.setText(null);
			}
		});
		
		JLabel lblAlterarDadosDo = new JLabel("Alterar dados do Cliente");
		lblAlterarDadosDo.setFont(new Font("Arial", Font.BOLD, 13));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel)
										.addComponent(lblNome)
										.addComponent(lblCpf)
										.addComponent(lblTelefone)
										.addComponent(lblCelular)
										.addComponent(lblEmail))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(emailp)
										.addComponent(nomep, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
										.addComponent(cpfp, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
										.addComponent(enderecop, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
										.addComponent(celularp)
										.addComponent(telefonep, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnConcluir)
									.addGap(30)
									.addComponent(btnCancelar))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(61)
							.addComponent(lblAlterarDadosDo)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAlterarDadosDo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(nomep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(cpfp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(enderecop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTelefone)
						.addComponent(telefonep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCelular)
						.addComponent(celularp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(emailp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConcluir)
						.addComponent(btnCancelar))
					.addGap(29))
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nome", "CPF", "Endereco", "Telefone", "Celular", "Email", "Data de Cadastro"
			}
		));
		table.getColumnModel().getColumn(7).setPreferredWidth(98);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	private void carregarCampos(java.awt.event.MouseEvent evt) {
		// Método para carregar automaticamente os campos de texto a partir da seleção de uma linha na Jtable.
		 if (table.getSelectedRow() != -1) {
		nomep.setText(table.getValueAt(table.getSelectedRow(),1).toString());
		cpfp.setText(table.getValueAt(table.getSelectedRow(),2).toString());
		enderecop.setText(table.getValueAt(table.getSelectedRow(),3).toString());
		telefonep.setText(table.getValueAt(table.getSelectedRow(),4).toString());
		celularp.setText(table.getValueAt(table.getSelectedRow(),5).toString());
		emailp.setText(table.getValueAt(table.getSelectedRow(),6).toString());
		codcli = (table.getValueAt(table.getSelectedRow(),0).toString());
		
		 }
		 }
}
