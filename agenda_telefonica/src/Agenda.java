import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Agenda {

    public static void main(String[] args) {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/agenda_telefonica";
            String user = "root";
            String pwd = "";

            Scanner teclado = new Scanner(System.in);

            System.out.println("Aluno: Egberto Dias dos Reis Júnior");
            System.out.println("Matrícula: 202051249196");
            System.out.println("************************************");
            System.out.println("************************************");
            System.out.println("\n");

            System.out.println("Olá, Bem vindo a nossa Agenda telefonica!!");
            while (true) {
                System.out.println("Escolha uma das opções :");
                System.out.println("1 - Listar números");
                System.out.println("2 - Adicionar novo número");
                System.out.println("3  - Alterar número existente");
                System.out.println("4  - Deletar número");
                System.out.println("5  - Sair.");

                int op = teclado.nextInt();
                teclado.nextLine();

                if (op == 1) {
                    Connection con = DriverManager.getConnection(url, user, pwd);
                    String sql = "SELECT * FROM `agenda`";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();
                    System.out.println("Lista de Contatos :");

                    while (rs.next()) {
                        System.out.print(rs.getRow() + "    - ");
                        System.out.print(rs.getString("Nome") + " - ");
                        System.out.println(rs.getString("Telefone"));
                    }
                    stmt.close();
                    con.close();
                }


                if (op == 2) {
                    Connection con = DriverManager.getConnection(url, user, pwd);
                    String sql = "INSERT INTO `agenda`(`Nome`, `Telefone`) VALUES (?, ?)";
                    PreparedStatement stmt = con.prepareStatement(sql);

                    System.out.println("Qual o nome da pessoa que deseja adicionar?");
                    String nome = teclado.nextLine();
                    System.out.println("Qual o telefone da pessoa que deseja adicionar?");
                    String telefone = teclado.nextLine();

                    stmt.setString(1, nome);
                    stmt.setString(2, telefone);
                    stmt.execute();
                    stmt.close();
                    con.close();
                }

                if (op == 3) {
                    Connection con = DriverManager.getConnection(url, user, pwd);
                    String sql = "UPDATE `agenda` SET `Nome`= ?,`Telefone`= ? WHERE Nome = ?";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    System.out.println("Digite o nome que deseja alterar");
                    String nomeAntigo = teclado.nextLine();
                    System.out.println("Digite o nome novo");
                    String nome = teclado.nextLine();
                    System.out.println("Digite o telefone novo");
                    String telefone = teclado.nextLine();
                    stmt.setString(1, nome);
                    stmt.setString(2, telefone);
                    stmt.setString(3, nomeAntigo);
                    stmt.execute();
                    stmt.close();
                    con.close();
                }

                if (op == 4) {
                    Connection con = DriverManager.getConnection(url, user, pwd);
                    String sql = "DELETE FROM `agenda` WHERE Nome = ?";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    System.out.println("Digite o nome que deseja deletar");
                    String nome = teclado.nextLine();
                    stmt.setString(1, nome);
                    stmt.execute();
                    stmt.close();
                    con.close();
                }

                if (op == 5) {
                    System.out.println("Saindo...");
                    break;
                } else {
                    System.out.println("Por favor, digite uma opção válida!");
                }

            }

        } catch (SQLException e) {
            System.out.println("Exceção de SQL - abrir conexão!");
            e.printStackTrace();
        }catch (InputMismatchException e) {
            System.out.println("FAVOR DIGITAR APENAS NÚMEROS");
            e.printStackTrace();
        }
    }
    }