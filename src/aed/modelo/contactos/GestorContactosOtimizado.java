package aed.modelo.contactos;

import aed.colecoes.iteraveis.IteradorIteravelDuplo;
import aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagemComIncrementoPorHash;
import aed.colecoes.iteraveis.lineares.naoordenadas.estruturas.ListaDuplaCircularBaseNaoOrdenada;
import aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseOrdemMaximaOrdenadaDistinta;
import aed.modelo.contactos.comparadores.ComparacaoDatasNascimentoAsc;

public enum GestorContactosOtimizado {
    INSTANCIA;

    public static final IteradorIteravelDuplo<Contacto>
            ITERADOR_DUPLO_COLECAO_VAZIA_CONTACTOS =
            new ListaDuplaCircularBaseNaoOrdenada<Contacto>().iterador();

    private TabelaHashPorSondagemComIncrementoPorHash
            <Data, GestorContactosDataNascimento> contactosPorDataNascimento;

    private ListaDuplaCircularBaseOrdemMaximaOrdenadaDistinta<Data> datasNascimento;

    GestorContactosOtimizado() {
        contactosPorDataNascimento = new TabelaHashPorSondagemComIncrementoPorHash<>(100);
        datasNascimento = new ListaDuplaCircularBaseOrdemMaximaOrdenadaDistinta<>(ComparacaoDatasNascimentoAsc.CRITERIO);
    }

    public void inserir(Contacto contacto) {
        Data dataNascimento = contacto.getDataNascimento();
        GestorContactosDataNascimento contactosDataNascimento =
                contactosPorDataNascimento.consultar(dataNascimento);
        if (contactosDataNascimento == null){
            contactosDataNascimento = new GestorContactosDataNascimento(dataNascimento);
            contactosPorDataNascimento.inserir(dataNascimento, contactosDataNascimento);
        }
        contactosDataNascimento.inserir(contacto);
    }

    public Contacto remover(Contacto contacto) {
        Data dataNascimento = contacto.getDataNascimento();
        GestorContactosDataNascimento contactosDataNascimento =
               contactosPorDataNascimento.consultar(dataNascimento);

       if (contactosDataNascimento == null){
           return null;
       }
       Contacto contactoRemovido = contactosDataNascimento.remover(contacto);
       if (contactosDataNascimento.isVazia()){
           contactosPorDataNascimento.remover(dataNascimento);
           datasNascimento.remover(dataNascimento);
       }
        return contactoRemovido;
    }

    public IteradorIteravelDuplo<Contacto> remover(Data dataNascimento) {
        GestorContactosDataNascimento contactosDataNascimento =
                contactosPorDataNascimento.remover
                        (dataNascimento);
        if (contactosDataNascimento == null){
            return ITERADOR_DUPLO_COLECAO_VAZIA_CONTACTOS;
        }
        datasNascimento.remover(dataNascimento);
        return contactosDataNascimento.iterador();
    }


    public IteradorIteravelDuplo<Contacto> consultar(Data dataNascimento) {

        GestorContactosDataNascimento contactosDataNascimento = contactosPorDataNascimento.consultar
                (dataNascimento);
        return contactosDataNascimento != null ?
                contactosDataNascimento.iterador() :
                ITERADOR_DUPLO_COLECAO_VAZIA_CONTACTOS;
    }


    public IteradorIteravelDuplo<Contacto> consultar(Data dataNascimentoInicio, Data dataNascimentoFim) {
        return new Iterador(dataNascimentoInicio, dataNascimentoFim);
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
            iteradorDeContactos = ITERADOR_DUPLO_COLECAO_VAZIA_CONTACTOS;
        }

        @Override
        public Contacto corrente() {
            return iteradorDeContactos.corrente();
        }

        @Override
        public boolean podeAvancar() {
            return iteradorDeContactos.podeAvancar() ||
                    iteradorDatasNascimento.podeAvancar();
        }

        @Override
        public Contacto avancar() {
            if (!iteradorDeContactos.podeAvancar()){
                iteradorDeContactos =
                        contactosPorDataNascimento.consultar(iteradorDatasNascimento.avancar()).iterador();
            }
            return iteradorDeContactos.avancar();
        }

        @Override
        public boolean podeRecuar() {
            return iteradorDeContactos.podeRecuar() ||
                    iteradorDatasNascimento.podeRecuar();
        }

        @Override
        public Contacto recuar() {
            if (!iteradorDeContactos.podeRecuar()){
                iteradorDeContactos = contactosPorDataNascimento.consultar(iteradorDatasNascimento.recuar()).iterador();
            }
            return iteradorDeContactos.recuar();
        }
    }
}
