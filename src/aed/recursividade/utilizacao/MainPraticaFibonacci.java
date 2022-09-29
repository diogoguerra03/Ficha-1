package aed.recursividade.utilizacao;

import aed.recursividade.algoritmos.Fibonacci;

public class MainPraticaFibonacci {
    public static void main(String[] args) {
        // c)
        int[] ns = {5, 0, -10, 40, 45};
        for (int n : ns) {
            Fibonacci.apresentar(n);
        }

        // d)
        int i = 5;
        while (i <= 20){
            Fibonacci.apresentar(i);
            i += 3;
        }
    }
}
