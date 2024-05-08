package sistem;

public class Filme {
    private String nome;
    private int anoDeLancamento;
    private String diretor;
    private int sala;

    Filme(String nome, int anoDeLancamento, String diretor, int sala) {
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
        this.diretor = diretor;
        this.sala = sala;
        }

        public static Filme getFilmePorIndice(int indice) {
            return null;
        }
        public String getNome() {
            return nome;
        }

        public int getAnoDeLancamento() {
            return anoDeLancamento;
        }
        
        public String getDiretor() {
            return diretor;
        }
        
        public int getSala() {
            return sala;
        }
        @Override
        public String toString() {
            return "Filme{" +
                    "nome='" + nome + '\'' +
                    ", anoDeLancamento=" + anoDeLancamento +
                    ", diretor='" + diretor + '\'' +
                    ", sala=" + sala +
                    '}';
        }   
}