package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "pessoa")
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    @Column(name = "email", unique = true, length = 150)
    private String email;
    
    @Column(name = "idade")
    private Integer idade;
    
    @Column(name = "cpf", unique = true, length = 14)
    private String cpf;
    
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    
    // Construtores
    public Pessoa() {}
    
    public Pessoa(String nome, String email, Integer idade, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public Integer getIdade() {
        return idade;
    }
    
    public void setIdade(Integer idade) {
        this.idade = idade;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    @Override
    public String toString() {
        return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + 
               ", idade=" + idade + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + "]";
    }
}