package aed.modelo.pessoas.comparadores;


import aed.Comparacao;
import aed.modelo.pessoas.Pessoa;

/**
 * @author Actual code:
 * Carlos Urbano<carlos.urbano@ipleiria.pt>
 * Catarina Reis<catarina.reis@ipleiria.pt>
 * Marco Ferreira<marco.ferreira@ipleiria.pt>
 * João Ramos<joao.f.ramos@ipleiria.pt>
 * Original code: José Magno<jose.magno@ipleiria.pt>
 */
public enum ComparacaoPessoasPorBIAsc implements Comparacao<Pessoa> {
    CRITERIO;

    @Override
    public int comparar(Pessoa o1, Pessoa o2) {
        return Long.compare(o1.getBI(), o2.getBI());
    }
}
