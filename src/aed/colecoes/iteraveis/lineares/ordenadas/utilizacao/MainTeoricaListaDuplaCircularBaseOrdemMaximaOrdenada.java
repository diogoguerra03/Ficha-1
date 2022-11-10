package aed.colecoes.iteraveis.lineares.ordenadas.utilizacao;

import aed.colecoes.iteraveis.IteradorIteravelDuplo;
import aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseOrdemMaximaOrdenada;
import aed.modelo.pessoas.Pessoa;
import aed.modelo.pessoas.comparadores.ComparacaoPessoasPorBIAsc;
import aed.modelo.pessoas.comparadores.ComparacaoPessoasPorNomeAsc;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class MainTeoricaListaDuplaCircularBaseOrdemMaximaOrdenada {

    public static void main(String[] args) {
        Pessoa p;
        ListaDuplaCircularBaseOrdemMaximaOrdenada<Pessoa> listaPessoasBI = new ListaDuplaCircularBaseOrdemMaximaOrdenada<>(
                ComparacaoPessoasPorBIAsc.CRITERIO);
        ListaDuplaCircularBaseOrdemMaximaOrdenada<Pessoa> listaPessoasNome = new ListaDuplaCircularBaseOrdemMaximaOrdenada<>(
                ComparacaoPessoasPorNomeAsc.CRITERIO);

        p = new Pessoa(3, "B");
        listaPessoasBI.inserir(p);
        listaPessoasNome.inserir(p);
        p = new Pessoa(7, "C");
        listaPessoasBI.inserir(p);
        listaPessoasNome.inserir(p);
        p = new Pessoa(5, "G");
        listaPessoasBI.inserir(p);
        listaPessoasNome.inserir(p);
        p = new Pessoa(1, "C");
        listaPessoasBI.inserir(p);
        listaPessoasNome.inserir(p);

        System.out.println("listaPessoasBI\n" + listaPessoasBI);
        System.out.println("listaPessoasNome\n" + listaPessoasNome);

        System.out.println("Pessoas de nome < C");
        for (Pessoa pessoa : listaPessoasBI) {
            if (pessoa.getNome().compareTo("C") < 0) {
                System.out.println(pessoa);
            }
        }

        System.out.println("\nPessoas de nome C");
        IteradorIteravelDuplo<Pessoa> it = listaPessoasNome.consultar(new Pessoa(0, "C"));
        while (it.podeAvancar()) {
            it.avancar();
            System.out.println(it.corrente());
        }

        listaPessoasBI.remover(p);

        System.out.println("\nPessoas de nome entre A e E inclusive");
        for (Pessoa pessoa : listaPessoasNome.consultar(new Pessoa(0, "A"), new Pessoa(0, "E"))) {
            System.out.println(pessoa);
        }

        System.out.println("\nPessoas de BI entre 2 e 6 inclusive por ordem inversa");
        it = listaPessoasBI.consultar(new Pessoa(2, ""), new Pessoa(6, ""));
        while (it.podeRecuar()) {
            System.out.println(it.recuar());
        }

        listaPessoasNome.remover(1);

        System.out.println("\nlistaPessoasBI\n" + listaPessoasBI);
        System.out.println("listaPessoasNome\n" + listaPessoasNome);

        System.out.println(listaPessoasBI.consultar(1));
    }
}
