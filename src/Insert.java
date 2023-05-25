import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Insert {
    String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
    String user = "postgres";
     String password = "123456";

    String INSERT_USERS_SQL = "INSERT INTO livro" +
            "  (id_isbn, id_categoria, id_editora, nm_titulo, dt_publicacao, nu_edicao, nu_volume, vl_preco, im_foto_capa) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static void main(String[] argv) throws SQLException {
        Insert livro = new Insert();
        livro.insertRecord();
    }

    public void insertRecord() throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, 17);
            preparedStatement.setString(2, 1);
            preparedStatement.setString(3, 1);
            preparedStatement.setString(4, "ADS e Sistema da informacao");
            preparedStatement.setString(5, "2023-05-18");
            preparedStatement.setString(6, 1);
            preparedStatement.setString(7, 2);
            preparedStatement.setString(8, 500.00);
            preparedStatement.setString(9, "");

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}