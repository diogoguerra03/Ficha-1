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
public class QuickSort<T> extends AlgoritmoOrdenacao<T> {

    public QuickSort(Comparacao<T> criterio) {
        super(criterio);
    }

    public void ordenar(T... elementos) {
        if (elementos.length == 0) {
            return;
        }
        ordenarRecursivo(0, elementos.length - 1, elementos);
    }

    private void ordenarRecursivo(int esq, int dir, T... elementos) {
        int i = esq;
        int j = dir;
        int meio = (i + j) / 2;
        T pivot = elementos[meio];

        do {
            while (criterio.comparar(elementos[i], pivot) < 0) {
                i++;
            }
            while (criterio.comparar(elementos[j], pivot) > 0) {
                j--;
            }
            if (i <= j) {
                trocar(elementos, i, j);
                i++;
                j--;
            }
        } while (i <= j);
        if (esq < j) {
            ordenarRecursivo(esq, j, elementos);
        }
        if (i < dir) {
            ordenarRecursivo(i, dir, elementos);
        }
    }
}
