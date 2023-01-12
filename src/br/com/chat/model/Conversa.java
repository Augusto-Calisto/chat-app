package br.com.chat.model;

import java.io.Serializable;

public class Conversa implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idUsuarioRemetente;
    private int idUsuarioDestinatario;
    private String texto;

    public String getTexto() {
        return texto;
    }

    public Conversa setTexto(String texto) {
        this.texto = texto;
        return this;
    }

    public int getIdUsuarioRemetente() {
        return idUsuarioRemetente;
    }

    public Conversa setIdUsuarioRemetente(int idUsuarioRemetente) {
        this.idUsuarioRemetente = idUsuarioRemetente;
        return this;
    }

    public int getIdUsuarioDestinatario() {
        return idUsuarioDestinatario;
    }

    public Conversa setIdUsuarioDestinatario(int idUsuarioDestinatario) {
        this.idUsuarioDestinatario = idUsuarioDestinatario;
        return this;
    }

    public boolean hasDestinatario() {
        return idUsuarioDestinatario > 0;
    }

    @Override
    public String toString() {
        return "Conversa { \n" +
                "      idUsuarioRemetente: " + idUsuarioRemetente + ", \n" +
                "      idUsuarioDestinatario: " + idUsuarioDestinatario + ", \n" +
                "      texto: '" + texto + "' \n" +
                "}";
    }
}