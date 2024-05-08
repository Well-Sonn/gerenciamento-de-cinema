package sistem;
import java.io.IOException;
import java.util.Scanner;

import user.Cliente;
import user.admin;

public class Validate {
    public static void validateUser() throws NumberFormatException, IOException {

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
                FileManager.listMovies(cliente);
                break;
            case "2":
                if (admin.ValidarAdmin(true)) { // Chama o método ValidarAdmin para verificar as credenciais de administrador
                    Questions questions = new Questions(); // Criando uma instância de Questions
                    questions.moviesOptions(); // Chamando o método moviesOptions() a partir dessa instância
                } else {
                    System.out.println("Credenciais de administrador inválidas.");
                }
                break;
            case "3":
                System.out.println("Fechar janela e encerrar");
                break;
            default:
                System.out.println("Tipo de usuário inválido");
                break;
        }

        scanner.close();
    }

    
}