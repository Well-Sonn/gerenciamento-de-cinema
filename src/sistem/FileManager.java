package sistem;

import java.io.*;
import java.util.*;

import user.Cliente;

public class FileManager {

    private static HashMap<String, Filme> filmes = new HashMap<>();

    private static void addMovie() {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Digite o nome do filme:");
        String nome = scanner2.nextLine();
        System.out.println("Digite o ano de lançamento do filme:");
        int anoDeLancamento = scanner2.nextInt();
        scanner2.nextLine();
        System.out.println("Digite o diretor do filme:");
        String diretor = scanner2.nextLine();    
        System.out.println("Em qual sala deve passar esse filme?");
        int sala = scanner2.nextInt();

        Filme filme = new Filme(nome, anoDeLancamento, diretor, sala);
        filmes.put(nome, filme);

        try {
            PrintWriter out = new PrintWriter(new FileWriter("src/data/arquivo.txt", true));
            out.println(nome + "," + anoDeLancamento + "," + diretor + "," + sala);
            out.close();
            System.out.println("----------------------------------------------------------------");
            System.out.println("- Filme cadastrado com sucesso!");
            System.out.println("----------------------------------------------------------------"); 
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar o arquivo.");
            e.printStackTrace();
        }
    }

    private static void listarFilmes(Cliente cliente) {
            
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
                if (attributes.length >= 4) { // Verifica se há pelo menos quatro elementos
                    Filme filme = new Filme(attributes[0], Integer.parseInt(attributes[1]), attributes[2], Integer.parseInt(attributes[3]));
                    filmes.put(attributes[0], filme);
                    listaDeFilmes.add(filme);
                } else {
                    System.out.println("Formato de dados inválido na linha: " + line);
                }
            }
            reader.close();

            for (int i = 0; i < listaDeFilmes.size(); i++) {
                Filme filme = listaDeFilmes.get(i);
                System.out.println((i + 1) + " - Nome: " + filme.getNome() +  " | Ano de Lançamento: " + filme.getAnoDeLancamento() + " | Diretor: " + filme.getDiretor());
                System.out.println("");
            }

            Questions questions = new Questions();
            int opcao = questions.askForOption();
            if (opcao == 1) {
                int escolhaFilme = questions.askForMovie(listaDeFilmes);
                Filme filmeEscolhido = listaDeFilmes.get(escolhaFilme - 1);
                System.out.println("----------------------------------------------------------------");
                System.out.println("- Você escolheu o filme: " + filmeEscolhido.getNome() + "\n- Sessões disponíveis para este filme:\n-" );
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
                    Poltrona.getRemoverPoltrona(escolhaPoltrona);
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("- Você reservou a poltrona " + escolhaPoltrona + ".");
                    poltronas = Poltrona.getPoltronas();
                    quantidadePoltronas++;
                } while (questions.askForMoreSeats() && !poltronas.isEmpty());

                double total = Ingresso.getCalcularPrecoTotal(quantidadePoltronas);
                if (questions.askForConfirmation(total)) {
                    if (questions.askForEmailConfirmation()) {
                        String email = questions.askForEmail();
                        cliente.setEmail(email);
                        System.out.println("- Comprovante enviado por email! Reserva confirmada com sucesso.");
                    } else {
                        System.out.println("- Reserva confirmada com sucesso.");
                    }
                } else {
                    System.out.println("- Encerrando Sessão.");
                }
    
        } else if (opcao == 2) {
            System.out.println("- Saindo...");
        } else {
            System.out.println("- Opção inválida.");
        }

    } catch (IOException e) {
        System.out.println("- Ocorreu um erro ao ler o arquivo.");
        e.printStackTrace();
    }

            System.out.println("----------------------------------------------------------------");

        }

        private static void deleteMovie(String nome) {
            try {
                // Lê os filmes do arquivo
                List<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader("src/data/arquivo.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();
        
                // Exibe os filmes para o usuário escolher qual excluir
                System.out.println("----------------------------------------------------------------");
                System.out.println("Filmes disponíveis para exclusão:");
                for (int i = 0; i < lines.size(); i++) {
                    String[] parts = lines.get(i).split(","); // Divide a linha em partes usando a vírgula como separador
                    String nome1 = parts[0]; // O nome do filme é a primeira parte
                    String diretor = parts[2]; // O diretor está na terceira parte
                    System.out.println((i + 1) + ". Filme: " + nome + " |   Diretor: " + diretor);
                }
                System.out.println("----------------------------------------------------------------");
                
                // Pedir ao usuário para inserir o número do filme a ser excluído
                Scanner scanner = new Scanner(System.in);
                int movieIndex;
                while (true) {
                    System.out.println("- Digite o número do filme que deseja excluir:");
                    if (scanner.hasNextInt()) {
                        movieIndex = scanner.nextInt();
                        if (movieIndex >= 1 && movieIndex <= lines.size()) {
                            break; // Sai do loop se o número estiver dentro do intervalo válido
                        } else {
                            System.out.println("----------------------------------------------------------------");
                            System.out.println("- Opção inválida.");
                            System.out.println("----------------------------------------------------------------");

                        }
                    } else {
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("- Digite um número válido.");
                        System.out.println("----------------------------------------------------------------");
                        scanner.next(); // Limpa o buffer de entrada
                    }
                }
                scanner.nextLine(); // Limpa o buffer
        
                // Pede confirmação para excluir
                System.out.println("----------------------------------------------------------------");
                System.out.println("- Deseja realmente excluir o filme selecionado? (\n- 1 Sim \n- 2 Não");
                int confirm = scanner.nextInt();
                if (confirm == 1) {
                    // Remove o filme selecionado da lista
                    lines.remove(movieIndex - 1);
        
                    // Atualiza o arquivo com os filmes restantes
                    PrintWriter writer = new PrintWriter(new FileWriter("src/data/arquivo.txt"));
                    for (String updatedLine : lines) {
                        writer.println(updatedLine);
                    }
                    writer.close();
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("- Filme excluído com sucesso.");
                    System.out.println("----------------------------------------------------------------");
                } else {
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("- Operação cancelada.");
                    System.out.println("----------------------------------------------------------------");
                }
            } catch  (IOException e) {
                System.out.println("- Erro ao ler o arquivo.");
                e.printStackTrace();
            }
        }
        // Metodo publico para listar os filmes
    public static void getListarFilmes(Cliente cliente) {
        listarFilmes(cliente);
    }
        // Método publico para adicionar um filme
    public static void getAddMovie() {
        addMovie();
    }
    // Método publico para excluir um filme
    public static void getDeleteMovie(String nome) {
        deleteMovie(nome);
    }

}

    