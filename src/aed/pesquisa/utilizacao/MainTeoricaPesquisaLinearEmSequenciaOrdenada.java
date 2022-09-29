package aed.pesquisa.utilizacao;

import aed.ComparacaoInteiros;
import aed.pesquisa.algoritmos.PesquisaLinearEmSequenciaOrdenada;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class MainTeoricaPesquisaLinearEmSequenciaOrdenada {

    public static void main(String[] args) {
        PesquisaLinearEmSequenciaOrdenada<Integer> pesquisaLinear =
                new PesquisaLinearEmSequenciaOrdenada<>(ComparacaoInteiros.CRITERIO);
        pesquisaLinear.apresentar(7, 1, 2, 4, 5, 6, 7, 8, 9);
        pesquisaLinear.apresentar(12, 1, 2, 4, 5, 6, 7, 8, 9);
    }
}
