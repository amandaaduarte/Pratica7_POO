import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Conexão com o banco de dados estabelecida.");

            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                exibirMenu();
                System.out.print("Digite a opção desejada: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        cadastrarLivro(conn, scanner);
                        break;
                    case 2:
                        pesquisarLivroPorPreco(conn, scanner);
                        break;
                    case 3:
                        pesquisarLivroPorTitulo(conn, scanner);
                        break;
                    case 4:
                        excluirLivro(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida. Digite um número de 1 a 5.");
                        break;
                }
            } while (opcao != 5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void excluirLivro(Connection conn, Scanner scanner) {

    }

    private static void exibirMenu() {
        System.out.println("----- MENU -----");
        System.out.println("<1> Cadastrar Livro");
        System.out.println("<2> Pesquisar Livro por Preço");
        System.out.println("<3> Pesquisar Livro por Título");
        System.out.println("<4> Excluir Livro");
        System.out.println("<5> Sair");
        System.out.println("-----------------");
    }

    private static void cadastrarLivro(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("----- CADASTRO DE LIVRO -----");

        System.out.print("Digite o título do livro: ");
        String titulo = scanner.next();

        System.out.print("Digite o autor do livro: ");
        String autor = scanner.next();

        System.out.print("Digite o preço do livro: ");
        double preco = scanner.nextDouble();

        String sql = "INSERT INTO livros (titulo, autor, vl_preco) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, titulo);
        statement.setString(2, autor);
        statement.setDouble(3, preco);
        statement.executeUpdate();

        System.out.println("Livro cadastrado com sucesso!");
        System.out.println("-----------------------------");
    }

    private static void pesquisarLivroPorPreco(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("----- PESQUISA POR PREÇO -----");

        System.out.print("Digite o valor mínimo do preço: ");
        double valorMinimo = scanner.nextDouble();

        String sql = "SELECT * FROM livros WHERE vl_preco >= ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDouble(1, valorMinimo);
        ResultSet resultSet = statement.executeQuery();


        System.out.println("-------------------------------");
    }

    private static void pesquisarLivroPorTitulo(Connection conn, Scanner scanner) throws SQLException{
        System.out.println("----- PESQUISA POR TÍTULO -----");
    }


    }