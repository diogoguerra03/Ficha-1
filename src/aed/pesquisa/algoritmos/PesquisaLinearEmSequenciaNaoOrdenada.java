package aed.pesquisa.algoritmos;

import aed.Comparacao;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class PesquisaLinearEmSequenciaNaoOrdenada<T> extends AlgoritmoPesquisa<T> {

    public PesquisaLinearEmSequenciaNaoOrdenada(Comparacao<T> criterio) {
        super(criterio);
    }

    public int pesquisar(T elemento, T... elementos) {
        for (int i = 0; i < elementos.length; i++) {
            if (criterio.comparar(elemento, elementos[i]) == 0) {
                return i;
            }
        }
        return NAO_ENCONTRADO;
    }
}
