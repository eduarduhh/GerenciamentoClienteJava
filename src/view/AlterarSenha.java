package view;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.UsuarioDAO;

public class AlterarSenha extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 686433377085187278L;
	private JPanel contentPane;
	public JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JPasswordField txtNovaSenha;


	/**
	 * Create the frame.
	 */
	public AlterarSenha() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 434, 41);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAlterarSenha = new JLabel("ALTERAR SENHA");
		lblAlterarSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAlterarSenha.setForeground(SystemColor.text);
		lblAlterarSenha.setBounds(137, 11, 167, 19);
		panel.add(lblAlterarSenha);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(0, 182, 434, 29);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("NOME:");
		lblNewLabel.setBounds(10, 55, 61, 14);
		contentPane.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setText("Administrador");
		txtUsuario.setEditable(false);
		txtUsuario.setBounds(130, 52, 234, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setBounds(10, 84, 46, 14);
		contentPane.add(lblSenha);
		
		JLabel lblRepetirSenha = new JLabel("NOVA SENHA");
		lblRepetirSenha.setBounds(10, 120, 93, 14);
		contentPane.add(lblRepetirSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(130, 81, 234, 20);
		contentPane.add(txtSenha);
		
		txtNovaSenha = new JPasswordField();
		txtNovaSenha.setBounds(130, 117, 234, 20);
		contentPane.add(txtNovaSenha);
		
		JButton btnAlterarSenha = new JButton("Alterar Senha");
		btnAlterarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterarSenhaUsuario();
			
			}
		});
		btnAlterarSenha.setForeground(SystemColor.textHighlight);
		btnAlterarSenha.setBackground(SystemColor.text);
		btnAlterarSenha.setBounds(214, 148, 150, 23);
		contentPane.add(btnAlterarSenha);
	}
	
	public void pegaUsuario () {
		Principal principal = new Principal();
		String texto =  principal.lblBemVindo.getText().replaceAll("Bem vindo ", "");
		txtUsuario.setText(texto);
	}
	@SuppressWarnings("deprecation")
	public void alterarSenhaUsuario() {
		
		if(txtSenha.getText().equals("") || txtNovaSenha.getText().equals("") ){
			JOptionPane.showMessageDialog(rootPane, "Preencha todos os campos");
		}else {
			UsuarioDAO  dao = new UsuarioDAO();
			
			String nome = txtUsuario.getText();
			String senha   = Base64.getEncoder().encodeToString(txtSenha.getText().getBytes());
			String novasSenha  = Base64.getEncoder().encodeToString(txtNovaSenha.getText().getBytes());
			
			boolean resultado = dao.verficiaUsuarioSenha(nome, senha);
			
			if(resultado == true) {
				dao.alterarSenha(nome, senha, novasSenha);
				int i = JOptionPane.showConfirmDialog(rootPane, "Deseja sair do Sistema", 
													"Troca de Senha", JOptionPane.YES_NO_OPTION);	
				if(i == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}else {
				JOptionPane.showMessageDialog(rootPane, "Senha incorreta");
			}
		}
		
	}
}
