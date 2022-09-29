package aed.ordenacao.algoritmos;

import aed.Comparacao;
import aed.utils.Vetor;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public abstract class AlgoritmoOrdenacao<T> {

    protected final Comparacao<T> criterio;

    public AlgoritmoOrdenacao(Comparacao<T> criterio) {
        this.criterio = criterio;
    }

    public abstract void ordenar(T... elementos);

    public void apresentar(T... elementos) {
        ordenar(elementos);
        System.out.print("Sequência ordenada por " + getClass().getSimpleName() + ": ");
        Vetor.apresentar(10, elementos);
    }

    protected void trocar(T[] elementos, int indice1, int indice2) {
        T aux = elementos[indice1];
        elementos[indice1] = elementos[indice2];
        elementos[indice2] = aux;
    }
}