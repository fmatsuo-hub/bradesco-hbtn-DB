import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class UsuarioOperations {
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public UsuarioOperations(MongoDatabase database) {
        this.database = database;
        this.collection = database.getCollection("usuarios");
    }

    public void inserirUsuarios() {
        try {
            Usuario alice = new Usuario("Alice", 25);
            Usuario bob = new Usuario("Bob", 30);
            Usuario charlie = new Usuario("Charlie", 35);

            List<Document> usuarios = Arrays.asList(
                alice.toDocument(),
                bob.toDocument(),
                charlie.toDocument()
            );

            collection.insertMany(usuarios);
            System.out.println("✓ 3 usuários inseridos com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao inserir usuários: " + e.getMessage());
        }
    }

    public void consultarUsuarios() {
        try {
            System.out.println("\n--- CONSULTA DE USUÁRIOS ---");
            for (Document doc : collection.find()) {
                Usuario usuario = Usuario.fromDocument(doc);
                System.out.println(usuario);
            }
        } catch (Exception e) {
            System.err.println("Erro ao consultar usuários: " + e.getMessage());
        }
    }

    public void atualizarIdadeBob() {
        try {
            UpdateResult result = collection.updateOne(
                Filters.eq("nome", "Bob"),
                Updates.set("idade", 32)
            );

            if (result.getModifiedCount() > 0) {
                System.out.println("\n✓ Idade do Bob atualizada para 32 anos!");
            } else {
                System.out.println("\n✗ Bob não encontrado para atualização.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar Bob: " + e.getMessage());
        }
    }

    public void deletarCharlie() {
        try {
            DeleteResult result = collection.deleteOne(Filters.eq("nome", "Charlie"));

            if (result.getDeletedCount() > 0) {
                System.out.println("\n✓ Charlie deletado com sucesso!");
            } else {
                System.out.println("\n✗ Charlie não encontrado para deleção.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao deletar Charlie: " + e.getMessage());
        }
    }

    public void executarTodasOperacoes() {
        System.out.println("=== INICIANDO OPERAÇÕES MONGODB ===\n");

        System.out.println("1. INSERINDO 3 REGISTROS:");
        inserirUsuarios();

        System.out.println("\n2. CONSULTA INICIAL:");
        consultarUsuarios();

        System.out.println("\n3. ATUALIZANDO IDADE DO BOB:");
        atualizarIdadeBob();

        System.out.println("\n4. CONSULTA APÓS ATUALIZAÇÃO:");
        consultarUsuarios();

        System.out.println("\n5. DELETANDO CHARLIE:");
        deletarCharlie();

        System.out.println("\n6. CONSULTA FINAL:");
        consultarUsuarios();

        System.out.println("\n=== OPERAÇÕES CONCLUÍDAS ===");
    }

    public static void main(String[] args) {
        MongoDBConnection mongoConnection = new MongoDBConnection();
        MongoDatabase database = mongoConnection.getDatabase();

        if (database != null) {
            UsuarioOperations operations = new UsuarioOperations(database);
            operations.executarTodasOperacoes();

            mongoConnection.closeConnection();
        }
    }
}