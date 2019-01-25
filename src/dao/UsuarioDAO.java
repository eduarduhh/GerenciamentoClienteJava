package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.Conexao;
import model.Usuario;

public class UsuarioDAO {
	
	public void inserir(Usuario usuario) {
		try {
			String sql ="insert into usuario (usuario,senha, nome, ativo , nivel) values (?, ?, ?, ?, ?) " ;
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getNome());
			ps.setString(4, usuario.getAtivo());
			ps.setString(5, usuario.getNivel());
			ps.execute();
			ps.close();
			conn.close();
			JOptionPane.showMessageDialog(null,  usuario.getUsuario()+" cadastrado com Sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar :"+e);
		}
	}
	
	public void alterar(Usuario usuario) {
		try {
			String sql ="update usuario set usuario = ?, senha = ?, nome = ?, ativo = ?, nivel = ? where codigo = ?" ;
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getNome());
			ps.setString(4, usuario.getAtivo());
			ps.setString(5, usuario.getNivel());
			ps.setInt(6, usuario.getCodigo());
			ps.execute();
			ps.close();
			conn.close();
			JOptionPane.showMessageDialog(null,  usuario.getUsuario()+" alterado com Sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao alterar :"+e);
		}
	}
	
	public List<Usuario> listarUsuario(){
		List<Usuario>listaUsuario = new ArrayList<>();
		try {
			String sql ="SELECT codigo, usuario, senha, nome , \r\n" + 
					"CASE \r\n" + 
					"	WHEN ativo = '1' THEN 'SIM'\r\n" + 
					"	WHEN ativo = '0' THEN 'NÃO'\r\n" + 
					"	end ativo, \r\n" +
					"CASE \r\n" + 
					"	WHEN nivel = '1' THEN 'ADMIN'\r\n" + 
					"	WHEN nivel = '2' THEN 'USUÁRIO'\r\n" + 
					"	end nivel \r\n" +
					"FROM usuario where d_e_l_e_t_e is null ";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setCodigo(rs.getInt("codigo"));
				usuario.setNome(rs.getString("nome"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAtivo(rs.getString("ativo"));
				usuario.setNivel(rs.getString("nivel"));
				listaUsuario.add(usuario);
			}
			return listaUsuario;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar :"+e);
		}
		return listaUsuario;
	}
	
	
	public List<Usuario> pesquisarUsuario(String nome){
		List<Usuario>listaUsuario = new ArrayList<>();
		try {
			String sql ="SELECT codigo, usuario, senha, nome ," + 
					"CASE " + 
					"	WHEN ativo = '1' THEN 'SIM'" + 
					"	WHEN ativo = '0' THEN 'NÃO'" + 
					"	end ativo, " + 
					"CASE \r\n" + 
					"	WHEN nivel = '1' THEN 'ADMIN'\r\n" + 
					"	WHEN nivel = '2' THEN 'USUÁRIO'\r\n" + 
					"	end nivel \r\n" +
					"FROM usuario " + 
					"WHERE nome like ? or usuario like ? and d_e_l_e_t_e is null ";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,"%"+ nome +"%" );
			ps.setString(2,"%"+ nome +"%" );
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setCodigo(rs.getInt("codigo"));
				usuario.setNome(rs.getString("nome"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAtivo(rs.getString("ativo"));
				usuario.setNivel(rs.getString("nivel"));
				listaUsuario.add(usuario);
			}
			return listaUsuario;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar :"+e);
		}
		return listaUsuario;
	}
	public void remover(int codigo) {
		try {
			String sql = "update   usuario  set d_e_l_e_t_e = 's' where codigo = ? ";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codigo);
			ps.execute();
			ps.close();
			conn.close();
			JOptionPane.showMessageDialog(null, "Removido com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao remover :"+e);
		}
	}
	
	public boolean verficiaUsuarioSenha(String nome, String senha) {
		boolean resultado = false;
			
		try {
			String sql ="select * from usuario where nome = ? and senha = ?";
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, senha);
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
	
	public void alterarSenha(String nome , String senha, String novaSenha) {
		try {
			String sql ="update usuario  set senha = ? where nome = ? and senha =  ? " ;
			Connection conn = Conexao.getConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, novaSenha);
			ps.setString(2, nome);
			ps.setString(3, senha);
			ps.execute();
			ps.close();
			conn.close();
			JOptionPane.showMessageDialog(null,"Senha alterada com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao alterar senha :"+e);
		}
	}
}
