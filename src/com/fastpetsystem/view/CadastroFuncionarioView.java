package com.fastpetsystem.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fastpetsystem.model.dao.AcessoBD;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class CadastroFuncionarioView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nome;
	private JTextField cpf;
	private JTextField tel;
	private JTextField email;
	private JPasswordField senha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFuncionarioView frame = new CadastroFuncionarioView();
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
	public CadastroFuncionarioView() {
		setTitle("Fast Pet");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\Logo_FastPet.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 467);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCadastroDeFuncionrio = new JLabel("Cadastro de Funcion\u00E1rio");
		lblCadastroDeFuncionrio.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 13));
		
		nome = new JTextField();
		nome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial", Font.BOLD, 13));
		
		cpf = new JTextField();
		cpf.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 13));
		
		tel = new JTextField();
		tel.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 13));
		
		email = new JTextField();
		email.setColumns(10);
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conexao = AcessoBD.ObterConexao();
		        Statement statement = null;
				try {
					statement = conexao.createStatement();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			  String password = String.valueOf(senha.getPassword());
				
		        String query = "INSERT INTO FUNCIONARIO (NOME_FUNCIONARIO,CPF,EMAIL,FONE,DT_CADASTRO, SENHA) VALUES ('"+nome.getText()+"','"+cpf.getText()+"','"+email.getText()+"','"+tel.getText()+"',SYSDATE,"+password+")";
		        
					 try {
						statement.executeQuery(query);
						JOptionPane.showMessageDialog(null,"Cadastro efetuado com sucesso");
						 nome.setText(null);
						 cpf.setText(null);
						 tel.setText(null);
						 email.setText(null);
						 senha.setText(null);
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Não foi possível efetuar o cadastro");
						 nome.setText(null);
						 cpf.setText(null);
						 tel.setText(null);
						 email.setText(null);
						 senha.setText(null);
					
						e1.printStackTrace();
					}
					 
			}
		});
		
		JButton btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLimpar.setBackground(SystemColor.inactiveCaption);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome.setText(null);
				 cpf.setText(null);
				 tel.setText(null);
				 email.setText(null);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Senha:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		
		senha = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(204)
							.addComponent(lblCadastroDeFuncionrio))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton)
									.addGap(40)
									.addComponent(btnLimpar))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNome)
										.addComponent(lblCpf)
										.addComponent(lblTelefone)
										.addComponent(lblEmail)
										.addComponent(lblNewLabel))
									.addGap(34)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(senha, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(email)
											.addComponent(nome, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
											.addComponent(tel)
											.addComponent(cpf, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)))))))
					.addGap(204))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(lblCadastroDeFuncionrio)
					.addGap(58)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(cpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(tel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(senha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(50))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
