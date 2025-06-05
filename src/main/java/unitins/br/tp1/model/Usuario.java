package unitins.br.tp1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Usuario extends DefaultEntity {
    @Column(length = 60, nullable = false)
    private String nome;
    @Column(length = 100, unique = true)
    private String email;
    @Column(length = 88, nullable = false)
    private String senha;

    private Perfil perfil;

    @OneToOne
    @JoinColumn(name = "id_telefone", unique = true)
    private Telefone telefone;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
