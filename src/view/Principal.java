package view;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import db.Conexao;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JLabel lblBemVindo = new JLabel(".");
	public JMenuItem mntmUsurio;

	/**
	 * Create the frame.
	 */
	public Principal() {
		
		 this.setLocationRelativeTo(null);
	        this.setExtendedState(MAXIMIZED_BOTH);
	        
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/user.png")));
		setTitle("PRINCIPAL");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 400, 782, 59);
		panel.setBackground(SystemColor.textHighlight);
		contentPane.add(panel);
		panel.setLayout(null);
		lblBemVindo.setForeground(SystemColor.inactiveCaptionBorder);
		
		
		lblBemVindo.setBounds(10, 11, 143, 37);
		panel.add(lblBemVindo);
		
		JLabel lblData = new JLabel(".");
		lblData.setForeground(SystemColor.text);
		lblData.setBounds(543, 11, 219, 37);
		panel.add(lblData);
		lblData.setText(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.text, null, null, null));
		menuBar.setBackground(SystemColor.textHighlight);
		menuBar.setBounds(0, 0, 782, 29);
		contentPane.add(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		mnCadastro.setForeground(SystemColor.inactiveCaptionBorder);
		mnCadastro.setBackground(SystemColor.textHighlight);
		menuBar.add(mnCadastro);
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente cliente = new Cliente();
				cliente.setLocationRelativeTo(null);
				cliente.setVisible(true);
			}
		});
		mntmCliente.setForeground(SystemColor.text);
		mntmCliente.setBackground(SystemColor.textHighlight);
		mnCadastro.add(mntmCliente);
		
		mntmUsurio = new JMenuItem("Usu\u00E1rio");
		mntmUsurio.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_MASK));
		mntmUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuario = new Usuario();
				usuario.setLocationRelativeTo(null);
				usuario.setVisible(true);
			}
		});
		mntmUsurio.setBackground(SystemColor.textHighlight);
		mntmUsurio.setForeground(SystemColor.inactiveCaptionBorder);
		mnCadastro.add(mntmUsurio);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		
		mnRelatrio.setForeground(SystemColor.text);
		mnRelatrio.setBackground(SystemColor.textHighlight);
		
		menuBar.add(mnRelatrio);
		
		JMenuItem mntmCliente_1 = new JMenuItem("Cliente");
		mntmCliente_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chamarRelatorio();
			}
		});
		mntmCliente_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		mnRelatrio.add(mntmCliente_1);
		
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		mnOpes.setBackground(SystemColor.textHighlight);
		mnOpes.setForeground(SystemColor.text);
		menuBar.add(mnOpes);
		
		JMenuItem mntmAlterarSenha = new JMenuItem("Alterar Senha");
		mntmAlterarSenha.setBackground(SystemColor.textHighlight);
		mntmAlterarSenha.setForeground(SystemColor.text);
		mntmAlterarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterarSenha senha = new AlterarSenha();
				String text = lblBemVindo.getText().replaceAll("Bem vindo ", "");
				senha.txtUsuario.setText(text);
				senha.setLocationRelativeTo(null);
				senha.setVisible(true);
			}
		});
		mntmAlterarSenha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		mnOpes.add(mntmAlterarSenha);
		
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void chamarRelatorio() {
		try {
			Connection conn = Conexao.getConexao();
			URL url = this.getClass().getClassLoader().getResource("report/img/rel.png");
			HashMap paramter = new  HashMap();
			paramter.put("img", url);
			JasperPrint jp = JasperFillManager.fillReport("report/rel_cliente.jasper",   paramter,  conn);
			JasperViewer jv = new JasperViewer(jp, false);
			jv.setVisible(true);
			jv.setTitle("RELATÓRIO DE CLIENTE");
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane,"Erro no relatorio "+e );
		}
	}
}
