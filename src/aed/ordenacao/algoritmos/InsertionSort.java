package aed.ordenacao.algoritmos;

import aed.Comparacao;

import java.util.Arrays;

public class InsertionSort<T> extends AlgoritmoOrdenacao<T>{
    public InsertionSort(Comparacao<T> criterio) {
        super(criterio);
    }

    @Override
    public void ordenar(T... elementos) {
        for (int i = 1; i < elementos.length; i++) {
            T selecionado = elementos[i];
            int j = i - 1;

            while (j >= 0 && criterio.comparar(elementos[j], selecionado) > 0){
                elementos[j+1] = elementos[j];
                j--;
            }
            elementos[j + 1] = selecionado;
        }
    }
}
