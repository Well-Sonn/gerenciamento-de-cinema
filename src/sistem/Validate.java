package sistem;
import java.io.IOException;
import java.util.Scanner;

import user.Cliente;
import user.admin;

public class Validate {
    private static void validateUser() throws NumberFormatException, IOException {

        Cliente cliente = new Cliente();

        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------------------------------------------");
        System.out.println("-  Bem vindo ao CineTech! Por favor, informe o tipo de acesso: -\n-  (1) - Usuário                                               -"+
            "\n-  (2) - Administrador                                         -"+
            "\n-  (3) - Fechar janela e encerrar                              -");
        System.out.println("----------------------------------------------------------------");

        String userType = scanner.nextLine();

        switch (userType.toLowerCase()) {
            case "1":
                FileManager.getListarFilmes(cliente);
                break;
            case "2":
                if (admin.getValidarAdmin(true)) { // Chama o metodo publuco getValidarAdmin para verificar as credenciais de administrador
                    Questions questions = new Questions(); // Criando uma instância de Questions
                    questions.moviesOptions(); // Chamando o metodo moviesOptions() a partir dessa instância
                } else {
                    System.out.println("Credenciais de administrador inválidas.");
                }
                break;
            case "3":
                System.out.println("Sessão Encerrada!");
                break;
            default:
                System.out.println("Tipo de usuário inválido");
                break;
        }

        scanner.close();
    }
    // Metodo publico para validar usuario
    public static void getValidateUser() {
        try {
            validateUser();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}