package aed.ordenacao.utilizacao;

import aed.ComparacaoInteiros;
import aed.ordenacao.algoritmos.QuickSort;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class MainTeoricaQuickSort {

    public static void main(String[] args) {
        QuickSort<Integer> quickSort = new QuickSort<>(ComparacaoInteiros.CRITERIO);
        quickSort.apresentar(3, 7, 2, 5, 4, 1, 6, 8, 9);
    }
}
