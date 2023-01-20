package br.com.chat.dao;

import br.com.chat.db.Conexao;
import br.com.chat.model.Conversa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDao {
    private Conexao conexao;
    
    public HistoricoDao() {
        conexao = new Conexao();
    }

    public void salvarConversa(Conversa conversa) {
        try {
            String sql = "INSERT INTO historico_conversas (id_usuario_remetente, id_usuario_destinatario, mensagem) VALUES (?, ?, ?)";

            if(conversa.getTexto() != null) {
                PreparedStatement preparedStatement = conexao.getConnection().prepareStatement(sql);

                preparedStatement.setInt(1, conversa.getIdUsuarioRemetente());
                
                preparedStatement.setInt(2, conversa.getIdUsuarioDestinatario());
                
                preparedStatement.setString(3, conversa.getTexto());

                preparedStatement.executeUpdate();
            }
        } catch(SQLException exception) {
            exception.getStackTrace();
        }
    }

    public List<Conversa> getHistoricoConversas(int idUsuarioRemetente, int idUsuarioDestinatario) {
        List<Conversa> conversas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM historico_conversas WHERE id_usuario_remetente = ? AND id_usuario_destinatario = ? OR id_usuario_remetente = ? AND id_usuario_destinatario = ? ORDER BY data_hora";

            PreparedStatement preparedStatement = conexao.getConnection().prepareStatement(sql);

            preparedStatement.setInt(1, idUsuarioRemetente);
            preparedStatement.setInt(2, idUsuarioDestinatario);
            preparedStatement.setInt(3, idUsuarioDestinatario);
            preparedStatement.setInt(4, idUsuarioRemetente);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Conversa conversa = new Conversa();

                conversa.setIdUsuarioRemetente(rs.getInt("id_usuario_remetente"));
                
                conversa.setIdUsuarioDestinatario(rs.getInt("id_usuario_destinatario"));
                
                conversa.setTexto(rs.getString("mensagem"));

                conversas.add(conversa);
            }

            return conversas;

        } catch(SQLException exception) {
            return List.of();
        }
    }
}