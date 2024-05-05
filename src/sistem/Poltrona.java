package sistem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Poltrona {
    public static List<String> getPoltronas() {
        List<String> poltronas = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/data/poltronas.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] poltronasLine = line.split(" ");
                for (String poltrona : poltronasLine) {
                    poltronas.add(poltrona);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo de poltronas.");
            e.printStackTrace();
        }
        return poltronas;
    }

    public static void removePoltrona(String poltrona) {
        List<String> poltronas = getPoltronas();
        poltronas.remove(poltrona);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/poltronas.txt"));
            for (String poltronaLine : poltronas) {
                writer.write(poltronaLine + " ");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever no arquivo de poltronas.");
            e.printStackTrace();
        }
    }
}
