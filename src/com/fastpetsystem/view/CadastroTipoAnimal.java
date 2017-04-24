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
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class CadastroTipoAnimal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroTipoAnimal frame = new CadastroTipoAnimal();
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
	public CadastroTipoAnimal() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\Logo_FastPet.png"));
		setTitle("Fast Pet");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 467);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCadastroDeEspcies = new JLabel("Cadastro de Esp\u00E9cies ");
		lblCadastroDeEspcies.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel lblEspcie = new JLabel("Esp\u00E9cie:");
		lblEspcie.setFont(new Font("Arial", Font.BOLD, 13));
		
		tipo = new JTextField();
		tipo.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(SystemColor.inactiveCaption);
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conexao = AcessoBD.ObterConexao();
		        Statement statement = null;
				try {
					statement = conexao.createStatement();
				} catch (SQLException ep) {
					// TODO Auto-generated catch block
					ep.printStackTrace();
				}
		        String query = "INSERT INTO TIPO_ANIMAL (NOME_TIPO_ANIMAL) VALUES ('"+tipo.getText()+"')";
		        
					 try {
						statement.executeQuery(query);
						 JOptionPane.showMessageDialog(null,"Cadastro efetuado com sucesso");
						 tipo.setText(null);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Não foi possível efetuar o cadastro");
						 tipo.setText(null);

						e1.printStackTrace();
					}
					
					
				
		        
			}
		});
		
		JButton btnLimpar = new JButton("Limpar Campo");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tipo.setText(null);
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLimpar.setBackground(SystemColor.inactiveCaption);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(216)
							.addComponent(lblCadastroDeEspcies))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCadastrar)
							.addGap(35)
							.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEspcie)
							.addGap(18)
							.addComponent(tipo, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(248, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastroDeEspcies)
					.addGap(70)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEspcie)
						.addComponent(tipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(121)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(156, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
