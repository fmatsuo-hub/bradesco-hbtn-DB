package models;

import entities.Aluno;

import javax.persistence.*;
import java.util.List;

public class AlunoModel {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("gestao-cursos-jpa");

    public void create(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally { em.close(); }
    }

    public Aluno findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try { return em.find(Aluno.class, id); }
        finally { em.close(); }
    }

    public List<Aluno> findAll() {
        EntityManager em = emf.createEntityManager();
        try { return em.createQuery("FROM Aluno", Aluno.class).getResultList(); }
        finally { em.close(); }
    }

    public void update(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally { em.close(); }
    }

    public void delete(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Aluno managed = em.merge(aluno);
            em.remove(managed);
            em.getTransaction().commit();
            System.out.println("Aluno removido!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally { em.close(); }
    }
}
