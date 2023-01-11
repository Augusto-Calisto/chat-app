package br.com.chat.entity;

public class Usuario {
    private int id;
    private String nome;
    private String username;
    private String email;
    private String senha;
    private Foto foto;

    public Usuario() {
        this.foto = new Foto();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Foto getFoto() {
        return foto;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}