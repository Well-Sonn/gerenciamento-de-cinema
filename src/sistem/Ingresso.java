package sistem;

public class Ingresso {
    private static final double PRECO_INGRESSO = 20.0;
    private static final double IMPOSTO = 0.10;

    private static double calcularPrecoTotal(int quantidade) {
        double precoTotal = quantidade * PRECO_INGRESSO;
        double impostoTotal = precoTotal * IMPOSTO;
        return precoTotal + impostoTotal;
    }

    // Metodo publico para calcular o preco total
    public static double getCalcularPrecoTotal(int quantidade) {
        return calcularPrecoTotal(quantidade);
    }
}    