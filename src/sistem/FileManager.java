package sistem;

import java.io.*;
import java.util.*;

public class FileManager {

    private static HashMap<String, Filme> filmes = new HashMap<>();

    public static void addMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do filme:");
        String nome = scanner.nextLine();
        System.out.println("Digite o ano de lançamento do filme:");
        int anoDeLancamento = scanner.nextInt();
        scanner.nextLine(); // Consumir sobras de nova linha
        System.out.println("Digite o diretor do filme:");
        String diretor = scanner.nextLine();

        Filme filme = new Filme(nome, anoDeLancamento, diretor);
        filmes.put(nome, filme);

        try {
            PrintWriter out = new PrintWriter(new FileWriter("src/data/arquivo.txt", true));
            out.println(nome + "," + anoDeLancamento + "," + diretor);
            out.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar o arquivo.");
            e.printStackTrace();
        }
    }

    public static void listMovies() {
            
        System.out.println("----------------------------------------------------------------");
        System.out.println("                ***** FILMES EM CARTAZ ******");
        System.out.println(" ");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/data/arquivo.txt"));
            String line;
            TreeMap<String, Filme> filmes = new TreeMap<>();
            List<Filme> listaDeFilmes = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                Filme filme = new Filme(attributes[0], Integer.parseInt(attributes[1]), attributes[2]);
                filmes.put(attributes[0], filme);
                listaDeFilmes.add(filme);
            }
            reader.close();

            for (int i = 0; i < listaDeFilmes.size(); i++) {
                Filme filme = listaDeFilmes.get(i);
                System.out.println((i + 1) + " - Nome: " + filme.nome +  " | Ano de Lançamento: " + filme.anoDeLancamento + " | Diretor: " + filme.diretor);
                System.out.println("");
            }

            Questions questions = new Questions();
            int opcao = questions.askForOption();
            if (opcao == 1) {
                int escolhaFilme = questions.askForMovie(listaDeFilmes);
                Filme filmeEscolhido = listaDeFilmes.get(escolhaFilme - 1);
                System.out.println("----------------------------------------------------------------");
                System.out.println("- Você escolheu o filme: " + filmeEscolhido.nome + "\n- Sessões disponíveis para este filme:\n-" );
                List<String> sessoes = Sessao.getSessoes();
                int escolhaSessao = questions.askForSession(sessoes);
                String sessaoEscolhida = sessoes.get(escolhaSessao - 1);
                System.out.println("----------------------------------------------------------------");
                System.out.println("- Você escolheu a sessão das " + sessaoEscolhida + "h.");

                List<String> poltronas = Poltrona.getPoltronas();
                String escolhaPoltrona;
                int quantidadePoltronas = 0;
                do {
                    escolhaPoltrona = questions.askForPoltrona(poltronas);
                    Poltrona.removePoltrona(escolhaPoltrona);
                    System.out.println("Você comprou a poltrona " + escolhaPoltrona + ".");
                    poltronas = Poltrona.getPoltronas();
                    quantidadePoltronas++;
                } while (questions.askForMoreSeats() && !poltronas.isEmpty());

                double total = quantidadePoltronas * 20.0;
                if (questions.askForConfirmation(total)) {
                    System.out.println("----------------------------------------------------------------");

                    System.out.println("Reserva confirmada com sucesso. Boa sessão!");
                } else {
                    System.out.println("Encerrando Sessão.");
                }
    
        } else if (opcao == 2) {
            System.out.println("Saindo...");
        } else {
            System.out.println("Opção inválida.");
        }

    } catch (IOException e) {
        System.out.println("Ocorreu um erro ao ler o arquivo.");
        e.printStackTrace();
    }

            System.out.println("----------------------------------------------------------------");

        }
    }