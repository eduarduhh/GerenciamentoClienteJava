package view;


import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import db.Conexao;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUSuario;
	private JPasswordField txtSenha;
	private JLabel lblBanco = new JLabel("");
	private JButton btnLogin = new JButton("Login");

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("GERENCIAMENTO DE CLIENTES");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/user.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Senha");
		lblUsurio.setBounds(95, 121, 46, 14);
		contentPane.add(lblUsurio);
		
		JLabel lblUsurio_1 = new JLabel("Usu\u00E1rio");
		lblUsurio_1.setBounds(95, 96, 46, 14);
		contentPane.add(lblUsurio_1);
		
		txtUSuario = new JTextField();
		txtUSuario.setBounds(167, 93, 212, 20);
		contentPane.add(txtUSuario);
		txtUSuario.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(167, 121, 212, 20);
		contentPane.add(txtSenha);
		btnLogin.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					login();
			    }
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnLogin.setForeground(SystemColor.textHighlight);
		btnLogin.setBounds(290, 152, 89, 23);
		contentPane.add(btnLogin);
		
		
		lblBanco.setIcon(new ImageIcon(Login.class.getResource("/img/dbon.png")));
		lblBanco.setBounds(22, 152, 46, 43);
		contentPane.add(lblBanco);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/img/fundotelaLogin.png")));
		label.setBounds(0, 0, 475, 237);
		contentPane.add(label);
		
		verificarBanco();
		
	}
	
	public void verificarBanco() {
		Connection conn = Conexao.getConexao();
		if(conn == null) {
			lblBanco.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
			btnLogin.setEnabled(false);
		}
			try {
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
	@SuppressWarnings("deprecation")
	public void login() {
		try {
			String sql = "select * from usuario where usuario = ? and senha  = ? and ativo = '1'";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, txtUSuario.getText());
			ps.setString(2, Base64.getEncoder().encodeToString(txtSenha.getText().getBytes()));
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Principal principal = new Principal();
				principal.lblBemVindo.setText("Bem vindo " + rs.getString("nome"));
				principal.setLocationRelativeTo(null);
				
				if(rs.getString("nivel").equals("2")) {
					principal.mntmUsurio.setEnabled(false);
				}
				
				principal.setVisible(true);
				dispose();
			}else {
				JOptionPane.showMessageDialog(null,"Usuário e/ou senha invalidos");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Problema no login" +e);
		}
	}
}
