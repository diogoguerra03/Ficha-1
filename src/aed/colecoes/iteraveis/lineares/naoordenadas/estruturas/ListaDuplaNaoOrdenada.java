package aed.colecoes.iteraveis.lineares.naoordenadas.estruturas;

import aed.colecoes.Colecao;
import aed.colecoes.iteraveis.IteradorIteravel;
import aed.colecoes.iteraveis.lineares.naoordenadas.ColecaoIteravelLinearNaoOrdenada;

import java.io.Serializable;

public class ListaDuplaNaoOrdenada<T> implements ColecaoIteravelLinearNaoOrdenada<T> {

    private No noInicial;
    private No noFinal;
    private int numeroElementos;

    @Override
    public IteradorIteravel<T> iterador() {
        return null;
    }

    @Override
    public int getNumeroElementos() {
        return 0;
    }

    @Override
    public T remover(T elem) {
        return null;
    }

    @Override
    public T removerPorReferencia(T elem) {
        return null;
    }

    @Override
    public T remover(int indice) {
        return null;
    }

    @Override
    public T consultar(int indice) {
        return getNo(indice).elemento;
    }

    @Override
    public boolean contem(T elem) {
        return getNo(elem) != null;
    }

    @Override
    public boolean contemReferencia(T elem) {
        return getNoPorReferencia(elem) != null;
    }

    @Override
    public void inserirNoInicio(T elem) {
        noInicial = new No(elem);
        if (++numeroElementos == 1){
            noFinal = noInicial = new No(elem);
        }
        else {
            noInicial = new No(elem, noInicial);
        }
    }

    @Override
    public void inserirNoFim(T elem) {
        noFinal = new No(elem);
        if (++numeroElementos == 1){
            noInicial = noFinal;
        }
    }

    private No getNo(T elem){
        No cor = noInicial;
        while(cor != null && !cor.elemento.equals(elem)){
            cor = cor.seguinte;
        }
        return cor;
    }

    private No getNoPorReferencia(T elem){
        No cor = noInicial;
        while(cor != null && cor.elemento != elem){
            cor = cor.seguinte;
        }
        return cor;
    }

    @Override
    public void inserir(int indice, T elem) {
        if (indice == 0){
            inserirNoInicio(elem);
        }
        else if (indice == numeroElementos) {
            inserirNoFim(elem);
        }
        else {
            new No(elem, getNo(indice));
            numeroElementos++;
        }
    }

    private No getNo(int indice) {
        if (indice < 0 || indice <= numeroElementos){
            throw new IndexOutOfBoundsException();
        }
        No cor;
        if (indice < numeroElementos / 2){
            cor = noInicial;
            while (indice-- > 0){
                cor = cor.seguinte;
            }
        }
        else{
            cor = noFinal;
            while(++indice < numeroElementos){
                cor = cor.anterior;
            }
        }
        return cor;
    }

    @Override
    public T removerDoInicio() {
        return null;
    }

    @Override
    public T removerDoFim() {
        return null;
    }

    protected class No implements Serializable {
        protected T elemento;
        protected No anterior;
        protected No seguinte;

        // Criação de nó com elemento elem depois de noFinal
        public No(T elem) {
            this.elemento = elem;
            anterior = noFinal;
            seguinte = null;
            if (noFinal != null){
                noFinal.seguinte = this;
            }
        }

        // Ciração de nó com elemento elem e inserção antes de nó seg
        public No(T elem, No seg) {
            elem = elem;
            anterior = seg.anterior;
            seguinte = seg;
            seguinte.anterior = this;
            if (anterior != null){
                anterior.seguinte = this;
            }

        }
    }
}

