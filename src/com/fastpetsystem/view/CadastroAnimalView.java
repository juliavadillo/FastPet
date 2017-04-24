package com.fastpetsystem.view;

import java.awt.BorderLayout;
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
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.print.DocFlavor.URL;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class CadastroAnimalView extends JFrame {

	private JPanel contentPane;
	private JTextField nome;
	static JComboBox<String> tipoanimal = null;
	private JTextField datanasci;
	private JTextField raca;
	private int cod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAnimalView frame = new CadastroAnimalView();
					
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
	public CadastroAnimalView() {
		setTitle("Fast Pet");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\Logo_FastPet.png"));
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 467);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblTipoAnimal = new JLabel("Tipo Animal:");
		lblTipoAnimal.setFont(new Font("Arial", Font.BOLD, 13));
		
		nome = new JTextField();
		nome.setColumns(10);
		
		tipoanimal = new JComboBox();
		tipoanimal.setBackground(Color.WHITE);
		preencherCmb();
		
		JLabel lblCadastroAnimal = new JLabel("Cadastro de Animal");
		lblCadastroAnimal.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Arial", Font.BOLD, 13));
		
		datanasci = new JTextField();
		datanasci.setColumns(10);
		
		JLabel lblPorte = new JLabel("Porte:");
		lblPorte.setFont(new Font("Arial", Font.BOLD, 13));
		
		JComboBox porte = new JComboBox();
		porte.setModel(new DefaultComboBoxModel(new String[] {"", "P", "M", "G"}));
		
		JLabel lblNewLabel = new JLabel("Ra\u00E7a:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		
		raca = new JTextField();
		raca.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCadastrar.setBackground(SystemColor.inactiveCaption);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conexao = AcessoBD.ObterConexao();
		        Statement statement = null;
		        try {
					statement = conexao.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}     
		        String tipo = (String) tipoanimal.getSelectedItem();
		        System.out.println(tipo);
		        String query1 = "SELECT COD_TIPO_ANIMAL FROM TIPO_ANIMAL WHERE NOME_TIPO_ANIMAL = '"+tipo+"'";
		        ResultSet resultado = null;
				try {
					resultado = statement.executeQuery(query1);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Resultado =" +resultado);
		        try {
				 cod = resultado.getInt(NORMAL);
				 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		       // cod = Integer.
		      				
		        String query2 = "INSERT INTO ANIMAL(NOME_ANIMAL, COD_TIPO_ANIMAL, DT_NASCIMENTO, DT_CADASTRO, PORTE, RACA) VALUES ('"+nome.getText()+"',"+cod+",'"+datanasci.getText()+"',SYSDATE,'"+porte.getSelectedItem()+"','"+raca.getText()+"')";
		        try {
					statement.executeQuery(query2);
					JOptionPane.showMessageDialog(null,"Cadastro efetuado com sucesso");
					nome.setText(null);
					tipoanimal.setSelectedItem(null);
					datanasci.setText(null);
					porte.setSelectedItem(null);
					raca.setText(null);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Erro ao efetuar cadastro");
					e.printStackTrace();
				}
			} 
		});
		
		JButton btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLimpar.setBackground(SystemColor.inactiveCaption);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nome.setText(null);
				tipoanimal.setSelectedItem(null);
				datanasci.setText(null);
				porte.setSelectedItem(null);
				raca.setText(null);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDataDeNascimento)
								.addComponent(lblPorte)
								.addComponent(lblNewLabel)
								.addComponent(lblTipoAnimal)
								.addComponent(lblNome)
								.addComponent(btnCadastrar))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(nome)
									.addComponent(raca, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
									.addComponent(porte, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(datanasci, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
									.addComponent(tipoanimal, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(268)
							.addComponent(lblCadastroAnimal)))
					.addContainerGap(220, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastroAnimal)
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoAnimal)
						.addComponent(tipoanimal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeNascimento)
						.addComponent(datanasci, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPorte)
						.addComponent(porte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(raca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar)
						.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(50))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void preencherCmb (){
	
	Connection conexao = AcessoBD.ObterConexao();
    Statement statement = null;
	try {
		statement = conexao.createStatement();
	} catch (SQLException ex) {
		// TODO Auto-generated catch block
		ex.printStackTrace();
	}
    String query = "SELECT * FROM TIPO_ANIMAL ORDER BY NOME_TIPO_ANIMAL";
   	ResultSet rs = null;
	try {
		tipoanimal.addItem(null);
		rs= statement.executeQuery(query);
		while(rs.next()) {
			
			tipoanimal.addItem(rs.getString("NOME_TIPO_ANIMAL"));
		}
			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
    
}
}
