package sistem;
import java.io.IOException;
import java.util.Scanner;

public class Validate {
    public static void validateUser() throws NumberFormatException, IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------------------------------------------");
        System.out.println("-  Bem vindo ao CineTech! Por favor, informe o tipo de acesso: -\n-  (1) - Usuário                                               -"+
            "\n-  (2) - Administrador                                         -"+
            "\n-  (3) - Fechar janela e encerrar                              -");
        System.out.println("----------------------------------------------------------------");

        String userType = scanner.nextLine();

        switch (userType.toLowerCase()) {
            case "1":
                FileManager.listMovies();
                break;
            case "2":
                FileManager.addMovie();
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