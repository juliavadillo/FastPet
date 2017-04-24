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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.fastpetsystem.model.dao.AcessoBD;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField cpf;
	private JPasswordField senha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\Logo_FastPet.png"));
		setTitle("Fast Pet");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblBemVindoAo = new JLabel("Bem Vindo ao Sistema Fast Pet");
		lblBemVindoAo.setFont(new Font("Arial", Font.BOLD, 18));

		JLabel lblLogin = new JLabel("CPF:");
		lblLogin.setFont(new Font("Arial", Font.BOLD, 13));

		cpf = new JTextField();
		cpf.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.BOLD, 13));

		senha = new JPasswordField();

		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conexao = AcessoBD.ObterConexao();
				Statement statement = null;

				try {
					statement = conexao.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String query = "SELECT CPF, SENHA FROM FUNCIONARIO WHERE cpf = " + cpf.getText() + " AND SENHA = "
						+ senha.getText() + "";
				ResultSet rs = null;

				try {
					rs = statement.executeQuery(query);
					if (rs.next()) {
						dispose();
						MenuView menu = new MenuView();
						menu.setVisible(true);

					} else
						JOptionPane.showMessageDialog(null, "Login Inválido");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup().addContainerGap(84, Short.MAX_VALUE)
								.addComponent(lblBemVindoAo).addGap(65))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(64)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnAcessar).addGap(18)
										.addComponent(btnCancelar))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblSenha).addComponent(lblLogin))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(senha)
												.addComponent(cpf, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))))
						.addContainerGap(171, Short.MAX_VALUE)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(lblBemVindoAo)
								.addGap(38)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(cpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblLogin))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(senha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSenha))
								.addGap(42)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnAcessar).addComponent(btnCancelar))
								.addContainerGap(57, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}
}
