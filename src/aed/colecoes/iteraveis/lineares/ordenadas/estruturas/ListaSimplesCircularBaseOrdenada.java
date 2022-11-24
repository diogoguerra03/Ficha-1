package aed.colecoes.iteraveis.lineares.ordenadas.estruturas;

import aed.Comparacao;
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
public class ListaSimplesCircularBaseOrdenada<T> implements ColecaoIteravelLinearOrdenada<T> {
    private static final long serialVersionUID = 1L;

    protected No base;
    protected Comparacao<T> criterio;
    protected int numeroElementos;

    public ListaSimplesCircularBaseOrdenada(Comparacao<T> cp) {
        base = new No();
        criterio = cp;
        numeroElementos = 0;
    }

    public ListaSimplesCircularBaseOrdenada(ColecaoIteravelLinearOrdenada<T> colecao) {
        this(colecao.getComparador());

        No noFinal = base;
        for (T elem : colecao) {
            noFinal = new No(elem, noFinal);
        }

        numeroElementos = colecao.getNumeroElementos();
    }

    protected No getNoAnteriorPorOrdem(T elem) {
        No ant = base;

        while (ant.seguinte != base && criterio.comparar(ant.seguinte.elemento, elem) < 0) {
            ant = ant.seguinte;
        }

        return ant;
    }

    protected No getNoAnterior(T elem) {
        No ant = getNoAnteriorPorOrdem(elem);

        while (ant.seguinte != base &&
                criterio.comparar(ant.seguinte.elemento, elem) == 0 &&
                !ant.seguinte.elemento.equals(elem)) {
            ant = ant.seguinte;
        }

        return ant;
    }


    protected No getNoAnteriorPorReferencia(T elem) {
        No ant = getNoAnteriorPorOrdem(elem);

        while (ant.seguinte != base &&
                criterio.comparar(ant.seguinte.elemento, elem) == 0 &&
                ant.seguinte.elemento != elem) {
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
        new No(elem, getNoAnteriorPorOrdem(elem));
        numeroElementos++;
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

        return ant.seguinte != base && ant.seguinte.elemento.equals(elem) ? removerNoSeguinte(ant) : null;
    }

    @Override
    public T removerPorReferencia(T elem) {
        No ant = getNoAnteriorPorReferencia(elem);

        return ant.seguinte != base && ant.seguinte.elemento == elem ? removerNoSeguinte(ant) : null;
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
        No no = getNoAnteriorPorOrdem(elem).seguinte;

        return no != base && criterio.comparar(no.elemento, elem) == 0;
    }

    @Override
    public boolean contem(T elem) {
        No no = getNoAnterior(elem).seguinte;

        return no != base && no.elemento.equals(elem);
    }

    @Override
    public boolean contemReferencia(T elem) {
        No no = getNoAnteriorPorReferencia(elem).seguinte;

        return no != base && no.elemento == elem;
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
        s.append("Lista Simples Circular Base Ordenada por ");
        s.append(criterio.getClass().getSimpleName()).append(" = {\n");
        No aux = base.seguinte;
        while (aux != base) {
            s.append(aux.elemento).append("\n");
            aux = aux.seguinte;
        }
        s.append("}\n");
        return s.toString();
    }

    protected class No implements Serializable {
        private static final long serialVersionUID = 1L;

        protected T elemento;
        protected No seguinte;

        // Criação do nó base
        protected No() {
            elemento = null;
            seguinte = this;
        }

        // Criação de nó com elemento elem e inserção após o nó ant
        protected No(T elem, No ant) {
            if (elem == null) {
                throw new IllegalArgumentException();
            }
            elemento = elem;
            seguinte = ant.seguinte;
            ant.seguinte = this;
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

            while (seguinteAoUltimo != base &&
                    criterio.comparar(seguinteAoUltimo.elemento, elemFinal) <= 0) {
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
