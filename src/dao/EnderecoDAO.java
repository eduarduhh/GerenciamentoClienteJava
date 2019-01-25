package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.Conexao;
import model.Endereco;

public class EnderecoDAO {
	
	public void inserir(Endereco endereco) {
		
		try {
			String sql  = "insert into endereco (tipo, endereco, bairro, cidade, cep, uf, codigo_cliente) values (? ,? ,? ,? ,? ,? ,?)";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, endereco.getTipo());
			ps.setString(2, endereco.getEndereco());
			ps.setString(3, endereco.getTipo());
			ps.setString(4, endereco.getCidade());
			ps.setString(5, endereco.getCep());
			ps.setString(6, endereco.getUf());
			ps.setInt(7, endereco.getCliente().getCodigo());
			ps.execute();
			ps.close();
			conn.close();
			JOptionPane.showMessageDialog(null, "Endereco" + endereco.getEndereco() + " foi cadastrado com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problemas no cadastro "+e);
		}
		
	}
	
public void alterar(Endereco endereco) {
		
		try {
			String sql  = "update endereco set tipo = ?, endereco =?, bairro = ?, "
					                                   + "cidade = ?, cep = ?, uf =? "
					                                   + "where codigo = ?";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, endereco.getTipo());
			ps.setString(2, endereco.getEndereco());
			ps.setString(3, endereco.getTipo());
			ps.setString(4, endereco.getCidade());
			ps.setString(5, endereco.getCep());
			ps.setString(6, endereco.getUf());
			ps.setInt(7, endereco.getCodigo());
			ps.execute();
			ps.close();
			conn.close();
			JOptionPane.showMessageDialog(null, "Endereco" + endereco.getEndereco() + " foi cadastrado com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problemas no cadastro "+e);
		}
		
	}

public List<Endereco> listarPorCliente(int codigo) {
	List<Endereco> listarTodos = new ArrayList<>();

	try {
		String sql = "select codigo, tipo, endereco, bairro, cidade, cep, uf from endereco where codigo_cliente  = ?" + 
				" and d_e_l_e_t_e is null ";
		Connection conn = Conexao.getConexao();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, codigo);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Endereco endereco = new Endereco();
			endereco.setCodigo(rs.getInt("codigo"));
			endereco.setTipo(rs.getString("tipo"));
			endereco.setEndereco(rs.getString("endereco"));
			endereco.setBairro(rs.getString("bairro"));
			endereco.setCidade(rs.getString("cidade"));
			endereco.setCep(rs.getString("cep"));
			endereco.setUf(rs.getString("uf"));
			listarTodos.add(endereco);	
		}
		rs.close();
		ps.close();
		conn.close();
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Problemas no listar endereço ");
	}
	return listarTodos;
}
public void remover(int codigo) {
	
	try {
		String sql  = "update endereco SET d_e_l_e_t_e = 's' where codigo =  ?";
		Connection conn = Conexao.getConexao();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, codigo);
		ps.execute();
		ps.close();
		conn.close();
		JOptionPane.showMessageDialog(null, "Removido com sucesso");
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Problemas ao remover "+e);
	}
	
}

}
