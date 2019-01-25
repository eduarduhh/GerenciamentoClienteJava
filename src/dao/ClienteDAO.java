package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.Conexao;
import model.Cliente;

public class ClienteDAO {
	
	public boolean cadastrado(String cpf) {
		boolean resultado = false;
			
		try {
			String sql ="select * from cliente where cpf = ?";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				resultado = true;
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao verificar registro :"+e);
		}
		return resultado;
	}
	public void inserir(Cliente cliente) {
		try {
			String sql ="INSERT INTO cliente (nome, cpf, rg, sexo, ativo) values (?, ?, ?, ?, ?) ";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getRg());
			ps.setString(4, cliente.getSexo());
			ps.setString(5, cliente.getAtivo());
			ps.execute();
			ps.close();
			conn.close();
			
			JOptionPane.showMessageDialog(null, "Cliente "+ cliente.getNome()+ " cadastrado com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar :"+e);
		}
	}
	
	public void alterar(Cliente cliente) {
		try {
			String sql ="update cliente set nome = ?, cpf = ?, rg = ?, sexo = ?, ativo = ? where codigo = ? ";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getRg());
			ps.setString(4, cliente.getSexo());
			ps.setString(5, cliente.getAtivo());
			ps.setInt(6, cliente.getCodigo());
			ps.execute();
			ps.close();
			conn.close();
			
			JOptionPane.showMessageDialog(null, "Cliente "+ cliente.getNome()+ " alterado com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar :"+e);
		}
	}
	
	public List<Cliente> listarCliente(){
		List<Cliente>listaCliente = new ArrayList<>();
		try {
			String sql ="SELECT codigo, nome, cpf, rg, \r\n" + 
					"CASE \r\n" + 
					"	WHEN sexo = 'M' THEN 'MASCULINO'\r\n" + 
					"	WHEN sexo = 'F' THEN 'FEMININO'\r\n" + 
					"	end sexo, \r\n" + 
					"CASE \r\n" + 
					"	WHEN ativo = '1' THEN 'SIM'\r\n" + 
					"	WHEN ativo = '0' THEN 'NÃO'\r\n" + 
					"	end ativo \r\n" + 
					"FROM cliente where d_e_l_e_t_e is null ";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setRg(rs.getString("rg"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setAtivo(rs.getString("ativo"));
				listaCliente.add(cliente);
			}
			return listaCliente;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar :"+e);
		}
		return listaCliente;
	}
	
	public List<Cliente> pesquisarCliente(String pesquisar){
		List<Cliente>listaCliente = new ArrayList<>();
		try {
			String sql ="SELECT codigo, nome, cpf, rg, \r\n" + 
					"CASE \r\n" + 
					"	WHEN sexo = 'M' THEN 'MASCULINO'\r\n" + 
					"	WHEN sexo = 'F' THEN 'FEMININO'\r\n" + 
					"	end sexo, \r\n" + 
					"CASE \r\n" + 
					"	WHEN ativo = '1' THEN 'SIM'\r\n" + 
					"	WHEN ativo = '0' THEN 'NÃO'\r\n" + 
					"	end ativo \r\n" + 
					"FROM cliente where d_e_l_e_t_e is null and (cliente.codigo = ? or  nome like ? )  ";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  pesquisar);
			ps.setString(2,  pesquisar + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setRg(rs.getString("rg"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setAtivo(rs.getString("ativo"));
				listaCliente.add(cliente);
			}
			return listaCliente;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar :"+e);
		}
		return listaCliente;
	}
	
	public void remover (int codigo) {
		try {
			String sql ="update cliente set  d_e_l_e_t_e = 's' where codigo = ?";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ps.execute();
			ps.close();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao remover :"+e);
		}
	}
	
	public Cliente  retornaCliente(int codigo) {
		Cliente cliente = new Cliente();
		try {
			String sql ="select * from cliente where codigo = ? ";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setRg(rs.getString("rg"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setAtivo(rs.getString("ativo"));
			}
			rs.close();
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao retornar cliente :"+e);
		}
		return  cliente;
	}
}
