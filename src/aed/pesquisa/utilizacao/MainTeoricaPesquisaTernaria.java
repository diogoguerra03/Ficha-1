package aed.pesquisa.utilizacao;

import aed.ComparacaoInteiros;
import aed.ordenacao.algoritmos.QuickSort;
import aed.pesquisa.algoritmos.PesquisaTernariaInterativa;
import aed.pesquisa.algoritmos.PesquisaTernariaRecursiva;
import aed.utils.VetorDeInteiros;

import java.util.Random;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class MainTeoricaPesquisaTernaria {

    public static void main(String[] args) {
        Random random = new Random();
        Integer[] elementos = VetorDeInteiros.criarAleatorioInteger(10, -10, 10, true);
        new QuickSort<>(ComparacaoInteiros.CRITERIO).apresentar(elementos);
        int elemento = random.nextInt(21) - 10;
        new PesquisaTernariaRecursiva<>(ComparacaoInteiros.CRITERIO).apresentar(elemento, elementos);
        new PesquisaTernariaInterativa<>(ComparacaoInteiros.CRITERIO).apresentar(elemento, elementos);
    }
}
