package aed.modelo.contactos;

import aed.colecoes.iteraveis.IteradorIteravel;
import aed.colecoes.iteraveis.IteradorIteravelDuplo;
import aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagemComIncrementoPorHash;
import aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseOrdemMaximaOrdenada;
import aed.modelo.contactos.comparadores.ComparacaoContactosPorPrimeiroNomeAscendenteSegPorUltimoNomeAscendente;

import java.util.Objects;

public class GestorContactosDataNascimentoContinuacao {
    private Data dataNascimento;
    private ListaDuplaCircularBaseOrdemMaximaOrdenada<Contacto> contactos;

    private TabelaHashPorSondagemComIncrementoPorHash<String, ListaDuplaCircularBaseOrdemMaximaOrdenada<Contacto>> contactosPorMorada;

    public GestorContactosDataNascimentoContinuacao(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
        contactos = new ListaDuplaCircularBaseOrdemMaximaOrdenada<>(ComparacaoContactosPorPrimeiroNomeAscendenteSegPorUltimoNomeAscendente.CRITERIO);
        contactosPorMorada = new TabelaHashPorSondagemComIncrementoPorHash<>(100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestorContactosDataNascimentoContinuacao that = (GestorContactosDataNascimentoContinuacao) o;
        return Objects.equals(dataNascimento, that.dataNascimento) && Objects.equals(contactos, that.contactos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataNascimento, contactos);
    }

    public int compareTo(GestorContactosDataNascimentoContinuacao o2) {
        return dataNascimento.compareTo(o2.dataNascimento);
    }

    public void inserir(Contacto contacto) {
        contactos.inserir(contacto);
        String morada = contacto.getMorada();
        ListaDuplaCircularBaseOrdemMaximaOrdenada<Contacto> contactosMorada = contactosPorMorada.consultar(morada);
        if (contactosMorada == null) {
            contactosMorada = new ListaDuplaCircularBaseOrdemMaximaOrdenada<>(ComparacaoContactosPorPrimeiroNomeAscendenteSegPorUltimoNomeAscendente.CRITERIO);
            contactosPorMorada.inserir(morada, contactosMorada);
        }
        contactosMorada.inserir(contacto);
    }

    public Contacto remover(Contacto contacto) {
        String morada = contacto.getMorada();
        ListaDuplaCircularBaseOrdemMaximaOrdenada<Contacto> contactosMorada = contactosPorMorada.consultar(morada);
        if (contactosMorada == null) {
            return null;
        }
        Contacto contactoRemovido = contactos.remover(contacto);
        contactosMorada.remover(contacto);
        if (contactosMorada.isVazia()) {
            contactosPorMorada.remover(morada);
        }
        return contactoRemovido;
    }

    public boolean isVazia() {
        return contactos.isVazia();
    }

    public IteradorIteravelDuplo<Contacto> iterador() {
        return contactos.iterador();
    }

    public IteradorIteravelDuplo<Contacto> consultar(String morada) {
        ListaDuplaCircularBaseOrdemMaximaOrdenada<Contacto> contactosMorada = contactosPorMorada.consultar(morada);

        return contactosMorada == null ? GestorContactosOtimizadoContinuacao.ITERADOR_DUPLO_COLECAO_VAZIA_DE_CONTACTOS : contactosMorada.iterador();
    }

    public IteradorIteravel<String> getMoradas() {
        return contactosPorMorada.iteradorChaves();
    }
}