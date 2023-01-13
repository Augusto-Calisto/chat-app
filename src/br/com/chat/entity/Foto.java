package br.com.chat.entity;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Base64;

public class Foto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String imagem;

    public ByteArrayInputStream getBytes() {
        byte[] fotoDePerfilCarregada = Base64.getDecoder().decode(this.imagem);
        return new ByteArrayInputStream(fotoDePerfilCarregada);
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}