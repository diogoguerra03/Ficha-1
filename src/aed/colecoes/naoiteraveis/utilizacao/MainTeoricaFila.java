package aed.colecoes.naoiteraveis.utilizacao;

import aed.colecoes.naoiteraveis.estruturas.Fila;
import aed.modelo.pessoas.Pessoa;

public class MainTeoricaFila {

    public static void main(String[] args) {
        Fila<Pessoa> filaPessoas = new Fila<>();

        filaPessoas.inserir(new Pessoa(3, "B"));
        filaPessoas.inserir(new Pessoa(1, "C"));
        filaPessoas.inserir(new Pessoa(2, "A"));

        System.out.println("filaPessoas\n" + filaPessoas);

        filaPessoas.remover();
        System.out.println("filaPessoas\n" + filaPessoas);

        System.out.println(filaPessoas.consultar());

        filaPessoas.remover();
        System.out.println("filaPessoas\n" + filaPessoas);
    }
}
