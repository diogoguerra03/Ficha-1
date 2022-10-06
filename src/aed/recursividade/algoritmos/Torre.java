package aed.recursividade.algoritmos;

import java.util.NoSuchElementException;

public class Torre {
    private char nome; // Nome da torre
    private int[] baseEDiscos;
    private int numeroDiscos; // nr de discos na torre

    // construtor
    public Torre(char nome, int numeroMaximoDiscos) {
        this.nome = nome;
        baseEDiscos = new int[numeroMaximoDiscos + 1];
        baseEDiscos[0] = 3 * numeroMaximoDiscos;
        numeroDiscos = 0;
    }

    public void preencherComDiscos(){
        for (int i = 1; i < baseEDiscos.length; i++) {
            baseEDiscos[i] = baseEDiscos.length - i;
        }

        numeroDiscos = baseEDiscos.length - 1;
    }

    public void moverDisco(Torre torreDeDestino){
        if (numeroDiscos == 0){
            throw new NoSuchElementException("Torre de origem vazia");
        }
        if (torreDeDestino.numeroDiscos == torreDeDestino.baseEDiscos.length - 1){
            throw new ArrayIndexOutOfBoundsException("Torre de destino cheia");
        }
        if (torreDeDestino.baseEDiscos[torreDeDestino.numeroDiscos] < baseEDiscos[numeroDiscos]){
            throw new IllegalArgumentException("Disco do topo da torre de origem maior do que disco do topo da torre de destino");
        }
        if (baseEDiscos.length != torreDeDestino.baseEDiscos.length){
            throw new IllegalArgumentException("Torres de tamanho diferente");
        }

        torreDeDestino.baseEDiscos[++torreDeDestino.numeroDiscos] = baseEDiscos[numeroDiscos];
        // torreDeDestino.numeroDiscos++; em cima temos o ++ para pré incremento
        baseEDiscos[numeroDiscos--] = 0;
        //numeroDiscos--; pós decremento
    }
}
