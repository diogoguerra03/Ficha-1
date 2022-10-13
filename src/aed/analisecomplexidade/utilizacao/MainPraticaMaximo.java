package aed.analisecomplexidade.utilizacao;

import aed.analisecomplexidade.algoritmos.Maximo;
import aed.utils.VetorDeInteiros;

public class MainPraticaMaximo {
    public static void main(String[] args) {

        int[] ns = {5, 10};
        for (int n : ns){
            Maximo.apresentar(VetorDeInteiros.criarAleatorioInt(n, -50, 50, true));
        }

    }
}
