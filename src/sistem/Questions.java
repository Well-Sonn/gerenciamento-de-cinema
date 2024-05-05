package sistem;

import java.util.List;
import java.util.Scanner;

public class Questions {
    private Scanner scanner;

    public Questions() {
        this.scanner = new Scanner(System.in);
    }

    public int askForOption() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Deseja agendar uma sessão? \n- 1 - Sim \n- 2 - Não");
        System.out.println("----------------------------------------------------------------");
        return scanner.nextInt();
    }

    public int askForMovie(List<Filme> listaDeFilmes) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Escolha um filme:");
        for (int i = 0; i < listaDeFilmes.size(); i++) {
            Filme filme = listaDeFilmes.get(i);
            System.out.println((i + 1) + " - " + filme.nome);
        }
        System.out.println("----------------------------------------------------------------");
        return scanner.nextInt();
    }
    public int askForSession(List<String> sessoes) {
        System.out.println("- Escolha uma sessão:");
        for (int i = 0; i < sessoes.size(); i++) {
            String sessao = sessoes.get(i);
            System.out.println("- "+ (i + 1) + " - " + sessao + "h");
        }
        System.out.println("----------------------------------------------------------------");
        return scanner.nextInt();
    }

    public String askForPoltrona(List<String> poltronas) {
        System.out.println("Escolha uma poltrona:");
        for (int i = 0; i < poltronas.size(); i++) {
            System.out.print((i + 1) + " - " + poltronas.get(i));
            if ((i + 1) % 3 == 0) {
                System.out.println();
            } else {
                System.out.print("\t");
            }
        }
        System.out.println();
        int escolhaPoltrona = scanner.nextInt();
        return poltronas.get(escolhaPoltrona - 1);
    }

    public boolean askForMoreSeats() {
        System.out.println("- Deseja adicionar mais algum lugar? \n- 1 - Sim, \n- 2 - Não");
        System.out.println("----------------------------------------------------------------");
        int response = scanner.nextInt();
        return response == 1;
    }

    public boolean askForConfirmation(double total) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("O valor total da compra é R$" + total + ". Deseja confirmar a compra? \n- 1 - Sim, \n- 2 - Não");
        int response = scanner.nextInt();
        return response == 1;
    }
    
}