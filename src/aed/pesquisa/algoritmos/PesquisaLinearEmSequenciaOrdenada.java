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
public class PesquisaLinearEmSequenciaOrdenada<T> extends AlgoritmoPesquisa<T> {

    public PesquisaLinearEmSequenciaOrdenada(Comparacao<T> criterio) {
        super(criterio);
    }

    public int pesquisar(T elemento, T... elementos) {
        if (elementos.length == 0 ||
                criterio.comparar(elemento, elementos[elementos.length - 1]) > 0) {
            return NAO_ENCONTRADO;
        }
        for (int i = 0; i < elementos.length; i++) {
            int cp = criterio.comparar(elemento, elementos[i]);
            if (cp < 0) {
                return NAO_ENCONTRADO;
            }
            if (cp == 0) {
                return i;
            }
        }
        return NAO_ENCONTRADO;
    }
}
