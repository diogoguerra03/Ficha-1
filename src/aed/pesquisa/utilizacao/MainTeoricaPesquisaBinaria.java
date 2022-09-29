package aed.pesquisa.utilizacao;

import aed.ComparacaoInteiros;
import aed.pesquisa.algoritmos.PesquisaBinaria;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class MainTeoricaPesquisaBinaria {

    public static void main(String[] args) {
        PesquisaBinaria<Integer> pesquisaBinaria = new PesquisaBinaria<>(ComparacaoInteiros.CRITERIO);
        pesquisaBinaria.apresentar(7, 7);
        pesquisaBinaria.apresentar(7, 1, 2, 4, 5, 6, 7, 8, 9);
        pesquisaBinaria.apresentar(3, 1, 2, 4, 5, 6, 7, 8, 9);
    }
}
