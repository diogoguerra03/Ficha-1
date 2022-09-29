package aed.analisecomplexidade.utilizacao;

import aed.analisecomplexidade.algoritmos.SubsequenciaSomaMaxima;
import aed.analisecomplexidade.algoritmos.SubsequenciaSomaMaximaDeOrdemNLog2N;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class MainTeoricaSubsequenciaSomaMaximaDeOrdemNLog2N {

    public static void main(String[] args) {
        SubsequenciaSomaMaxima subsequenciaSomaMaxima = new SubsequenciaSomaMaximaDeOrdemNLog2N();
        subsequenciaSomaMaxima.apresentar(-2, 11, -4, 5, 7, -3, 13, 7, -5, 3);
        subsequenciaSomaMaxima.apresentar(4, -10, 3, 5, -9, 6);
    }
}
