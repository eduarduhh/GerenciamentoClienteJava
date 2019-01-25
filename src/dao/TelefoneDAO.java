package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.Conexao;
import model.Telefone;

public class TelefoneDAO {

	public void inserir(Telefone telefone) {
		try {
			String sql = "insert into telefone(tipo, numero, codigo_cliente) values (?, ?, ?) ";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, telefone.getTipo());
			ps.setString(2, telefone.getNumero());
			ps.setInt(3, telefone.getCliente().getCodigo());
			ps.execute();
			ps.close();
			conn.close();
			JOptionPane.showMessageDialog(null, "O telefone " + telefone.getNumero() + "foi cadastrado com Sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problemas no cadastro "+e);
		}
	}

	public void alterar(Telefone telefone) {
		try {
			String sql = "UPDATE telefone SET tipo = ?, numero = ? where codigo = ?";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, telefone.getTipo());
			ps.setString(2, telefone.getNumero());
			ps.setInt(3, telefone.getCodigo());
			ps.execute();
			ps.close();
			conn.close();
			JOptionPane.showMessageDialog(null, "O telefone " + telefone.getNumero() + "foi alterado com Sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problemas no alterar ");
		}
	}

	public List<Telefone> listarPorCliente(int codigo) {
		List<Telefone> listarTodos = new ArrayList<>();

		try {
			String sql = "SELECT  codigo, tipo, numero from telefone where codigo_cliente = ? and d_e_l_e_t_e is null ";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Telefone telefone = new Telefone();
				telefone.setCodigo(rs.getInt("codigo"));
				telefone.setTipo(rs.getString("tipo"));
				telefone.setNumero(rs.getString("numero"));
				listarTodos.add(telefone);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problemas no listar telefone ");
		}
		return listarTodos;
	}
	
	public void remover(int codigo) {
		try {
			String sql = "update telefone set d_e_l_e_t_e = 's' where codigo = ?";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ps.execute();
			ps.close();
			conn.close();
			JOptionPane.showMessageDialog(null, "O telefone foi removido com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao remover telefone "+e);
		}
	}

}
