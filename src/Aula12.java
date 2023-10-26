import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class Aula12 {

    /////////// MENU ///////////
    public static void menu() {
        System.out.println("\n");
        System.out.println("----- AUTORES -----");
        System.out.println("1 - Listar Autores");
        System.out.println("2 - Cadastrar Autores");
        System.out.println("3 - Alterar Autores");
        System.out.println("4 - Deletar Autores");
        System.out.println("----- LIVROS -----");
        System.out.println("5 - Listar Livros");
        System.out.println("6 - Cadastrar Livros");
        System.out.println("7 - Alterar Livros");
        System.out.println("8 - Deletar Livros");
        System.out.println("0 - Sair");
        System.out.println("\n");
    }

    /////////// LISTAR ///////////
    public static void listarAutor() {
        Connection connection;
        Statement stmt;
        //PreparedStatement rs;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aulapooii",
                    "root",
                    "3236"
            );
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM authors");

            while (((ResultSet) rs).next()) {
                System.out.println("Codigo do autor: " + ((ResultSet) rs).getInt(1));
                System.out.println("Nome do autor: " + ((ResultSet) rs).getString(2));
                System.out.println("Sobrenome do autor: " + ((ResultSet) rs).getString(3));
                System.out.println("_____________________________________");
            }
        } catch (SQLException ex) {
            System.out.println("Erro: não conseguiu conectar no BD");
        }
    }

    public static void listarLivros() {
        Connection connection;
        Statement stmt;
        //PreparedStatement rs;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aulapooii",
                    "root",
                    "3236"
            );
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM titles");

            while(((ResultSet)rs).next()) {
                System.out.println("ISBN: " + ((ResultSet) rs).getInt(1));
                System.out.println("Title: " + ((ResultSet) rs).getString(2));
                System.out.println("EditionNumber: " + ((ResultSet) rs).getInt(3));
                System.out.println("Copyright: " + ((ResultSet) rs).getString(4));
                System.out.println("_____________________________________");
            }
        } catch (SQLException ex) {
            System.out.println("Erro: não conseguiu conectar no BD");
        }
    }

    /////////// INSERIR ///////////
    public static void inserirAutor() {
        Connection connection;
        Statement stmt;
        Scanner teclado = new Scanner(System.in);
        PreparedStatement ps;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aulapooii",
                    "root",
                    "3236");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM authors");

            System.out.println("Digite o nome: ");
            String nome = teclado.next();
            System.out.println("Digite o sobrenome: ");
            String sobrenome = teclado.next();

            int retorno;
            ps = connection.prepareStatement("INSERT INTO authors(firstName, lastName) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nome);
            ps.setString(2, sobrenome);

            retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("\nNovo registro realizado:" + nome + " - " + sobrenome);
            } else {
                System.out.println("Não foi possível criar um novo registro!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro: não conseguiu conectar no BD");
        }
    }

    public static void inserirLivros() {
        Connection connection;
        Statement stmt;
        PreparedStatement ps;
        Scanner teclado = new Scanner(System.in);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aulapooii",
                    "root",
                    "3236");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM titles");

            System.out.println("Digite o nome do livro: ");
            String livro = teclado.next();
            System.out.println("Digite o número da edição: ");
            Integer edicao = teclado.nextInt();
            System.out.println("Digite o nome da editora: ");
            String editora = teclado.next();

            int retorno;
            ps = connection.prepareStatement("INSERT INTO titles(title, editionNumber, copyright) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, livro);
            ps.setInt(2, edicao);
            ps.setString(3, editora);

            retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("\nNovo registro realizado:" + livro + " - " + edicao + " - " + editora);
            } else {
                System.out.println("Não foi possível criar um novo registro!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro: não conseguiu conectar no BD");
        }
    }

    /////////// ALTERAR ///////////
    public static void alterarAutor() {
        Connection connection;
        Statement stmt;
        PreparedStatement ps;
        ResultSet rs;
        Scanner teclado = new Scanner(System.in);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aulapooii",
                    "root",
                    "3236");
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM authors");

            System.out.println("Digite o nome");
            String nome = teclado.next();
            System.out.println("Digite o codigo");
            int codigo = teclado.nextInt();

            int retorno;
            //retorno = stmt.executeUpdate("UPDATE authors SET firstName='"+nome+" " + "' WHERE authorsID="+codigo);
            ps = connection.prepareStatement("UPDATE authors SET firstName=? WHERE authorsID=?");
            ps.setString(1, nome);
            ps.setInt(2, codigo);
            retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("\nNovo registro alterado:" + codigo + " - " + nome);
            } else {
                System.out.println("Não foi possível alterar os registros!");
            }
        }
        catch (SQLException ex) {
            System.out.println("Erro: não conseguiu conectar no BD");
        }
    }

    public static void alterarLivros() {
        Connection connection;
        Statement stmt;
        PreparedStatement ps;
        ResultSet rs;
        Scanner teclado = new Scanner(System.in);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aulapooii",
                    "root",
                    "3236");
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM titles");

            System.out.println("Digite o nome do livro: ");
            String nome = teclado.next();
            System.out.println("Digite o codigo: ");
            int codigo = teclado.nextInt();
            System.out.println("Digite o número da edição: ");
            int edicao = teclado.nextInt();
            System.out.println("Digite a editora do livro: ");
            String editora = teclado.next();

            int retorno;
            //retorno = stmt.executeUpdate("UPDATE authors SET firstName='"+nome+" " + "' WHERE authorsID="+codigo);
            ps = connection.prepareStatement("UPDATE titles SET title=?, editionNumber=?, copyright=? WHERE ISBN=?");
            ps.setString(1, nome);
            ps.setInt(2, codigo);
            ps.setInt(3, edicao);
            ps.setString(4, editora);

            retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("\nNovo registro alterado:" + codigo + " - " + nome + " - " +edicao+ " - " +editora);
            } else {
                System.out.println("Não foi possível alterar os registros!");
            }
        }
        catch (SQLException ex) {
            System.out.println("Erro: não conseguiu conectar no BD");
        }
    }

    /////////// DELETAR ///////////
    public static void deletarAutor() {
        Connection connection;
        Statement stmt;
        PreparedStatement ps;
        ResultSet rs;
        Scanner teclado = new Scanner(System.in);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aulapooii",
                    "root",
                    "3236");
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM authors");
            int codigo;
            System.out.println("Digite o codigo que deseja excluir: ");
            codigo = teclado.nextInt();

            int retorno;
            ps = connection.prepareStatement("DELETE FROM authors WHERE authorsID=?");
            ps.setInt(1, codigo);
            retorno = ps.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Erro: não conseguiu conectar no BD");
        }
    }

    public static void deletarLivro() {
        Connection connection;
        Statement stmt;
        PreparedStatement ps;
        ResultSet rs;
        Scanner teclado = new Scanner(System.in);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aulapooii",
                    "root",
                    "3236");
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM titles");
            int codigo;
            System.out.println("Digite o codigo que deseja excluir: ");
            codigo = teclado.nextInt();

            int retorno;
            ps = connection.prepareStatement("DELETE FROM titles WHERE ISBN=?");
            ps.setInt(1, codigo);
            retorno = ps.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Erro: não conseguiu conectar no BD");
        }
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcao;
        do {
            menu();
            System.out.println("Digite uma opção ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1 -> {
                    listarAutor();
                    break;
                }
                case 2 -> {
                    inserirAutor();
                    break;
                }
                case 3 -> {
                    alterarAutor();
                    break;
                }
                case 4 -> {
                    deletarAutor();
                    break;
                }
                case 5 -> {
                    listarLivros();
                    break;
                }
                case 6 -> {
                    inserirLivros();
                    break;
                }
                case 7 -> {
                    alterarLivros();
                    break;
                }
                case 8 -> {
                    deletarLivro();
                    break;
                }

            }
        } while (opcao != 0);
    }
}