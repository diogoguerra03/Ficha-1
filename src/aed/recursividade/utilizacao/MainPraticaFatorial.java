package aed.recursividade.utilizacao;

import aed.recursividade.algoritmos.Fatorial;

public class MainPraticaFatorial {
    public static void main(String[] args) {
        /*Fatorial.apresentar(5);
        Fatorial.apresentar(0);
        Fatorial.apresentar(-5);
        Fatorial.apresentar(21);*/

        int[] ns = {5 ,0 , -5, 21};

        for (int n : ns) {
            Fatorial.apresentar(n);
        }
    }
}
