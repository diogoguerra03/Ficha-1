package aed.modelo.contactos;

import aed.colecoes.iteraveis.IteradorIteravel;
import aed.colecoes.iteraveis.IteradorIteravelDuplo;
import aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagemComIncrementoPorHash;
import aed.colecoes.iteraveis.lineares.naoordenadas.estruturas.ListaDuplaCircularBaseNaoOrdenada;
import aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseOrdemMaximaOrdenadaDistinta;
import aed.modelo.contactos.comparadores.ComparacaoDatasNascimentoAsc;

public enum GestorContactosOtimizadoContinuacao {
    INSTANCIA;
    public static final IteradorIteravelDuplo<Contacto> ITERADOR_DUPLO_COLECAO_VAZIA_DE_CONTACTOS = new ListaDuplaCircularBaseNaoOrdenada<Contacto>().iterador();
    public static final IteradorIteravelDuplo<String> ITERADOR_VAZIO_STRINGS = new ListaDuplaCircularBaseNaoOrdenada<String>().iterador();
    private TabelaHashPorSondagemComIncrementoPorHash<Data, GestorContactosDataNascimentoContinuacao> contactosPorDataNascimento; // THH TabelaHashPorHash
    private TabelaHashPorSondagemComIncrementoPorHash<Long, Contacto> contactosPorNumeroTelefone; // THH TabelaHashPorHash
    private ListaDuplaCircularBaseOrdemMaximaOrdenadaDistinta<Data> datasNascimento;


    GestorContactosOtimizadoContinuacao() {
        contactosPorDataNascimento = new TabelaHashPorSondagemComIncrementoPorHash<>(100);
        datasNascimento = new ListaDuplaCircularBaseOrdemMaximaOrdenadaDistinta<>(ComparacaoDatasNascimentoAsc.CRITERIO);
        contactosPorNumeroTelefone = new TabelaHashPorSondagemComIncrementoPorHash<>(100);
    }

    public void inserir(Contacto contacto) {
        contactosPorNumeroTelefone.inserir(contacto.getNumeroTelefone(), contacto);
        Data dataNascimento = contacto.getDataNascimento();
        GestorContactosDataNascimentoContinuacao contactosDataNascimento = contactosPorDataNascimento.consultar(dataNascimento);
        if (contactosDataNascimento == null) {

            //contactosDataNascimento = contactosDataNascimentoVazio;
            contactosDataNascimento = new GestorContactosDataNascimentoContinuacao(dataNascimento);
            contactosPorDataNascimento.inserir(dataNascimento, contactosDataNascimento);
        }
        contactosDataNascimento.inserir(contacto);
    }

    public Contacto remover(Contacto contacto) {
        long numeroTelefone = contacto.getNumeroTelefone();
        if (!contacto.equals(contactosPorNumeroTelefone.consultar(numeroTelefone))) {
            return null;
        }
        Contacto contactoRemovido = contactosPorNumeroTelefone.remover(numeroTelefone);

        removerDosContactosPorDataNascimento(contactoRemovido);
        return contactoRemovido;
    }

    public Contacto consultar(long numeroTelefone) {
        return contactosPorNumeroTelefone.consultar(numeroTelefone);
    }

    public Contacto remover(long numeroTelefone) {
        Contacto contactoRemovido = contactosPorNumeroTelefone.remover(numeroTelefone);
        if (contactoRemovido == null) {
            return null;
        }
        removerDosContactosPorDataNascimento(contactoRemovido);
        return contactoRemovido;
    }

    private void removerDosContactosPorDataNascimento(Contacto contactoRemovido) {
        Data dataNascimento = contactoRemovido.getDataNascimento();
        GestorContactosDataNascimentoContinuacao contactosDataNascimento = contactosPorDataNascimento.consultar(dataNascimento);

        contactosDataNascimento.remover(contactoRemovido);
        if (contactosDataNascimento.isVazia()) {
            contactosPorDataNascimento.remover(dataNascimento);
            datasNascimento.remover(dataNascimento);
        }
    }

    public IteradorIteravelDuplo<Contacto> remover(Data dataNascimento) {
        GestorContactosDataNascimentoContinuacao contactosDataNascimento = contactosPorDataNascimento.remover(dataNascimento);
        if (contactosDataNascimento == null) {
            return ITERADOR_DUPLO_COLECAO_VAZIA_DE_CONTACTOS;
        }
        datasNascimento.remover(dataNascimento);
        IteradorIteravelDuplo<Contacto> iteradorContactos = contactosDataNascimento.iterador();
        for (Contacto contacto : iteradorContactos) {
            contactosPorNumeroTelefone.remover(contacto.getNumeroTelefone());
        }
        iteradorContactos.reiniciar(); // se não tiver isto, devolve um iterador VAZIO!!!
        return iteradorContactos;
    }

    public IteradorIteravelDuplo<Contacto> consultar(Data dataNascimento) {
        GestorContactosDataNascimentoContinuacao contactosDataNascimento = contactosPorDataNascimento.consultar(dataNascimento);
        return contactosDataNascimento != null ?
                contactosDataNascimento.iterador() :
                ITERADOR_DUPLO_COLECAO_VAZIA_DE_CONTACTOS;
    }

    public IteradorIteravelDuplo<Contacto> consultar(Data dataNascimentoInicio, Data dataNascimentoFim) {
        return new Iterador(dataNascimentoInicio, dataNascimentoFim);
    }

    public IteradorIteravelDuplo<Contacto> consultar(Data dataNascimento, String morada) {
        GestorContactosDataNascimentoContinuacao contactosDataNascimento = contactosPorDataNascimento.consultar(dataNascimento);

        return contactosDataNascimento == null ? ITERADOR_DUPLO_COLECAO_VAZIA_DE_CONTACTOS : contactosDataNascimento.consultar(morada);
    }

    public IteradorIteravel<String> consultarMoradas(Data dataNascimento) {
        GestorContactosDataNascimentoContinuacao contactosDataNascimento = contactosPorDataNascimento.consultar(dataNascimento);

        return contactosDataNascimento == null ? ITERADOR_VAZIO_STRINGS : contactosDataNascimento.getMoradas();
    }

    private class Iterador implements IteradorIteravelDuplo<Contacto> {
        private IteradorIteravelDuplo<Data> iteradorDatasNascimento;
        private IteradorIteravelDuplo<Contacto> iteradorDeContactos;

        public Iterador(Data dataNascimentoInicio, Data dataNascimentoFim) {
            iteradorDatasNascimento = datasNascimento.consultar(dataNascimentoInicio, dataNascimentoFim);
            reiniciar();
        }

        @Override
        public void reiniciar() {
            iteradorDatasNascimento.reiniciar();
            iteradorDeContactos = ITERADOR_DUPLO_COLECAO_VAZIA_DE_CONTACTOS;
        }

        @Override
        public Contacto corrente() {
            return iteradorDeContactos.corrente();
        }

        @Override
        public boolean podeAvancar() {
            return iteradorDeContactos.podeAvancar() || iteradorDatasNascimento.podeAvancar();
        }

        @Override
        public Contacto avancar() {
            if (!iteradorDeContactos.podeAvancar()) {
                Data dataNascimento = iteradorDatasNascimento.avancar();
                iteradorDeContactos = contactosPorDataNascimento.consultar(dataNascimento).iterador();
            }
            return iteradorDeContactos.avancar();
        }

        @Override
        public boolean podeRecuar() {
            return iteradorDeContactos.podeRecuar() || iteradorDatasNascimento.podeRecuar();
        }

        @Override
        public Contacto recuar() {
            if (!iteradorDeContactos.podeRecuar()) {
                Data dataNascimneto = iteradorDatasNascimento.recuar();
                iteradorDeContactos = contactosPorDataNascimento.consultar(dataNascimneto).iterador();
            }
            return iteradorDeContactos.recuar();
        }
    }
}
