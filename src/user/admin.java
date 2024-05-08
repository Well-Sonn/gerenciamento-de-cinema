package user;

import java.util.Scanner;

public class admin {
    
     public static boolean ValidarAdmin(boolean isAdmin) {
        if (isAdmin) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("----------------------------------------------------------------");
            System.out.println("- Bem vindo usuário Administrador!");
            System.out.println("");
            System.out.println("- Insira seu login: ");
            String username = scanner.nextLine();
            System.out.println("----------------------------------------------------------------");

            System.out.println("Insira sua senha: ");
            String password = scanner.nextLine();
            boolean isValid = username.equals("admin") && password.equals("admin123");

            // scanner.close();

            return isValid;
        } else {
            return false; // Se não for admin, não precisa validar
        }
        
    }
}
