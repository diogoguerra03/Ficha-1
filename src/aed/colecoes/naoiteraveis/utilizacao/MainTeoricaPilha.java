package aed.colecoes.naoiteraveis.utilizacao;

import aed.colecoes.naoiteraveis.estruturas.Pilha;
import aed.modelo.pessoas.Pessoa;

public class MainTeoricaPilha {

    public static void main(String[] args) {
        Pilha<Pessoa> pilhaPessoas = new Pilha<>();

        pilhaPessoas.inserir(new Pessoa(3, "B"));
        pilhaPessoas.inserir(new Pessoa(1, "C"));
        pilhaPessoas.inserir(new Pessoa(2, "A"));

        System.out.println("pilhaPessoas\n" + pilhaPessoas);

        pilhaPessoas.remover();
        System.out.println("pilhaPessoas\n" + pilhaPessoas);

        System.out.println(pilhaPessoas.consultar());

        pilhaPessoas.remover();
        System.out.println("pilhaPessoas\n" + pilhaPessoas);
    }
}
