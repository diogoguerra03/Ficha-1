package aed.colecoes.iteraveis.lineares.ordenadas.estruturas;

import aed.Comparacao;
import aed.colecoes.iteraveis.ColecaoIteravel;
import aed.colecoes.iteraveis.IteradorIteravelDuplo;
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
public class ListaDuplaCircularBaseOrdemMaximaOrdenada<T> implements ColecaoIteravelLinearOrdenada<T> {
    private static final long serialVersionUID = 1L;

    protected Base base;
    protected Comparacao<T> criterio;
    protected int numeroElementos;

    public ListaDuplaCircularBaseOrdemMaximaOrdenada(Comparacao<T> cp) {
        base = new Base();
        criterio = cp;
        numeroElementos = 0;
    }

    public ListaDuplaCircularBaseOrdemMaximaOrdenada(Comparacao<T> cp, ColecaoIteravelLinearOrdenada<T> colecao) {
        this(cp);

        if (cp.equals(colecao.getComparador())) {
            for (T elem : colecao) {
                new NoComElemento(elem, base);
            }

            numeroElementos = colecao.getNumeroElementos();
        } else {
            inserirTodos(colecao);
        }
    }

    public ListaDuplaCircularBaseOrdemMaximaOrdenada(Comparacao<T> cp, ColecaoIteravel<T> colecao) {
        this(cp);

        inserirTodos(colecao);
    }

    protected No getNoPorOrdem(T elem) {
        if (numeroElementos == 0 ||
                base.anterior.compararElemento(elem) < 0) {
            return base;
        }

        No cor = base.seguinte;

        while (cor.compararElemento(elem) < 0) {
            cor = cor.seguinte;
        }

        return cor;
    }

    protected No getNo(T elem) {
        No cor = getNoPorOrdem(elem);

        while (cor.compararElemento(elem) == 0 &&
                !cor.isElementoIgual(elem)) {
            cor = cor.seguinte;
        }
        return cor;
    }

    protected No getNoPorReferencia(T elem) {
        No cor = getNoPorOrdem(elem);

        while (cor.compararElemento(elem) == 0 &&
                !cor.isElementoIgualPorReferencia(elem)) {
            cor = cor.seguinte;
        }
        return cor;

    }


    protected No getNo(int indice) {
        if (indice < 0 || indice >= numeroElementos) {
            throw new IndexOutOfBoundsException();
        }

        No cor;
        if (indice < numeroElementos / 2) {
            cor = base.seguinte;

            while (indice-- > 0) {
                cor = cor.seguinte;
            }
        } else {
            cor = base.anterior;

            while (++indice < numeroElementos) {
                cor = cor.anterior;
            }
        }

        return cor;
    }

    @Override
    public void inserir(T elem) {
        new NoComElemento(elem, getNoPorOrdem(elem));
        numeroElementos++;
    }

    private void inserirTodos(ColecaoIteravel<T> colecao) {
        for (T elemento : colecao) {
            inserir(elemento);
        }
    }

    private T removerNo(No no) {
        no.anterior.seguinte = no.seguinte;
        no.seguinte.anterior = no.anterior;
        numeroElementos--;

        return no.elemento;
    }

    @Override
    public T remover(T elem) {
        No no = getNo(elem);
        return no.isElementoIgual(elem) ? removerNo(no) : null;
    }

    @Override
    public T removerPorReferencia(T elem) {
        No no = getNoPorReferencia(elem);
        return no.isElementoIgualPorReferencia(elem) ? removerNo(no) : null;
    }

    @Override
    public T remover(int indice) {
        return removerNo(getNo(indice));
    }

    @Override
    public T consultar(int indice) {
        return getNo(indice).elemento;
    }


    // É necessário de modo a evitar downcast externo de IteradorIteravel a IteradorIteravelDuplo
    @Override
    public IteradorIteravelDuplo<T> consultar(T elem) {
        return new Iterador(elem, elem);
    }

    @Override
    public IteradorIteravelDuplo<T> consultar(T elemInicial, T elemFinal) {
        return new Iterador(elemInicial, elemFinal);
    }

    @Override
    public boolean contemOrdem(T elem) {
        return getNoPorOrdem(elem).compararElemento(elem) == 0;
    }

    @Override
    public boolean contem(T elem) {
        return getNo(elem).isElementoIgual(elem);
    }

    @Override
    public boolean contemReferencia(T elem) {
        return getNoPorReferencia(elem).isElementoIgualPorReferencia(elem);
    }

    @Override
    public int getNumeroElementos() {
        return numeroElementos;
    }

    @Override
    public IteradorIteravelDuplo<T> iterador() {
        return new Iterador();
    }

    @Override
    public Comparacao<T> getComparador() {
        return criterio;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Lista Dupla Circular Base Ordem Máxima Ordenada por ");
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
        protected No anterior;
        protected No seguinte;

        protected abstract boolean isElementoIgual(T elem);

        protected abstract boolean isElementoIgualPorReferencia(T elem);

        protected abstract int compararElemento(T elem);
    }

    protected class Base extends No {

        protected Base() {
            elemento = null;
            seguinte = anterior = this;
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

        protected NoComElemento(T elem, No seg) {
            if (elem == null) {
                throw new IllegalArgumentException();
            }
            elemento = elem;
            anterior = seg.anterior;
            seguinte = seg;
            anterior.seguinte = seguinte.anterior = this;
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


    protected class Iterador implements IteradorIteravelDuplo<T> {
        protected No anteriorAoPrimeiro;
        protected No anterior;
        protected No corrente;
        protected No seguinteAoUltimo;

        protected Iterador() {
            seguinteAoUltimo = anteriorAoPrimeiro = base;
            reiniciar();
        }

        protected Iterador(T elemInicial, T elemFinal) {
            if (criterio.comparar(elemInicial, elemFinal) > 0) {
                throw new IllegalArgumentException();
            }

            anteriorAoPrimeiro = getNoPorOrdem(elemInicial).anterior;

            if (base.anterior.compararElemento(elemFinal) <= 0) {
                seguinteAoUltimo = base;
            } else {

                seguinteAoUltimo = anteriorAoPrimeiro.seguinte;

                while (seguinteAoUltimo != base &&
                        seguinteAoUltimo.compararElemento(elemFinal) <= 0) {
                    seguinteAoUltimo = seguinteAoUltimo.seguinte;
                }
            }

            reiniciar();
        }

        @Override
        public void reiniciar() {
            anterior = seguinteAoUltimo.anterior;
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

            anterior = corrente;
            corrente = corrente.seguinte;
            return corrente.elemento;
        }

        @Override
        public boolean podeRecuar() {
            return anterior != anteriorAoPrimeiro;
        }

        @Override
        public T recuar() {
            if (!podeRecuar()) {
                throw new NoSuchElementException();
            }

            corrente = anterior;
            anterior = anterior.anterior;

            return corrente.elemento;
        }
    }

}