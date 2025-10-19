package models;

import entities.Pessoa;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

public class PessoaModel {
    
    public void create(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa criada com sucesso !!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao criar a pessoa !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }
    
    public void update(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            System.out.println("Iniciando a transação de atualização");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Pessoa atualizada com sucesso !!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao atualizar a pessoa !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }
    
    public void delete(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            System.out.println("Iniciando a transação de exclusão");
            em.getTransaction().begin();
            Pessoa pessoaToRemove = em.find(Pessoa.class, p.getId());
            if (pessoaToRemove != null) {
                em.remove(pessoaToRemove);
            }
            em.getTransaction().commit();
            System.out.println("Pessoa excluída com sucesso !!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao excluir a pessoa !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }
    
    public Pessoa findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Pessoa.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar pessoa por ID !!!" + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public List<Pessoa> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        
        try {
            Query query = em.createQuery("SELECT p FROM Pessoa p");
            pessoas = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todas as pessoas !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        
        return pessoas;
    }
}