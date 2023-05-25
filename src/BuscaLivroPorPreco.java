import java.sql.*;

public class BuscaLivroPorPreco {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
        String user = "postgres";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            double precoMinimo = 50.0;

            String sql = "SELECT * FROM livro WHERE vl_preco >= ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1, precoMinimo);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                double preco = resultSet.getDouble("vl_preco");

                System.out.println("ID: " + id);
                System.out.println("Título: " + titulo);
                System.out.println("Autor: " + autor);
                System.out.println("Preço: " + preco);
                System.out.println("----------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
