package aed.colecoes.iteraveis.lineares.naoordenadas.estruturas;

import aed.colecoes.iteraveis.IteradorIteravel;
import aed.colecoes.iteraveis.IteradorIteravelDuplo;
import aed.colecoes.iteraveis.lineares.naoordenadas.ColecaoIteravelLinearNaoOrdenada;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class ListaDuplaNaoOrdenada<T> implements ColecaoIteravelLinearNaoOrdenada<T> {

    private No noInicial;
    private No noFinal;
    private int numeroElementos;

    public ListaDuplaNaoOrdenada() {
        noInicial = noFinal = null;
        numeroElementos = 0;
    }

    @Override
    public IteradorIteravelDuplo<T> iterador() {
        return new Iterador();
    }

    @Override
    public int getNumeroElementos() {
        return 0;
    }

    @Override
    public T remover(T elem) {
        No no = getNo(elem);
        return no != null ? removerNo(no) : null;
    }

    @Override
    public T removerPorReferencia(T elem) {
        No no = getNoPorReferencia(elem);
        return no != null ? removerNo(no) : null;
    }

    @Override
    public T remover(int indice) {
        return removerNo(getNo(indice));
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
        if(numeroElementos == 0){
            return null;
        }
        No aux = noInicial;
        noInicial = aux.seguinte;
        if (--numeroElementos == 0){
            noFinal = null;
        }
        else{
            noInicial.anterior = null;
        }

        return aux.elemento;
    }

    @Override
    public T removerDoFim() {
        if (numeroElementos == 0){
            return null;
        }
        No aux = noFinal;
        noFinal = aux.anterior;
        if (--numeroElementos == 0){
            noInicial = null;
        }
        else{
            noFinal.seguinte = null;
        }

        return aux.elemento;
    }

    private T removerNo(No no){
        if (no == noInicial){
            noInicial = noInicial.seguinte;
        }
        else{
            no.anterior.seguinte = no.seguinte;
        }

        if(no == noFinal){
            noFinal = noFinal.anterior;
        }
        else{
            no.seguinte.anterior = no.anterior;
        }
        numeroElementos--;
        return no.elemento;
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

    protected class Iterador implements IteradorIteravelDuplo<T> {
        protected No anterior;
        protected No corrente;
        protected No seguinte;

        public Iterador() {

        }

        @Override
        public void reiniciar() {
            anterior = noFinal;
            seguinte = noInicial;
            corrente = null;
        }

        @Override
        public T corrente() {
            if (corrente == null) {
                throw new NoSuchElementException();
            }
            return corrente.elemento;
        }

        @Override
        public boolean podeAvancar() {
            return seguinte != null;
        }

        @Override
        public T avancar() {
            if (!podeAvancar()){
                throw new NoSuchElementException();
            }
            anterior = corrente;
            corrente = seguinte;
            seguinte = seguinte.seguinte;
            return corrente.elemento;
        }

        @Override
        public boolean podeRecuar(){
            return anterior != null;
        }

        @Override
        public T recuar(){
            if (!podeRecuar()){
                throw new NoSuchElementException();
            }
            seguinte = corrente;
            corrente = anterior;
            anterior = anterior.anterior;

            return corrente.elemento;
        }

    }
}

