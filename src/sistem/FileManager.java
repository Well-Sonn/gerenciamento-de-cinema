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
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                Filme filme = new Filme(attributes[0], Integer.parseInt(attributes[1]), attributes[2]);
                filmes.put(attributes[0], filme);
            }
            reader.close();

            for (String nome : filmes.keySet()) {
                Filme filme = filmes.get(nome);
                System.out.println("- Nome: " + filme.nome +  " | Ano de Lançamento: " + filme.anoDeLancamento + " | Diretor: " + filme.diretor);
                System.out.println("");
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo.");
            e.printStackTrace();
        }

        System.out.println("----------------------------------------------------------------");

    }
}