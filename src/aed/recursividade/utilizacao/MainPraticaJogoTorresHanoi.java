package aed.recursividade.utilizacao;

import aed.recursividade.algoritmos.JogoTorresHanoi;

public class MainPraticaJogoTorresHanoi {
    public static void main(String[] args) {

        int[] ns = {3,0,4,5};

        for (int n : ns) {
            JogoTorresHanoi.apresentar(n);
        }
    }
}
