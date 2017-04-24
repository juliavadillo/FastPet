package com.fastpetsystem.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.fastpetsystem.model.dao.AcessoBD;

public class CadastroClienteView extends JFrame {
	private JTextField nome;
	private JTextField email;
	private JTextField celular;
	private JTextField fone;
	private JTextField endereco;
	private JTextField cpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroClienteView frame = new CadastroClienteView();
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
	public CadastroClienteView() {
		setTitle("Fast Pet");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\Logo_FastPet.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 685, 467);

		JLabel lblCadastroconsultaDeCliente = new JLabel("Cadastro de Cliente");
		lblCadastroconsultaDeCliente.setFont(new Font("Arial", Font.BOLD, 18));

		nome = new JTextField();
		nome.setForeground(new Color(0, 0, 0));
		nome.setColumns(10);

		JLabel label_1 = new JLabel("Nome:");
		label_1.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel label_2 = new JLabel("CPF:");
		label_2.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel label_3 = new JLabel("Endere\u00E7o:");
		label_3.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel label_4 = new JLabel("Telefone Fixo:");
		label_4.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel label_5 = new JLabel("Celular:");
		label_5.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel lblEmail = new JLabel("E-mail: ");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 12));

		email = new JTextField();
		email.setColumns(10);

		celular = new JTextField();
		celular.setColumns(10);

		fone = new JTextField();
		fone.setColumns(10);

		endereco = new JTextField();
		endereco.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(SystemColor.inactiveCaption);
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
				String query = "INSERT INTO CLIENTE (NOME_CLIENTE,CPF,ENDERECO,FONE_FIXO,FONE_CELULAR,EMAIL,DT_CADASTRO) VALUES ('"
						+ nome.getText() + "','" + cpf.getText() + "','" + endereco.getText() + "','" + fone.getText()
						+ "','" + celular.getText() + "','" + email.getText() + "',SYSDATE)";
				try {
					statement.executeQuery(query);
					JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso");
					nome.setText(null);
					cpf.setText(null);
					endereco.setText(null);
					fone.setText(null);
					celular.setText(null);
					email.setText(null);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao efetuar cadastro");
					e.printStackTrace();
				}

			}
		});
		cpf = new JTextField();
		cpf.setColumns(10);

		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.setBackground(SystemColor.inactiveCaption);
		btnLimparCampos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nome.setText(null);
				cpf.setText(null);
				endereco.setText(null);
				fone.setText(null);
				celular.setText(null);
				email.setText(null);
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
								groupLayout
										.createSequentialGroup().addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGap(10)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 85,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 65,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(label_2).addComponent(label_1)))
												.addGroup(groupLayout.createSequentialGroup().addContainerGap()
														.addComponent(label_5))
												.addGroup(groupLayout.createSequentialGroup().addContainerGap()
														.addComponent(lblEmail)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(email, GroupLayout.PREFERRED_SIZE, 237,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(celular, GroupLayout.PREFERRED_SIZE, 124,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(fone, GroupLayout.PREFERRED_SIZE, 124,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(endereco, GroupLayout.PREFERRED_SIZE,
																237, GroupLayout.PREFERRED_SIZE)
														.addComponent(nome, GroupLayout.PREFERRED_SIZE, 237,
																GroupLayout.PREFERRED_SIZE))
												.addComponent(cpf, GroupLayout.PREFERRED_SIZE, 123,
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup().addGap(240)
										.addComponent(lblCadastroconsultaDeCliente))
								.addGroup(groupLayout.createSequentialGroup().addGap(36)
										.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 95,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(btnLimparCampos, GroupLayout.PREFERRED_SIZE, 139,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(257, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(27).addComponent(lblCadastroconsultaDeCliente).addGap(41)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_2).addComponent(cpf,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(endereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_4).addComponent(fone,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(celular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
				.addGap(54)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLimparCampos, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addGap(63)));
		getContentPane().setLayout(groupLayout);

	}
}
