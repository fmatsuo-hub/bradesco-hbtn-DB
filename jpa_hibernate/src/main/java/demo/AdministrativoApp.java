package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;
import java.time.LocalDate;
import java.util.List;

public class AdministrativoApp {

    public static void main(String[] args) {
        // Testando ProdutoModel
        ProdutoModel produtoModel = new ProdutoModel();
        
        // 1) Criando produtos
        Produto p1 = new Produto();
        p1.setNome("TV Samsung 55\"");
        p1.setPreco(2500.0);
        p1.setQuantidade(10);
        p1.setStatus(true);
        
        Produto p2 = new Produto();
        p2.setNome("Notebook Dell");
        p2.setPreco(3500.0);
        p2.setQuantidade(5);
        p2.setStatus(true);
        
        System.out.println("=== CRIANDO PRODUTOS ===");
        produtoModel.create(p1);
        produtoModel.create(p2);
        
        // 2) Buscando todos os produtos
        System.out.println("\n=== BUSCANDO TODOS OS PRODUTOS ===");
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados: " + produtos.size());
        for (Produto p : produtos) {
            System.out.println(p);
        }
        
        // 3) Buscando produto por ID
        System.out.println("\n=== BUSCANDO PRODUTO POR ID ===");
        if (!produtos.isEmpty()) {
            Produto produtoEncontrado = produtoModel.findById(produtos.get(0).getId());
            System.out.println("Produto encontrado: " + produtoEncontrado);
            
            // 4) Atualizando produto
            System.out.println("\n=== ATUALIZANDO PRODUTO ===");
            produtoEncontrado.setPreco(2700.0);
            produtoEncontrado.setQuantidade(8);
            produtoModel.update(produtoEncontrado);
        }
        
        // Testando PessoaModel
        PessoaModel pessoaModel = new PessoaModel();
        
        // 1) Criando pessoas
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("João Silva");
        pessoa1.setEmail("joao.silva@email.com");
        pessoa1.setIdade(30);
        pessoa1.setCpf("123.456.789-00");
        pessoa1.setDataNascimento(LocalDate.of(1993, 5, 15));
        
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Maria Santos");
        pessoa2.setEmail("maria.santos@email.com");
        pessoa2.setIdade(25);
        pessoa2.setCpf("987.654.321-00");
        pessoa2.setDataNascimento(LocalDate.of(1998, 8, 22));
        
        System.out.println("\n=== CRIANDO PESSOAS ===");
        pessoaModel.create(pessoa1);
        pessoaModel.create(pessoa2);
        
        // 2) Buscando todas as pessoas
        System.out.println("\n=== BUSCANDO TODAS AS PESSOAS ===");
        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontradas: " + pessoas.size());
        for (Pessoa p : pessoas) {
            System.out.println(p);
        }
        
        // 3) Buscando pessoa por ID
        System.out.println("\n=== BUSCANDO PESSOA POR ID ===");
        if (!pessoas.isEmpty()) {
            Pessoa pessoaEncontrada = pessoaModel.findById(pessoas.get(0).getId());
            System.out.println("Pessoa encontrada: " + pessoaEncontrada);
            
            // 4) Atualizando pessoa
            System.out.println("\n=== ATUALIZANDO PESSOA ===");
            pessoaEncontrada.setEmail("joao.novo@email.com");
            pessoaEncontrada.setIdade(31);
            pessoaModel.update(pessoaEncontrada);
        }
        
        // 5) Testando exclusão
        System.out.println("\n=== TESTANDO EXCLUSÃO ===");
        if (!pessoas.isEmpty() && pessoas.size() > 1) {
            Pessoa pessoaParaExcluir = pessoas.get(1);
            System.out.println("Excluindo pessoa: " + pessoaParaExcluir.getNome());
            pessoaModel.delete(pessoaParaExcluir);
        }
        
        // Listando dados finais
        System.out.println("\n=== DADOS FINAIS ===");
        System.out.println("Produtos no banco: " + produtoModel.findAll().size());
        System.out.println("Pessoas no banco: " + pessoaModel.findAll().size());
    }
}