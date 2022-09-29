package aed.pesquisa.utilizacao;

import aed.ComparacaoInteiros;
import aed.pesquisa.algoritmos.PesquisaLinearEmSequenciaNaoOrdenada;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class MainTeoricaPesquisaLinearEmSequenciaNaoOrdenada {

    public static void main(String[] args) {
        PesquisaLinearEmSequenciaNaoOrdenada<Integer> pesquisaLinear = new PesquisaLinearEmSequenciaNaoOrdenada<>(ComparacaoInteiros.CRITERIO);
        pesquisaLinear.apresentar(1, 7, 2, 5, 4, 1, 6, 8, 9);
        pesquisaLinear.apresentar(3, 7, 2, 5, 4, 1, 6, 8, 9);
        pesquisaLinear.apresentar(3, 1, 2, 4, 5, 6, 7, 8, 9);
    }
}
