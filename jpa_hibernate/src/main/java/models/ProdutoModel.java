package models;

import entities.Produto;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

public class ProdutoModel {
    
    public void create(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }
    
    public void update(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            System.out.println("Iniciando a transação de atualização");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Produto atualizado com sucesso !!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao atualizar o produto !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }
    
    public void delete(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            System.out.println("Iniciando a transação de exclusão");
            em.getTransaction().begin();
            Produto produtoToRemove = em.find(Produto.class, p.getId());
            if (produtoToRemove != null) {
                em.remove(produtoToRemove);
            }
            em.getTransaction().commit();
            System.out.println("Produto excluído com sucesso !!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao excluir o produto !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }
    
    public Produto findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Produto.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar produto por ID !!!" + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public List<Produto> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        
        List<Produto> produtos = new ArrayList<Produto>();
        
        try {
            Query query = em.createQuery("SELECT p FROM Produto p");
            produtos = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os produtos !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        
        return produtos;
    }
}