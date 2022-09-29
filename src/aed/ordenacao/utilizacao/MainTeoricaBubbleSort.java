package aed.ordenacao.utilizacao;

import aed.ComparacaoInteiros;
import aed.ordenacao.algoritmos.BubbleSort;

import java.util.Random;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class MainTeoricaBubbleSort {

    public static void main(String[] args) {
        BubbleSort<Integer> bubbleSort = new BubbleSort<>(ComparacaoInteiros.CRITERIO);
        bubbleSort.apresentar(3, 7, 2, 5, 4, 1, 6, 8, 9);
        Random random = new Random();
        random.nextInt(10); // 0..9
        //entre a e b
        int A = 10;
        int B = 20;
        int valorEntreAEB = A + random.nextInt(B - A + 1);
    }
}
