package models;

import entities.Aluno;
import entities.Curso;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CursoModel {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("gestao-cursos-jpa");

    public void create(Curso curso) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Anexa alunos existentes (evita "detached entity passed to persist")
            List<Aluno> anexados = new ArrayList<>();
            for (Aluno a : curso.getAlunos()) {
                if (a.getId() != null) {
                    anexados.add(em.getReference(Aluno.class, a.getId()));
                } else {
                    anexados.add(a); // novo aluno (não é nosso caso aqui)
                }
            }
            curso.getAlunos().clear();
            curso.getAlunos().addAll(anexados);

            em.persist(curso); // cascade salva material; professor será novo ou já existente
            em.getTransaction().commit();
            System.out.println("Curso criado!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally { em.close(); }
    }

    public Curso findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try { return em.find(Curso.class, id); }
        finally { em.close(); }
    }

    public List<Curso> findAll() {
        EntityManager em = emf.createEntityManager();
        try { return em.createQuery("FROM Curso", Curso.class).getResultList(); }
        finally { em.close(); }
    }

    public void update(Curso curso) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
            System.out.println("Curso atualizado!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally { em.close(); }
    }

    public void delete(Curso curso) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Curso managed = em.merge(curso);
            // Opcional: limpar relação N:M antes de remover
            managed.getAlunos().clear();
            em.remove(managed);
            em.getTransaction().commit();
            System.out.println("Curso removido!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally { em.close(); }
    }
}
