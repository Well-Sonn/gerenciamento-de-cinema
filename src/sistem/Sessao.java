package sistem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sessao {
    public static List<String> getSessoes() {
        List<String> sessoes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/data/sessoes.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                sessoes.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo de sessões.");
            e.printStackTrace();
        }
        return sessoes;
    }

}
