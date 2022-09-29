package aed.ordenacao.algoritmos;

import aed.Comparacao;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class BubbleSort<T> extends AlgoritmoOrdenacao<T> {

    public BubbleSort(Comparacao<T> criterio) {
        super(criterio);
    }

    public void ordenar(T... elementos) {
        for (int indiceFim = elementos.length - 1; indiceFim > 0; indiceFim--) {
            for (int i = 1; i <= indiceFim; i++) {
                if (criterio.comparar(elementos[i], elementos[i - 1]) < 0) {
                    trocar(elementos, i, i - 1);
                }
            }
        }
    }

}
