package aed.recursividade.utilizacao;

import aed.recursividade.algoritmos.JogoTorresHanoiV2;

public class MainPraticaJogoTorresHanoiV2 {
    public static void main(String[] args) {

        int[] ns = {3,0,4,5};

        for (int n : ns) {
            JogoTorresHanoiV2.apresentar(n);
        }
    }
}
