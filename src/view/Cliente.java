package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import dao.ClienteDAO;
import dao.EnderecoDAO;
import dao.TelefoneDAO;
import model.Telefone;

public class Cliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 190395791358351910L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtPesquisaTabela;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JFormattedTextField txtRg;
	private JRadioButton rdbtnMasculino = new JRadioButton("MASCULINO");
	private JRadioButton rdbtnFeminino = new JRadioButton("FEMININO");
	private JPanel pManutencao;
	private JRadioButton rdbtnAtivo;
	private JRadioButton rdbtnInativo;
	private JTabbedPane tabbedPane;
	private JButton btnEditar;
	private JButton btnIncluir;
	private JFormattedTextField txtCpf;
	private JButton btnExcluir;
	private JTable tableTelefone;
	private JTextField txtCodigoTelefone;
	private JTextField txtNomeTelefone;
	private JTextField txtCodigoTel;
	private JTextField txtTipoTel;
	private JTextField txtNumeroTel;
	private JButton btnSalvarTel;
	private JButton btnExcluirTel;
	private JButton btnLimparTel;
	private JTextField txtCodigoEndereco;
	private JTextField txtNomeEndreco;
	private JTextField txtTipoEnd;
	private JTextField txtEnderecoEnd;
	private JTextField txtBairroEnd;
	private JTextField txtCidadeEnd;
	private JFormattedTextField txtCepEnd;
	private JTable tableEndereco;
	private JTextField txtCodigoEnd;
	private JButton btnExcluirEnd;
	private JComboBox<String> cbUfEnd;

	

	/**
	 * Create the frame.
	 */
	public Cliente() {
		setTitle("CLIENTE");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cliente.class.getResource("/img/user.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 936, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 930, 45);
		panel.setBackground(SystemColor.textHighlight);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblClientes = new JLabel("CLIENTES");
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClientes.setForeground(SystemColor.text);
		lblClientes.setBounds(412, 11, 129, 23);
		panel.add(lblClientes);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 591, 930, 29);
		panel_1.setBackground(SystemColor.textHighlight);
		contentPane.add(panel_1);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 58, 910, 522);
		contentPane.add(tabbedPane);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Listagem", null, panel_2, null);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 38, 917, 468);
		panel_2.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selecionaDados();
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setIcon(new ImageIcon(Cliente.class.getResource("/img/iconfinder_search_285651.png")));
		lblPesquisar.setBounds(27, 11, 84, 14);
		panel_2.add(lblPesquisar);

		txtPesquisaTabela = new JTextField();
		txtPesquisaTabela.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				String pesquisar = txtPesquisaTabela.getText();
				pesquisarTabela(pesquisar);
			}
		});
		txtPesquisaTabela.setBounds(112, 7, 423, 20);
		panel_2.add(txtPesquisaTabela);
		txtPesquisaTabela.setColumns(10);

		pManutencao = new JPanel();
		tabbedPane.addTab("Manuten\u00E7\u00E3o", null, pManutencao, null);
		pManutencao.setLayout(null);

		JLabel lblCodigo = new JLabel("CODIGO");
		lblCodigo.setBounds(29, 48, 77, 14);
		pManutencao.add(lblCodigo);

		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(29, 85, 64, 14);
		pManutencao.add(lblNome);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(102, 45, 317, 20);
		pManutencao.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(102, 82, 317, 20);
		pManutencao.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(29, 116, 46, 14);
		pManutencao.add(lblCpf);

		JLabel lblRg = new JLabel("RG");
		lblRg.setBounds(29, 146, 46, 14);
		pManutencao.add(lblRg);

		txtRg = new JFormattedTextField();
		txtRg.setBounds(102, 144, 317, 20);
		pManutencao.add(txtRg);
		txtRg.setColumns(10);

		rdbtnMasculino.setBounds(102, 172, 109, 23);
		rdbtnMasculino.setSelected(true);
		pManutencao.add(rdbtnMasculino);

		rdbtnFeminino.setBounds(310, 172, 109, 23);
		pManutencao.add(rdbtnFeminino);

		JLabel lblSexo = new JLabel("SEXO");
		lblSexo.setBounds(29, 176, 46, 14);
		pManutencao.add(lblSexo);

		JSeparator separator = new JSeparator();
		separator.setBounds(29, 248, 457, 2);
		pManutencao.add(separator);

		btnIncluir = new JButton("Incluir");
		btnIncluir.setBackground(SystemColor.text);
		btnIncluir.setForeground(SystemColor.textHighlight);
		btnIncluir.setIcon(new ImageIcon(Cliente.class.getResource("/img/iconfinder_floppy_285657 (2).png")));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText().equals("") || txtCpf.getText().equals("") || txtRg.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Preencha todos os campos");
				} else {
					ClienteDAO dao = new ClienteDAO();
					boolean cpf = dao.cadastrado(txtCpf.getText());
					if (cpf == true) {
						JOptionPane.showMessageDialog(rootPane, "o CPF " + txtCpf.getText() + " ja está cadastrado");
					} else {
						model.Cliente cliente = new model.Cliente();
						cliente.setNome(txtNome.getText().toUpperCase());
						cliente.setCpf(txtCpf.getText());
						cliente.setRg(txtRg.getText());
						if (rdbtnMasculino.isSelected()) {
							cliente.setSexo("M");
						} else {
							cliente.setSexo("F");
						}
						if (rdbtnAtivo.isSelected()) {
							cliente.setAtivo("1");
						} else {
							cliente.setAtivo("0");
						}
						dao.inserir(cliente);
						limpar();
						mostrarTabela();
						tabbedPane.setSelectedIndex(0);
					}
				}
			}
		});
		btnIncluir.setBounds(29, 261, 93, 23);
		pManutencao.add(btnIncluir);

		btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(Cliente.class.getResource("/img/iconfinder_book_285636.png")));
		btnEditar.setBackground(SystemColor.text);
		btnEditar.setForeground(SystemColor.textHighlight);
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText().equals("") || txtCpf.getText().equals("") || txtRg.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "o CPF " + txtCpf.getText() + " ja está cadastrado");
				} else {
					ClienteDAO dao = new ClienteDAO();
					model.Cliente cliente = new model.Cliente();
					cliente.setNome(txtNome.getText().toUpperCase());
					cliente.setCpf(txtCpf.getText());
					cliente.setRg(txtRg.getText());
					cliente.setCodigo(Integer.parseInt(txtCodigo.getText()));
					if (rdbtnMasculino.isSelected()) {
						cliente.setSexo("M");
					} else {
						cliente.setSexo("F");
					}
					if (rdbtnAtivo.isSelected()) {
						cliente.setAtivo("1");
					} else {
						cliente.setAtivo("0");
					}
					dao.alterar(cliente);
					limpar();
					mostrarTabela();
					tabbedPane.setSelectedIndex(0);
					btnEditar.setEnabled(false);
					btnIncluir.setEnabled(true);
					btnExcluir.setEnabled(false);
				}

			}
		});
		btnEditar.setBounds(145, 261, 93, 23);
		pManutencao.add(btnEditar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBackground(SystemColor.text);
		btnLimpar.setForeground(SystemColor.textHighlight);
		btnLimpar.setIcon(new ImageIcon(Cliente.class.getResource("/img/iconfinder_sign-info_299086.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
			}
		});
		btnLimpar.setBounds(379, 261, 107, 23);
		pManutencao.add(btnLimpar);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnFeminino);
		buttonGroup.add(rdbtnMasculino);

		JLabel lblAtivo = new JLabel("ATIVO");
		lblAtivo.setBounds(29, 211, 46, 14);
		pManutencao.add(lblAtivo);

		rdbtnAtivo = new JRadioButton("ATIVO");
		rdbtnAtivo.setSelected(true);
		rdbtnAtivo.setBounds(102, 207, 109, 23);
		pManutencao.add(rdbtnAtivo);

		rdbtnInativo = new JRadioButton("INATIVO");
		rdbtnInativo.setBounds(310, 198, 109, 23);
		pManutencao.add(rdbtnInativo);

		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(rdbtnAtivo);
		buttonGroup1.add(rdbtnInativo);

		txtCpf = new JFormattedTextField();
		txtCpf.setBounds(102, 113, 317, 20);

		pManutencao.add(txtCpf);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente remover " + txtNome.getText(),
						"Remover", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					int codigo = Integer.parseInt(txtCodigo.getText());
					ClienteDAO dao = new ClienteDAO();
					dao.remover(codigo);
					limpar();
					mostrarTabela();

					tabbedPane.setSelectedIndex(0);
					btnEditar.setEnabled(false);
					btnIncluir.setEnabled(true);
					btnExcluir.setEnabled(false);
				}
			}
		});
		btnExcluir.setBackground(SystemColor.text);
		btnExcluir.setForeground(SystemColor.textHighlight);
		btnExcluir.setIcon(new ImageIcon(Cliente.class.getResource("/img/iconfinder_sign-error_299045 (1).png")));
		btnExcluir.setBounds(259, 261, 110, 23);
		pManutencao.add(btnExcluir);

		JPanel Endereco = new JPanel();
		tabbedPane.addTab("Endereco", null, Endereco, null);
		Endereco.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(370, 0, 535, 494);
		Endereco.add(scrollPane_2);

		tableEndereco = new JTable();
		tableEndereco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selecionaDadosEndereco();
			}
		});
		scrollPane_2.setViewportView(tableEndereco);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.textHighlight);
		panel_5.setBounds(0, 0, 370, 42);
		Endereco.add(panel_5);
		panel_5.setLayout(null);

		JLabel label_1 = new JLabel("DADOS DO CLIENTE");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(109, 11, 168, 23);
		panel_5.add(label_1);

		JLabel lblCodigo_3 = new JLabel("CODIGO");
		lblCodigo_3.setBounds(10, 53, 46, 14);
		Endereco.add(lblCodigo_3);

		JLabel lblNome_1 = new JLabel("NOME");
		lblNome_1.setBounds(10, 90, 46, 14);
		Endereco.add(lblNome_1);

		txtCodigoEndereco = new JTextField();
		txtCodigoEndereco.setEditable(false);
		txtCodigoEndereco.setBounds(66, 50, 86, 20);
		Endereco.add(txtCodigoEndereco);
		txtCodigoEndereco.setColumns(10);

		txtNomeEndreco = new JTextField();
		txtNomeEndreco.setEditable(false);
		txtNomeEndreco.setBounds(66, 87, 276, 20);
		Endereco.add(txtNomeEndreco);
		txtNomeEndreco.setColumns(10);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 115, 370, 2);
		Endereco.add(separator_2);

		JLabel lblTipo_1 = new JLabel("TIPO");
		lblTipo_1.setBounds(10, 164, 46, 14);
		Endereco.add(lblTipo_1);

		txtTipoEnd = new JTextField();
		txtTipoEnd.setBounds(97, 161, 243, 20);
		Endereco.add(txtTipoEnd);
		txtTipoEnd.setColumns(10);

		txtEnderecoEnd = new JTextField();
		txtEnderecoEnd.setBounds(97, 192, 245, 20);
		Endereco.add(txtEnderecoEnd);
		txtEnderecoEnd.setColumns(10);

		JLabel lblEndereo = new JLabel("ENDERE\u00C7O");
		lblEndereo.setBounds(10, 195, 64, 14);
		Endereco.add(lblEndereo);

		JLabel lblBairro = new JLabel("BAIRRO");
		lblBairro.setBounds(10, 230, 46, 14);
		Endereco.add(lblBairro);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(10, 265, 46, 14);
		Endereco.add(lblCep);

		txtBairroEnd = new JTextField();
		txtBairroEnd.setBounds(97, 227, 243, 20);
		Endereco.add(txtBairroEnd);
		txtBairroEnd.setColumns(10);

		JLabel lblCidade = new JLabel("CIDADE");
		lblCidade.setBounds(10, 302, 46, 14);
		Endereco.add(lblCidade);

		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(10, 347, 46, 14);
		Endereco.add(lblUf);

		txtCidadeEnd = new JTextField();
		txtCidadeEnd.setBounds(97, 299, 245, 20);
		Endereco.add(txtCidadeEnd);
		txtCidadeEnd.setColumns(10);

		txtCepEnd = new JFormattedTextField();
		txtCepEnd.setBounds(97, 262, 243, 20);
		Endereco.add(txtCepEnd);

		cbUfEnd = new JComboBox<String>();
		cbUfEnd.setModel(new DefaultComboBoxModel<String>(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cbUfEnd.setBounds(97, 344, 245, 20);
		Endereco.add(cbUfEnd);

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCodigoEndereco.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane,"Primeiro selecione o cliente");
				}else {
					if(txtTipoEnd.getText().equals("") ||
						txtEnderecoEnd.getText().equals("") ||
						txtBairroEnd.getText().equals("") ||
						txtCidadeEnd.getText().equals("") ||
						txtCepEnd.getText().equals("") ) {
						
						JOptionPane.showMessageDialog(rootPane, "Preencha todos os campos");
					}else {
						 if(txtCodigoEnd.getText().equals("")) {
							 EnderecoDAO enderecoDAO = new EnderecoDAO();
							 model.Endereco endereco = new model.Endereco();
							 ClienteDAO clienteDAO = new ClienteDAO();
							 model.Cliente cliente = new model.Cliente();
							 
							 int codigo = Integer.parseInt(txtCodigoEndereco.getText());
							 cliente = clienteDAO.retornaCliente(codigo);
							 
							 endereco.setTipo(txtTipoEnd.getText());
							 endereco.setEndereco(txtEnderecoEnd.getText());
							 endereco.setBairro(txtBairroEnd.getText());
							 endereco.setCidade(txtCidadeEnd.getText());
							 endereco.setCep(txtCepEnd.getText());
							 endereco.setUf((String)cbUfEnd.getSelectedItem());
							 endereco.setCliente(cliente);
							 
							 enderecoDAO.inserir(endereco);
							 mostrarTabelaEndereco(codigo);
							 limparEnd();
						 }else {
							 //altera
							 
							 EnderecoDAO enderecoDAO = new EnderecoDAO();
							 model.Endereco endereco = new model.Endereco();
							 ClienteDAO clienteDAO = new ClienteDAO();
							 model.Cliente cliente = new model.Cliente();
							 
							 int codigo = Integer.parseInt(txtCodigoEndereco.getText());
							 cliente = clienteDAO.retornaCliente(codigo);
							 
							 endereco.setCodigo(Integer.parseInt(txtCodigoEnd.getText()));
							 endereco.setTipo(txtTipoEnd.getText());
							 endereco.setEndereco(txtEnderecoEnd.getText());
							 endereco.setBairro(txtBairroEnd.getText());
							 endereco.setCidade(txtCidadeEnd.getText());
							 endereco.setCep(txtCepEnd.getText());
							 endereco.setUf((String)cbUfEnd.getSelectedItem());
							 endereco.setCliente(cliente);
							 
							 enderecoDAO.alterar(endereco);
							 mostrarTabelaEndereco(codigo);
							 limparEnd();
						 }
					}
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(Cliente.class.getResource("/img/iconfinder_floppy_285657 (2).png")));
		btnNewButton.setBounds(20, 401, 97, 23);
		Endereco.add(btnNewButton);

		btnExcluirEnd = new JButton("Excluir");
		btnExcluirEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(rootPane, 
								"Deseja remover o endereco:" + txtEnderecoEnd.getText(),
								"Remover", JOptionPane.YES_NO_OPTION);
				if(i == JOptionPane.YES_OPTION) {
					EnderecoDAO enderecoDAO = new EnderecoDAO();
					int codigo = Integer.parseInt(txtCodigoEnd.getText());
					enderecoDAO.remover(codigo);
					codigo = Integer.parseInt(txtCodigoEndereco.getText());
					mostrarTabelaEndereco(codigo);
					limparEnd();
				}
			}
		});
		btnExcluirEnd.setIcon(new ImageIcon(Cliente.class.getResource("/img/iconfinder_sign-error_299045 (1).png")));
		btnExcluirEnd.setBounds(134, 401, 97, 23);
		Endereco.add(btnExcluirEnd);
		btnExcluirEnd.setEnabled(false);
		
		JButton btnNewButton_2 = new JButton("Limpar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparEnd();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Cliente.class.getResource("/img/iconfinder_sign-info_299086.png")));
		btnNewButton_2.setBounds(253, 401, 101, 23);
		Endereco.add(btnNewButton_2);
		
		txtCodigoEnd = new JTextField();
		txtCodigoEnd.setEditable(false);
		txtCodigoEnd.setBounds(97, 128, 245, 20);
		Endereco.add(txtCodigoEnd);
		txtCodigoEnd.setColumns(10);
		
		JLabel lblCodigo_4 = new JLabel("CODIGO");
		lblCodigo_4.setBounds(10, 131, 46, 14);
		Endereco.add(lblCodigo_4);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Telefone", null, panel_3, null);
		panel_3.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(412, 0, 493, 494);
		panel_3.add(scrollPane_1);

		tableTelefone = new JTable();
		tableTelefone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selecionaDadosTelefone();
				btnExcluirTel.setEnabled(true);
			}
		});
		scrollPane_1.setViewportView(tableTelefone);

		JLabel lblCodigo_1 = new JLabel("CODIGO");
		lblCodigo_1.setBounds(10, 45, 46, 14);
		panel_3.add(lblCodigo_1);

		txtCodigoTelefone = new JTextField();
		txtCodigoTelefone.setEditable(false);
		txtCodigoTelefone.setBounds(66, 42, 86, 20);
		panel_3.add(txtCodigoTelefone);
		txtCodigoTelefone.setColumns(10);

		JLabel lblCliente = new JLabel("NOME");
		lblCliente.setBounds(10, 73, 46, 14);
		panel_3.add(lblCliente);

		txtNomeTelefone = new JTextField();
		txtNomeTelefone.setEditable(false);
		txtNomeTelefone.setBounds(66, 70, 299, 20);
		panel_3.add(txtNomeTelefone);
		txtNomeTelefone.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 122, 412, 2);
		panel_3.add(separator_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.textHighlight);
		panel_4.setForeground(SystemColor.menu);
		panel_4.setBounds(0, 0, 412, 34);
		panel_3.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblDadosDoCliente = new JLabel("DADOS DO CLIENTE");
		lblDadosDoCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDadosDoCliente.setVerticalAlignment(SwingConstants.TOP);
		lblDadosDoCliente.setForeground(SystemColor.text);
		lblDadosDoCliente.setBounds(125, 11, 168, 23);
		panel_4.add(lblDadosDoCliente);

		JLabel label = new JLabel("");
		label.setBounds(10, 141, 46, 14);
		panel_3.add(label);

		JLabel lblCodigo_2 = new JLabel("CODIGO");
		lblCodigo_2.setBounds(10, 165, 46, 14);
		panel_3.add(lblCodigo_2);

		txtCodigoTel = new JTextField();
		txtCodigoTel.setEditable(false);
		txtCodigoTel.setBounds(66, 162, 299, 20);
		panel_3.add(txtCodigoTel);
		txtCodigoTel.setColumns(10);

		JLabel lblTipo = new JLabel("TIPO");
		lblTipo.setBounds(10, 209, 46, 14);
		panel_3.add(lblTipo);

		txtTipoTel = new JTextField();
		txtTipoTel.setBounds(66, 206, 299, 20);
		panel_3.add(txtTipoTel);
		txtTipoTel.setColumns(10);

		JLabel lblNmero = new JLabel("N\u00DAMERO");
		lblNmero.setBounds(10, 250, 52, 14);
		panel_3.add(lblNmero);

		txtNumeroTel = new JTextField();
		txtNumeroTel.setBounds(66, 247, 299, 20);
		panel_3.add(txtNumeroTel);
		txtNumeroTel.setColumns(10);

		btnSalvarTel = new JButton("Salvar");
		btnSalvarTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCodigoTelefone.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Primeiro selecione o cliente");
				} else {
					if (txtTipoTel.getText().equals("") || txtNumeroTel.getText().equals("")) {
						JOptionPane.showMessageDialog(rootPane, "Preencha todos os campos");
					} else {
						if (txtCodigoTel.getText().equals("")) {

							// SALVAR TELEFONE
							model.Cliente cliente = new model.Cliente();
							ClienteDAO clienteDAO = new ClienteDAO();
							Telefone telefone = new Telefone();
							TelefoneDAO telefoneDAO = new TelefoneDAO();

							int codigoCliente = Integer.parseInt(txtCodigoTelefone.getText());
							cliente = clienteDAO.retornaCliente(codigoCliente);

							telefone.setCliente(cliente);
							telefone.setTipo(txtTipoTel.getText());
							telefone.setNumero(txtNumeroTel.getText());

							telefoneDAO.inserir(telefone);

							mostrarTabelaTelefone(cliente.getCodigo());
							limparTel();
						} else {
							// ALTERAR TELEFONE

							model.Cliente cliente = new model.Cliente();
							ClienteDAO clienteDAO = new ClienteDAO();
							Telefone telefone = new Telefone();
							TelefoneDAO telefoneDAO = new TelefoneDAO();

							int codigoCliente = Integer.parseInt(txtCodigoTelefone.getText());
							cliente = clienteDAO.retornaCliente(codigoCliente);

							telefone.setCliente(cliente);
							telefone.setCodigo(Integer.parseInt(txtCodigoTel.getText()));
							telefone.setTipo(txtTipoTel.getText());
							telefone.setNumero(txtNumeroTel.getText());

							telefoneDAO.alterar(telefone);

							mostrarTabelaTelefone(cliente.getCodigo());
							limparTel();
						}
					}

				}
			}

		});
		btnSalvarTel.setForeground(SystemColor.textHighlight);
		btnSalvarTel.setBackground(SystemColor.text);
		btnSalvarTel.setIcon(new ImageIcon(Cliente.class.getResource("/img/iconfinder_floppy_285657 (2).png")));
		btnSalvarTel.setBounds(51, 309, 101, 23);
		panel_3.add(btnSalvarTel);

		btnExcluirTel = new JButton("Excluir");
		btnExcluirTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelefoneDAO telefoneDAO = new TelefoneDAO();
				int codigo = Integer.parseInt(txtCodigoTel.getText());
				int codigoCliente = Integer.parseInt(txtCodigoTelefone.getText());
				telefoneDAO.remover(codigo);

				mostrarTabelaTelefone(codigoCliente);
				limparTel();
				btnExcluirTel.setEnabled(false);
			}
		});

		btnExcluirTel.setForeground(SystemColor.textHighlight);
		btnExcluirTel.setBackground(SystemColor.text);
		btnExcluirTel.setIcon(new ImageIcon(Cliente.class.getResource("/img/iconfinder_sign-error_299045 (1).png")));
		btnExcluirTel.setBounds(162, 309, 105, 23);
		panel_3.add(btnExcluirTel);
		btnExcluirTel.setEnabled(false);

		btnLimparTel = new JButton("Limpar");
		btnLimparTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTel();
				btnExcluirTel.setEnabled(false);
			}
		});
		btnLimparTel.setForeground(SystemColor.textHighlight);
		btnLimparTel.setBackground(SystemColor.text);
		btnLimparTel.setIcon(new ImageIcon(Cliente.class.getResource("/img/iconfinder_sign-info_299086.png")));
		btnLimparTel.setBounds(277, 309, 101, 23);
		panel_3.add(btnLimparTel);
		// funcoes
		// tabela Cliente
		mostrarTabela();
		// tabela telefone
		mostrarTabelaTelefone(0);
		// tabela endereco
		mostrarTabelaEndereco(0);
		// setar mascaras nos campos
		mascaras();
	}

	public void mostrarTabela() {

		DefaultTableModel model = new DefaultTableModel() {
			// bloqueia editat coluna
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		};
		model.addColumn("CODIGO");
		model.addColumn("NOME");
		model.addColumn("RG");
		model.addColumn("CPF");
		model.addColumn("SEXO");
		model.addColumn("ATIVO");

		table.setModel(model);

		ClienteDAO dao = new ClienteDAO();

		List<model.Cliente> listaCliente = new ArrayList<>();
		listaCliente = dao.listarCliente();

		for (model.Cliente c : listaCliente) {
			String[] linha = new String[6];
			linha[0] = Integer.toString(c.getCodigo());
			linha[1] = c.getNome();
			linha[2] = c.getRg();
			linha[3] = c.getCpf();
			linha[4] = c.getSexo();
			linha[5] = c.getAtivo();
			model.addRow(linha);
		}

	}

	public void mostrarTabelaTelefone(int codigo) {

		DefaultTableModel model = new DefaultTableModel() {
			// bloqueia editat coluna
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		};
		model.addColumn("CODIGO");
		model.addColumn("TIPO");
		model.addColumn("NÚMERO");

		tableTelefone.setModel(model);

		TelefoneDAO dao = new TelefoneDAO();

		List<model.Telefone> listaTelefone = new ArrayList<>();
		listaTelefone = dao.listarPorCliente(codigo);

		for (model.Telefone t : listaTelefone) {
			String[] linha = new String[3];
			linha[0] = Integer.toString(t.getCodigo());
			linha[1] = t.getTipo();
			linha[2] = t.getNumero();
			model.addRow(linha);
		}

	}

	public void mostrarTabelaEndereco(int codigo) {
		// TABELA ENDERECO
		DefaultTableModel model = new DefaultTableModel() {
			// bloqueia editat coluna
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		};
		model.addColumn("CODIGO");
		model.addColumn("TIPO");
		model.addColumn("ENDERECO");
		model.addColumn("BAIRRO");
		model.addColumn("CIDADE");
		model.addColumn("CEP");
		model.addColumn("UF");

		tableEndereco.setModel(model);

		EnderecoDAO dao = new EnderecoDAO();

		List<model.Endereco> listaEndereco = new ArrayList<>();
		listaEndereco = dao.listarPorCliente(codigo);

		for (model.Endereco e : listaEndereco) {
			String[] linha = new String[7];
			linha[0] = Integer.toString(e.getCodigo());
			linha[1] = e.getTipo();
			linha[2] = e.getEndereco();
			linha[3] = e.getBairro();
			linha[4] = e.getCidade();
			linha[5] = e.getCep();
			linha[6] = e.getUf();
			model.addRow(linha);
		}

	}

	public void pesquisarTabela(String pesquisar) {
		// PESQUISA DADOS DA TABELA CLIENTE
		DefaultTableModel model = new DefaultTableModel() {
			// bloqueia editat coluna
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		model.addColumn("CODIGO");
		model.addColumn("NOME");
		model.addColumn("RG");
		model.addColumn("CPF");
		model.addColumn("SEXO");
		model.addColumn("ATIVO");

		table.setModel(model);

		ClienteDAO dao = new ClienteDAO();

		List<model.Cliente> listaCliente = new ArrayList<>();
		listaCliente = dao.pesquisarCliente(pesquisar);

		for (model.Cliente c : listaCliente) {
			String[] linha = new String[7];
			linha[0] = Integer.toString(c.getCodigo());
			linha[1] = c.getNome();
			linha[2] = c.getRg();
			linha[3] = c.getCpf();
			linha[4] = c.getSexo();
			linha[5] = c.getAtivo();
			model.addRow(linha);
		}

	}

	public void selecionaDados() {
		// SELECIONA DADOS DA TABELA CLIENTE
		int row = table.getSelectedRow();
		// PANEL MANUTENCAO
		txtCodigo.setText((String) table.getValueAt(row, 0));
		txtNome.setText((String) table.getValueAt(row, 1));
		txtRg.setText((String) table.getValueAt(row, 2));
		txtCpf.setText((String) table.getValueAt(row, 3));

		if (table.getValueAt(row, 4).toString().equals("MASCULINO")) {
			rdbtnMasculino.setSelected(true);
		} else {
			rdbtnFeminino.setSelected(true);
		}
		if (table.getValueAt(row, 5).toString().equals("SIM")) {
			rdbtnAtivo.setSelected(true);
		} else {
			rdbtnInativo.setSelected(true);
		}
		// PANEL TELEFONE
		txtCodigoTelefone.setText((String) table.getValueAt(row, 0));
		txtNomeTelefone.setText((String) table.getValueAt(row, 1));
		// PANEL ENDERECO
		txtCodigoEndereco.setText((String) table.getValueAt(row, 0));
		txtNomeEndreco.setText((String) table.getValueAt(row, 1));
		// POPULANDO TABELA TELEFONE
		int codigo = Integer.parseInt((String) table.getValueAt(row, 0));
		mostrarTabelaTelefone(codigo);
		mostrarTabelaEndereco(codigo);
		// selecionar index Manutencao
		tabbedPane.setSelectedIndex(1);
		btnEditar.setEnabled(true);
		btnIncluir.setEnabled(false);
		btnExcluir.setEnabled(true);

	}

	public void selecionaDadosTelefone() {
		// SELECIONA DADOS DA TABELA TELEFONE
		int row = tableTelefone.getSelectedRow();
		// PANEL TELEFONE
		txtCodigoTel.setText((String) tableTelefone.getValueAt(row, 0));
		txtTipoTel.setText((String) tableTelefone.getValueAt(row, 1));
		txtNumeroTel.setText((String) tableTelefone.getValueAt(row, 2));

	}
	
	public void selecionaDadosEndereco() {
		// SELECIONA DADOS DA TABELA Endereco
		int row = tableEndereco.getSelectedRow();
		// PANEL ENDERECO
		txtCodigoEnd.setText((String) tableEndereco.getValueAt(row, 0));
		txtTipoEnd.setText((String) tableEndereco.getValueAt(row, 1));
		txtEnderecoEnd.setText((String) tableEndereco.getValueAt(row, 2));
		txtBairroEnd.setText((String) tableEndereco.getValueAt(row, 3));
		txtCidadeEnd.setText((String) tableEndereco.getValueAt(row, 4));
		txtCepEnd.setText((String) tableEndereco.getValueAt(row, 5));
		cbUfEnd.setSelectedItem((String)tableEndereco.getValueAt(row, 6));
		
		btnExcluirEnd.setEnabled(true);
	

	}
	public void limpar() {
		// LIMPA OS CAMPOS CLIENTE
		txtCodigo.setText("");
		txtNome.setText("");
		txtRg.setText("");
		txtCpf.setText("");
		btnEditar.setEnabled(false);
		btnIncluir.setEnabled(true);
	}

	public void limparTel() {
		// LIMPA OS CAMPOS CLIENTE
		txtCodigoTel.setText("");
		txtNumeroTel.setText("");
		txtTipoTel.setText("");
		btnExcluirTel.setEnabled(false);
	}
	
	public void limparEnd() {
		// LIMPA OS CAMPOS CLIENTE
		txtCodigoEnd.setText("");
		txtTipoEnd.setText("");
		txtEnderecoEnd.setText("");
		txtEnderecoEnd.setText("");
		txtBairroEnd.setText("");
		txtCidadeEnd.setText("");
		txtCepEnd.setValue("");
		btnExcluirEnd.setEnabled(false);
	}

	public void mascaras() {
		// EMPREGANDO MASCARAS
		try {
			txtCepEnd.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("#####-###")));
			txtCpf.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
			txtRg.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##.###.###-#")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
