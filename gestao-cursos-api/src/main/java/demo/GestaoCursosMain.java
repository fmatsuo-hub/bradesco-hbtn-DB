package demo;

import entities.*;
import models.*;

public class GestaoCursosMain {
    public static void main(String[] args) {
        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();

        // Criando professor
        Professor professor = new Professor();
        professor.setNome("Carlos Silva");

        // Criando material
        MaterialCurso material = new MaterialCurso();
        material.setUrl("http://material-curso.com/java");

        // Criando curso
        Curso curso = new Curso();
        curso.setNome("Java Avançado");
        curso.setProfessor(professor);
        curso.setMaterial(material);

        // Criando aluno
        Aluno aluno = new Aluno();
        aluno.setNome("João Souza");

        Endereco endereco = new Endereco();
        endereco.setRua("Rua A");
        endereco.setAluno(aluno);

        Telefone telefone = new Telefone();
        telefone.setNumero("99999-9999");
        telefone.setAluno(aluno);

        // Adicionando às listas (já inicializadas nas entidades)
        aluno.getEnderecos().add(endereco);
        aluno.getTelefones().add(telefone);

        curso.getAlunos().add(aluno);
        aluno.getCursos().add(curso);

        // Salvando aluno (cascata salva endereço e telefone também)
        alunoModel.create(aluno);

        // Salvando curso
        cursoModel.create(curso);

        System.out.println("Dados salvos com sucesso!");
    }
}
