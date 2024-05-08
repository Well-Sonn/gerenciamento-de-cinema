package user;

import java.io.Console;

public class admin {
    
    private static boolean validarAdmin(boolean isAdmin) {
        if (isAdmin) {
            Console console = System.console();
            if (console == null) {
                System.err.println("Console não disponível.");
                System.exit(1);
            }

            System.out.println("----------------------------------------------------------------");
            System.out.println("- Bem vindo usuário Administrador!");
            System.out.println("");
            String username = console.readLine("- Insira seu login: ");
            char[] passwordArray = console.readPassword("Insira sua senha: ");
            String password = new String(passwordArray);
            boolean isValid = username.equals("admin") && password.equals("admin123");

            return isValid;
        } else {
            return false; // Se não for admin, não precisa validar
        }
    }
    // Método público para chamar validarAdmin()
    public static boolean getValidarAdmin(boolean isAdmin) {
        return validarAdmin(isAdmin);
    }
}
