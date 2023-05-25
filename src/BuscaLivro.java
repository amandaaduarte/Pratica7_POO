import java.sql.*;

public class BuscaLivro {
    public static void main(String[] args) {
         String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
         String user = "postgres";
         String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String tituloBusca = "Início do texto do título";

            String sql = "SELECT * FROM livro WHERE titulo LIKE ?";  // Utilizamos o LIKE para fazer uma busca por uma parte do título
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, tituloBusca + "%");  // Usamos o % para buscar qualquer título que comece com o texto especificado

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");


                System.out.println("ID: " + id);
                System.out.println("Título: " + titulo);
                System.out.println("Autor: " + autor);
                System.out.println("----------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
