package br.com.chat.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.chat.db.Conexao;
import br.com.chat.entity.Usuario;

public class UsuarioDao {
	private static final Logger LOGGER = Logger.getLogger(UsuarioDao.class.getName());
	
	private Conexao conexao;
	
	public UsuarioDao() {
		conexao = new Conexao();
	}
	
    public Optional<Usuario> autenticar(String username, String senha) {
        Optional<Usuario> optional = Optional.empty();

        try {
            String sql = "SELECT * FROM usuarios WHERE username = ? AND senha = ?";

            PreparedStatement preparedStatement = conexao.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, senha);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                optional = Optional.of(new Usuario());

                optional.get().setId(resultSet.getInt("id"));
                
                optional.get().setNome(resultSet.getString("nome"));
                
                optional.get().setUsername(resultSet.getString("username"));
                
                optional.get().setEmail(resultSet.getString("email"));
                
                optional.get().getFoto().setImagem(resultSet.getString("foto_perfil"));
            }

            resultSet.close();

        } catch(SQLException sqlException) {
        	LOGGER.log(Level.SEVERE, sqlException.getMessage());
        }

        return optional;
    }
    
    public int cadastrar(Usuario usuario) {
    	try {
	    	String sql = "INSERT INTO usuarios (nome, username, email, senha, foto_perfil) VALUES (?, ?, ?, ?, ?)";
	
	        PreparedStatement preparedStatement = conexao.getConnection().prepareStatement(sql);
	
	        preparedStatement.setString(1, usuario.getNome());
	        preparedStatement.setString(2, usuario.getUsername());
	        preparedStatement.setString(3, usuario.getEmail());
	        preparedStatement.setString(4, usuario.getSenha());
	        preparedStatement.setString(5, usuario.getFoto().getImagem());
	
	        return preparedStatement.executeUpdate();
	        
    	} catch(SQLException sqlException) {    		
        	LOGGER.log(Level.SEVERE, sqlException.getMessage());
        	
        	return -1;
        }
    }
    
    public List<Usuario> buscarTodosAmigos(int id) {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String sql = "SELECT * FROM usuarios WHERE id <> ? ORDER BY nome";

            PreparedStatement preparedStatement = conexao.getConnection().prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(resultSet.getInt("id"));
                
                usuario.setNome(resultSet.getString("nome"));
                
                usuario.setUsername(resultSet.getString("username"));
                
                usuario.getFoto().setImagem(resultSet.getString("foto_perfil"));

                usuarios.add(usuario);
            }

            return usuarios;
            
        } catch(SQLException sqlException) {
        	LOGGER.log(Level.SEVERE, sqlException.getMessage());
        	
            return List.of();
        }
    }
    
    public List<Usuario> buscarAmigosPeloNome(String busca, int id) {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String sql = "SELECT * FROM usuarios WHERE id <> ? AND nome LIKE ? ORDER BY nome";

            PreparedStatement preparedStatement = conexao.getConnection().prepareStatement(sql);

            preparedStatement.setInt(1, id);
            
            preparedStatement.setString(2, "%" + busca + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(resultSet.getInt("id"));
                
                usuario.setNome(resultSet.getString("nome"));
                
                usuario.setUsername(resultSet.getString("username"));
                
                usuario.getFoto().setImagem(resultSet.getString("foto_perfil"));

                usuarios.add(usuario);
            }

            return usuarios;
            
        } catch(SQLException sqlException) {        	
            return List.of();
        }
    }
}