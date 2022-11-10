package aed.colecoes.iteraveis.associativas.utilizacao;

import aed.colecoes.iteraveis.IteradorIteravel;
import aed.colecoes.iteraveis.associativas.estruturas.Associacao;
import aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagemComIncrementoPorHash;
import aed.modelo.pessoas.Pessoa;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public class MainTeoricaTabelaHashPorSondagemComIncrementoPorHash {

    public static void main(String[] args) {
        TabelaHashPorSondagemComIncrementoPorHash<Long, Pessoa> tabelaHashPessoasBI =
                new TabelaHashPorSondagemComIncrementoPorHash<>(5);

        tabelaHashPessoasBI.inserir(3L, new Pessoa(3, "B"));
        tabelaHashPessoasBI.inserir(2L, new Pessoa(2, "C"));
        tabelaHashPessoasBI.remover(3L);
        System.out.println("\nTabelaHashPessoasBI\n" + tabelaHashPessoasBI);
        tabelaHashPessoasBI.inserir(7L, new Pessoa(7, "C"));
        tabelaHashPessoasBI.inserir(13L, new Pessoa(13, "A"));

        System.out.println("TabelaHashPessoasBI\n" + tabelaHashPessoasBI);

        System.out.println("\nConsultar Chave: 2 " + tabelaHashPessoasBI.consultar(2L));
        System.out.println("\nConsultar Chave: 12 " + tabelaHashPessoasBI.consultar(12L));

        tabelaHashPessoasBI.inserir(12L, new Pessoa(12, "F"));
        tabelaHashPessoasBI.inserir(23L, new Pessoa(23, "H"));
        System.out.println("\nTabelaHashPessoasBI\n" + tabelaHashPessoasBI);

        tabelaHashPessoasBI.remover(2L);
        System.out.println("\nTabelaHashPessoasBI\n" + tabelaHashPessoasBI);

        System.out.println("\nConsultar Chave: 13 " + tabelaHashPessoasBI.consultar(13L));

        IteradorIteravel<Associacao<Long, Pessoa>> iteradorTabelaHashPessoasBI = tabelaHashPessoasBI.iterador();

        System.out.println("Pessoas < C da tabelaHashPessoasBI");
        Associacao<Long, Pessoa> a;
        while (iteradorTabelaHashPessoasBI.podeAvancar()) {
            a = iteradorTabelaHashPessoasBI.avancar();
            if (a.getValor().getNome().compareTo("C") < 0) {
                System.out.println(iteradorTabelaHashPessoasBI.corrente());
            }
        }

        System.out.println("Pessoas da tabelaHashPessoasBI");
        for (Pessoa pessoa : tabelaHashPessoasBI.iteradorValores()) {
            System.out.println(pessoa);
        }

        System.out.println("BI da tabelaHashPessoasBI");
        for (Long bi : tabelaHashPessoasBI.iteradorChaves()) {
            System.out.println(bi);
        }

        iteradorTabelaHashPessoasBI.reiniciar();
        System.out.println("\nPrimeiro elemento da tabelaHashPessoasBI");
        System.out.println(iteradorTabelaHashPessoasBI.avancar());
    }
}
