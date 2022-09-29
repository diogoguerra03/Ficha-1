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
public class SelectionSort<T> extends AlgoritmoOrdenacao<T> {

    public SelectionSort(Comparacao<T> criterio) {
        super(criterio);
    }

    public void ordenar(T... elementos) {
        for (int i = 0; i < elementos.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < elementos.length; j++) {
                if (criterio.comparar(elementos[j], elementos[menor]) < 0) {
                    menor = j;
                }
            }
            if (menor != i) {
                trocar(elementos, i, menor);
            }
        }
    }
}
