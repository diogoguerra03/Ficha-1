package aed.colecoes.iteraveis.lineares.ordenadas.estruturas;

import aed.Comparacao;
import aed.colecoes.iteraveis.ColecaoIteravel;
import aed.colecoes.iteraveis.IteradorIteravel;
import aed.colecoes.iteraveis.lineares.ordenadas.ColecaoIteravelLinearOrdenada;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class ListaSimplesCircularBaseOrdemMaximaOrdenada<T> implements ColecaoIteravelLinearOrdenada<T> {
    private static final long serialVersionUID = 1L;

    protected Base base;
    protected Comparacao<T> criterio;
    protected int numeroElementos;

    public ListaSimplesCircularBaseOrdemMaximaOrdenada(Comparacao<T> cp) {
        base = new Base();
        criterio = cp;
        numeroElementos = 0;
    }

    public ListaSimplesCircularBaseOrdemMaximaOrdenada(Comparacao<T> cp, ColecaoIteravelLinearOrdenada<T> colecao) {
        this(cp);

        if (cp.equals(colecao.getComparador())) {
            No noFinal = base;
            for (T elem : colecao) {
                noFinal = new NoComElemento(elem, noFinal);
            }

            numeroElementos = colecao.getNumeroElementos();
        } else {
            inserirTodos(colecao);
        }
    }

    public ListaSimplesCircularBaseOrdemMaximaOrdenada(Comparacao<T> cp, ColecaoIteravel<T> colecao) {
        this(cp);

        inserirTodos(colecao);
    }

    protected No getNoAnteriorPorOrdem(T elem) {
        No ant = base;

        while (ant.seguinte.compararElemento(elem) < 0) {
            ant = ant.seguinte;
        }

        return ant;
    }

    protected No getNoAnterior(T elem) {
        No ant = getNoAnteriorPorOrdem(elem);

        while (ant.seguinte.compararElemento(elem) == 0 &&
                !ant.seguinte.isElementoIgual(elem)) {
            ant = ant.seguinte;
        }

        return ant;
    }

    protected No getNoAnteriorPorReferencia(T elem) {
        No ant = getNoAnteriorPorOrdem(elem);

        while (ant.seguinte.compararElemento(elem) == 0 &&
                !ant.seguinte.isElementoIgualPorReferencia(elem)) {
            ant = ant.seguinte;
        }

        return ant;
    }

    protected No getNoAnterior(int indice) {
        if (indice < 0 || indice >= numeroElementos) {
            throw new IndexOutOfBoundsException();
        }

        No ant = base;
        while (indice-- > 0) {
            ant = ant.seguinte;
        }

        return ant;
    }

    @Override
    public void inserir(T elem) {
        new NoComElemento(elem, getNoAnteriorPorOrdem(elem));
        numeroElementos++;
    }

    private void inserirTodos(ColecaoIteravel<T> colecao) {
        for (T elemento : colecao) {
            inserir(elemento);
        }
    }

    private T removerNoSeguinte(No ant) {
        No aux = ant.seguinte;
        ant.seguinte = aux.seguinte;
        numeroElementos--;

        return aux.elemento;
    }

    @Override
    public T remover(T elem) {
        No ant = getNoAnterior(elem);

        return ant.seguinte.isElementoIgual(elem) ? removerNoSeguinte(ant) : null;
    }

    @Override
    public T removerPorReferencia(T elem) {
        No ant = getNoAnteriorPorReferencia(elem);

        return ant.seguinte.isElementoIgualPorReferencia(elem) ? removerNoSeguinte(ant) : null;
    }

    @Override
    public T remover(int indice) {
        return removerNoSeguinte(getNoAnterior(indice));
    }

    @Override
    public T consultar(int indice) {
        return getNoAnterior(indice).seguinte.elemento;
    }

    @Override
    public IteradorIteravel<T> consultar(T elemInicial, T elemFinal) {
        return new Iterador(elemInicial, elemFinal);
    }

    @Override
    public boolean contemOrdem(T elem) {
        return getNoAnteriorPorOrdem(elem).seguinte.compararElemento(elem) == 0;
    }

    @Override
    public boolean contem(T elem) {
        return getNoAnterior(elem).seguinte.isElementoIgual(elem);
    }

    @Override
    public boolean contemReferencia(T elem) {
        return getNoAnteriorPorReferencia(elem).seguinte.isElementoIgualPorReferencia(elem);
    }

    @Override
    public int getNumeroElementos() {
        return numeroElementos;
    }

    @Override
    public IteradorIteravel<T> iterador() {
        return new Iterador();
    }

    @Override
    public Comparacao<T> getComparador() {
        return criterio;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Lista Simples Circular Base Ordem Máxima Ordenada por ");
        s.append(criterio.getClass().getSimpleName()).append(" = {\n");
        No aux = base.seguinte;
        while (aux != base) {
            s.append(aux.elemento).append("\n");
            aux = aux.seguinte;
        }
        s.append("}\n");

        return s.toString();
    }


    protected abstract class No implements Serializable {
        private static final long serialVersionUID = 1L;

        protected T elemento;
        protected No seguinte;

        protected abstract boolean isElementoIgual(T elem);

        protected abstract boolean isElementoIgualPorReferencia(T elem);

        protected abstract int compararElemento(T elem);
    }

    protected class Base extends No {

        protected Base() {
            elemento = null;
            seguinte = this;
        }

        protected boolean isElementoIgual(T elem) {
            return false;
        }

        protected boolean isElementoIgualPorReferencia(T elem) {
            return false;
        }

        protected int compararElemento(T elem) {
            return 1;
        }
    }

    protected class NoComElemento extends No {

        protected NoComElemento(T elem, No ant) {
            if (elem == null) {
                throw new IllegalArgumentException();
            }
            elemento = elem;
            seguinte = ant.seguinte;
            ant.seguinte = this;
        }

        protected boolean isElementoIgual(T elem) {
            return elemento.equals(elem);
        }

        protected boolean isElementoIgualPorReferencia(T elem) {
            return elemento == elem;
        }

        protected int compararElemento(T elem) {
            return criterio.comparar(elemento, elem);
        }
    }


    protected class Iterador implements IteradorIteravel<T> {
        protected No anteriorAoPrimeiro;
        protected No corrente;
        protected No seguinteAoUltimo;

        protected Iterador() {
            anteriorAoPrimeiro = seguinteAoUltimo = base;
            reiniciar();
        }

        protected Iterador(T elemInicial, T elemFinal) {
            if (criterio.comparar(elemInicial, elemFinal) > 0) {
                throw new NoSuchElementException();
            }

            anteriorAoPrimeiro = getNoAnteriorPorOrdem(elemInicial);
            seguinteAoUltimo = anteriorAoPrimeiro.seguinte;

            while (seguinteAoUltimo.compararElemento(elemFinal) <= 0) {
                seguinteAoUltimo = seguinteAoUltimo.seguinte;
            }

            reiniciar();
        }

        @Override
        public void reiniciar() {
            corrente = anteriorAoPrimeiro;
        }

        @Override
        public T corrente() {
            if (corrente == anteriorAoPrimeiro) {
                throw new NoSuchElementException();
            }
            return corrente.elemento;
        }

        @Override
        public boolean podeAvancar() {
            return corrente.seguinte != seguinteAoUltimo;
        }

        @Override
        public T avancar() {
            if (!podeAvancar()) {
                throw new NoSuchElementException();
            }

            corrente = corrente.seguinte;
            return corrente.elemento;
        }
    }
}
