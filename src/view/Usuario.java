package view;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.UsuarioDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Font;

public class Usuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel Panel;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtUSuario;
	
	private JTable table = new JTable();
	private JRadioButton rdbtnAtivo = new JRadioButton("Ativo");
	private JRadioButton rdbtnInativo = new JRadioButton("Inativo");
	private JPasswordField txtSenha;
	private JTextField txtPesquisar;
	private JRadioButton rdbtnAdminisrtador;
	private JRadioButton rdbtnUsuario;
	private JButton btnExcluir;
	
	/**
	 * Create the frame.
	 */
	public Usuario() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuario.class.getResource("/img/user.png")));
		setTitle("USU\u00C1RIO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 878, 361);
		Panel = new JPanel();
		Panel.setBackground(UIManager.getColor("Button.background"));
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel);
		Panel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("CODIGO");
		lblCodigo.setForeground(SystemColor.windowBorder);
		lblCodigo.setBackground(SystemColor.textInactiveText);
		lblCodigo.setBounds(24, 46, 46, 14);
		Panel.add(lblCodigo);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setForeground(SystemColor.windowBorder);
		lblNome.setBackground(SystemColor.textInactiveText);
		lblNome.setBounds(24, 88, 46, 14);
		Panel.add(lblNome);
		
		JLabel lblUsurio = new JLabel("USU\u00C1RIO");
		lblUsurio.setForeground(SystemColor.windowBorder);
		lblUsurio.setBackground(SystemColor.textInactiveText);
		lblUsurio.setBounds(24, 129, 56, 14);
		Panel.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setForeground(SystemColor.windowBorder);
		lblSenha.setBackground(SystemColor.textInactiveText);
		lblSenha.setBounds(24, 173, 46, 14);
		Panel.add(lblSenha);
		
		txtCodigo = new JTextField();
		txtCodigo.setBackground(UIManager.getColor("Button.background"));
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(80, 43, 297, 20);
		Panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBackground(SystemColor.controlLtHighlight);
		txtNome.setBounds(80, 85, 297, 20);
		Panel.add(txtNome);
		txtNome.setColumns(10);
		
		txtUSuario = new JTextField();
		txtUSuario.setBackground(SystemColor.controlLtHighlight);
		txtUSuario.setBounds(80, 126, 297, 20);
		Panel.add(txtUSuario);
		txtUSuario.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(SystemColor.textHighlight);
		btnSalvar.setBackground(SystemColor.text);
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(txtNome.getText().equals("") 
						|| txtNome.getText().equals("")
						|| txtSenha.getText().equals("")) {
					
					JOptionPane.showMessageDialog(rootPane, "Preencha todos os campos");
				}else {
					if(txtCodigo.getText().equals("")) {
						//CADASTRO
						model.Usuario usuario = new model.Usuario();
						UsuarioDAO dao = new UsuarioDAO();
						usuario.setNome(txtNome.getText());
						usuario.setUsuario(txtUSuario.getText());
						// encriptando 64
						String senha  = Base64.getEncoder().encodeToString(txtSenha.getText().getBytes());
						usuario.setSenha(senha);
						if(rdbtnAtivo.isSelected()) {
							usuario.setAtivo("1");
						}else {
							usuario.setAtivo("0");
						}
						if(rdbtnAdminisrtador.isSelected()) {
							usuario.setNivel("1");
						}else {
							usuario.setNivel("2");
						}
						dao.inserir(usuario);
						mostrarTabela();
					}else {
						//ALTERACAO
						model.Usuario usuario = new model.Usuario();
						UsuarioDAO dao = new UsuarioDAO();
						usuario.setCodigo(Integer.parseInt(txtCodigo.getText()));
						usuario.setNome(txtNome.getText());
						usuario.setUsuario(txtUSuario.getText());
						// encriptando 64
						String senha  = Base64.getEncoder().encodeToString(txtSenha.getText().getBytes());
						usuario.setSenha(senha);
						if(rdbtnAtivo.isSelected()) {
							usuario.setAtivo("1");
						}else {
							usuario.setAtivo("0");
						}
						if(rdbtnAdminisrtador.isSelected()) {
							usuario.setNivel("1");
						}else {
							usuario.setNivel("2");
						}
						dao.alterar(usuario);
						mostrarTabela();
					}
					limparCampos();
				}
			}
		});
		btnSalvar.setIcon(new ImageIcon(Usuario.class.getResource("/img/iconfinder_floppy_285657 (2).png")));
		btnSalvar.setBounds(66, 267, 97, 23);
		Panel.add(btnSalvar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(rootPane,
						"Tem certeza que deseja remover o usuario:"+txtNome.getText(),
						"Remover", JOptionPane.YES_NO_OPTION);
						if(i == JOptionPane.YES_OPTION) {
							UsuarioDAO  dao = new UsuarioDAO();
							int codigo = Integer.parseInt(txtCodigo.getText());
							dao.remover(codigo);
							mostrarTabela();
							limparCampos();
						}
			}
		});
		btnExcluir.setForeground(SystemColor.textHighlight);
		btnExcluir.setBackground(SystemColor.text);
		btnExcluir.setIcon(new ImageIcon(Usuario.class.getResource("/img/iconfinder_sign-error_299045 (1).png")));
		btnExcluir.setBounds(173, 267, 100, 23);
		Panel.add(btnExcluir);
		
		JButton btnRemover = new JButton("Limpar");
		btnRemover.setForeground(SystemColor.textHighlight);
		btnRemover.setBackground(SystemColor.text);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});
		btnRemover.setIcon(new ImageIcon(Usuario.class.getResource("/img/iconfinder_sign-info_299086.png")));
		btnRemover.setBounds(283, 267, 97, 23);
		Panel.add(btnRemover);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(409, 73, 453, 175);
		Panel.add(scrollPane);
		table.setBackground(SystemColor.text);
		table.setForeground(SystemColor.textHighlight);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selecionaDados();
			}
		});
		
		
		scrollPane.setViewportView(table);
		
		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setBackground(SystemColor.windowBorder);
		lblPesquisar.setForeground(SystemColor.textInactiveText);
		lblPesquisar.setIcon(new ImageIcon(Usuario.class.getResource("/img/iconfinder_search_285651.png")));
		lblPesquisar.setBounds(409, 46, 82, 14);
		Panel.add(lblPesquisar);
		rdbtnAtivo.setForeground(Color.BLACK);
		
	
		
		
		rdbtnAtivo.setBounds(80, 197, 109, 23);
		rdbtnAtivo.setSelected(true);
		Panel.add(rdbtnAtivo);
		
		rdbtnInativo.setBounds(218, 197, 109, 23);
		Panel.add(rdbtnInativo);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnAtivo);
		buttonGroup.add(rdbtnInativo);
		
		txtSenha = new JPasswordField();
		txtSenha.setBackground(SystemColor.controlLtHighlight);
		txtSenha.setBounds(80, 170, 297, 20);
		Panel.add(txtSenha);
		
		txtPesquisar = new JTextField();
		txtPesquisar.setBackground(SystemColor.controlLtHighlight);
		txtPesquisar.setForeground(SystemColor.textHighlight);
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				String nome = txtPesquisar.getText();
				pesquisarTabela(nome);
			}
		});
		txtPesquisar.setBounds(492, 43, 275, 20);
		Panel.add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(-13, 0, 885, 35);
		Panel.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsurios = new JLabel("Usu\u00E1rios");
		lblUsurios.setForeground(SystemColor.inactiveCaptionBorder);
		lblUsurios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsurios.setBounds(370, 11, 433, 14);
		panel.add(lblUsurios);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(0, 301, 885, 31);
		Panel.add(panel_1);
		
		JLabel lblAtivo = new JLabel("ATIVO");
		lblAtivo.setForeground(SystemColor.windowBorder);
		lblAtivo.setBounds(24, 201, 46, 14);
		Panel.add(lblAtivo);
		
		JLabel lblNewLabel = new JLabel("NIVEL");
		lblNewLabel.setForeground(SystemColor.windowBorder);
		lblNewLabel.setBounds(24, 226, 46, 14);
		Panel.add(lblNewLabel);
		
		rdbtnAdminisrtador = new JRadioButton("Administrador");
		rdbtnAdminisrtador.setSelected(true);
		rdbtnAdminisrtador.setBounds(80, 223, 109, 23);
		Panel.add(rdbtnAdminisrtador);
		
		rdbtnUsuario = new JRadioButton("Usu\u00E1rio");
		rdbtnUsuario.setBounds(218, 223, 109, 23);
		Panel.add(rdbtnUsuario);
		
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(rdbtnAdminisrtador);
		buttonGroup2.add(rdbtnUsuario);
		
		
		
		
		mostrarTabela();
	
	}
	
	public void mostrarTabela() {
		
		DefaultTableModel model = new DefaultTableModel(){
			 //bloqueia editat coluna
			private static final long serialVersionUID = 1L;

			@Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		model.addColumn("CODIGO");
		model.addColumn("NOME");
		model.addColumn("USUÁRIO");
		model.addColumn("SENHA");
		model.addColumn("ATIVO");
		model.addColumn("NIVEL");
		table.setModel(model);		

		UsuarioDAO dao = new UsuarioDAO();
		List<model.Usuario> listaUsuario = new ArrayList<>();
		listaUsuario = dao.listarUsuario();
		
		for (model.Usuario u : listaUsuario) {
			String[] linha = new String[6];
			linha[0] = Integer.toString(u.getCodigo());
			linha[1] = u.getNome();
			linha[2] = u.getUsuario();
			linha[3] = u.getSenha();
			linha[4] = u.getAtivo();
			linha[5] = u.getNivel();
			model.addRow(linha);
		}
		
	}
	
	public void pesquisarTabela(String nome) {
		
		DefaultTableModel model = new DefaultTableModel(){
			 //bloqueia editat coluna
			private static final long serialVersionUID = 1L;

			@Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		
		model.addColumn("CODIGO");
		model.addColumn("NOME");
		model.addColumn("USUÁRIO");
		model.addColumn("SENHA");
		model.addColumn("ATIVO");
		model.addColumn("NIVEL");
		table.setModel(model);		

		UsuarioDAO dao = new UsuarioDAO();
		List<model.Usuario> listaUsuario = new ArrayList<>();
		listaUsuario = dao.pesquisarUsuario(nome);
		
		for (model.Usuario u : listaUsuario) {
			String[] linha = new String[6];
			linha[0] = Integer.toString(u.getCodigo());
			linha[1] = u.getNome();
			linha[2] = u.getUsuario();
			linha[3] = u.getSenha();
			linha[4] = u.getAtivo();
			linha[5] = u.getNivel();
			model.addRow(linha);
		}
		
		btnExcluir.setEnabled(false);
		
	}

	public void selecionaDados() {
		int row = table.getSelectedRow();
		txtCodigo.setText((String) table.getValueAt(row, 0));
		txtNome.setText((String) table.getValueAt(row, 1));
		txtUSuario.setText((String) table.getValueAt(row, 2));
		//desencriptar senha 64
		String senha = (String) table.getValueAt(row, 3) ;
		txtSenha.setText( new String(Base64.getDecoder().decode(senha)));
		if(table.getValueAt(row, 4).toString().equals("SIM")) {
			rdbtnAtivo.setSelected(true);
		}else {
			rdbtnInativo.setSelected(true);
		}
		
		if(table.getValueAt(row, 5).toString().equals("ADMIN")) {
			rdbtnAdminisrtador.setSelected(true);
		}else {
			rdbtnUsuario.setSelected(true);
		}
		
		btnExcluir.setEnabled(true);
		
	}
	
	public void limparCampos() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtUSuario.setText("");
		txtSenha.setText("");
		rdbtnAtivo.setSelected(true);
		
		btnExcluir.setEnabled(false);
	
	}
}
