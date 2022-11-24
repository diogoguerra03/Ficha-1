package aed.colecoes.iteraveis.lineares.naoordenadas.utilizacao;

import aed.colecoes.iteraveis.IteradorIteravelDuplo;
import aed.colecoes.iteraveis.lineares.naoordenadas.estruturas.ListaDuplaCircularBaseNaoOrdenada;
import aed.modelo.pessoas.Pessoa;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class MainTeoricaListaDuplaCircularBaseNaoOrdenada {

    public static void main(String[] args) {
        Pessoa p;
        ListaDuplaCircularBaseNaoOrdenada<Pessoa> listaPessoas = new ListaDuplaCircularBaseNaoOrdenada<>();

        p = new Pessoa(3, "B");
        listaPessoas.inserirNoFim(p);
        p = new Pessoa(1, "C");
        listaPessoas.inserirNoInicio(p);
        p = new Pessoa(1, "C");
        listaPessoas.inserir(p);
        p = new Pessoa(2, "A");
        listaPessoas.inserir(2, p);

        System.out.println("listaPessoas\n" + listaPessoas);

        System.out.println("Pessoas de nome < C");
        for (Pessoa pessoa : listaPessoas) {
            if (pessoa.getNome().compareTo("C") < 0) {
                System.out.println(pessoa);
            }
        }

        System.out.println("\nPessoas lista");
        IteradorIteravelDuplo<Pessoa> iteradorListaPessoas = listaPessoas.iterador();
        while (iteradorListaPessoas.podeAvancar()) {
            iteradorListaPessoas.avancar();
            System.out.println(iteradorListaPessoas.corrente());
        }

        System.out.println("\nPessoas lista inverso");
        iteradorListaPessoas.reiniciar();
        while (iteradorListaPessoas.podeRecuar()) {
            iteradorListaPessoas.recuar();
            System.out.println(iteradorListaPessoas.corrente());
        }

        listaPessoas.removerPorReferencia(listaPessoas.consultar(3));
        listaPessoas.remover(1);
        listaPessoas.removerDoInicio();
        listaPessoas.removerDoFim();
        System.out.println("\nlistaPessoas\n" + listaPessoas);
    }
}
