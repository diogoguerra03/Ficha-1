package aed.modelo.contactos;

import aed.colecoes.iteraveis.IteradorIteravelDuplo;
import aed.colecoes.iteraveis.lineares.naoordenadas.estruturas.ListaDuplaCircularBaseNaoOrdenada;
import aed.colecoes.iteraveis.lineares.naoordenadas.estruturas.ListaDuplaNaoOrdenada;
import aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseOrdemMaximaOrdenadaDistinta;
import aed.modelo.contactos.comparadores.ComparacaoGestorContactosDataNascimentoAsc;

public enum GestorContactos {
    INSTANCIA;

    public static final IteradorIteravelDuplo<Contacto> ITERADOR_DUPLO_COLECAO_VAZIA_CONTACTOS = new ListaDuplaCircularBaseNaoOrdenada<Contacto>().iterador();
    private ListaDuplaCircularBaseOrdemMaximaOrdenadaDistinta
        <GestorContactosDataNascimento> contactosPorDataNascimento;

    GestorContactos() {
        contactosPorDataNascimento = new ListaDuplaCircularBaseOrdemMaximaOrdenadaDistinta<>
                (ComparacaoGestorContactosDataNascimentoAsc.CRITERIO);
    }

    public void inserir(Contacto contacto) {
        GestorContactosDataNascimento contactosDataNascimentoVazio =
                new GestorContactosDataNascimento(contacto.getDataNascimento());
        GestorContactosDataNascimento contactosDataNascimento =
                contactosPorDataNascimento.consultarDistinto(contactosDataNascimentoVazio);
        if (contactosDataNascimento == null){
            contactosDataNascimento = contactosDataNascimentoVazio;
            contactosPorDataNascimento.inserir(contactosDataNascimento);
        }
        contactosDataNascimento.inserir(contacto);
    }

    public Contacto remover(Contacto contacto) {
       GestorContactosDataNascimento contactosDataNascimento =
               contactosPorDataNascimento.consultarDistinto(
               new GestorContactosDataNascimento(contacto.getDataNascimento()));
       if (contactosDataNascimento == null){
           return null;
       }
       Contacto contactoRemovido = contactosDataNascimento.remover(contacto);
       if (contactosDataNascimento.isVazia()){
           contactosPorDataNascimento.remover(contactosDataNascimento);
       }
        return contactoRemovido;
    }

    public IteradorIteravelDuplo<Contacto> remover(Data dataNascimento) {
        GestorContactosDataNascimento contactosDataNascimento =
                contactosPorDataNascimento.remover
                        (new GestorContactosDataNascimento(dataNascimento));
        return contactosPorDataNascimento != null ?
                contactosDataNascimento.iterador() :
                ITERADOR_DUPLO_COLECAO_VAZIA_CONTACTOS;
    }


    public IteradorIteravelDuplo<Contacto> consultar(Data dataNascimento) {

        GestorContactosDataNascimento contactosDataNascimento = contactosPorDataNascimento.consultarDistinto
                (new GestorContactosDataNascimento(dataNascimento));
        return contactosDataNascimento != null ?
                contactosDataNascimento.iterador() :
                ITERADOR_DUPLO_COLECAO_VAZIA_CONTACTOS;
    }


    public IteradorIteravelDuplo<Contacto> consultar(Data dataNascimentoInicio, Data dataNascimentoFim) {
        return new Iterador(dataNascimentoInicio, dataNascimentoFim);
    }

    private class Iterador implements IteradorIteravelDuplo<Contacto> {
        private IteradorIteravelDuplo<GestorContactosDataNascimento>
                iteradorGestores;

        private IteradorIteravelDuplo<Contacto> iteradorDeContactos;

        public Iterador(Data dataNascimentoInicio, Data dataNascimentoFim) {
            iteradorGestores =
            contactosPorDataNascimento.consultar
                    (new GestorContactosDataNascimento(dataNascimentoInicio),
                        new GestorContactosDataNascimento(dataNascimentoFim));
            reiniciar();
        }

        @Override
        public void reiniciar() {
            iteradorGestores.reiniciar();
            iteradorDeContactos = ITERADOR_DUPLO_COLECAO_VAZIA_CONTACTOS;
        }

        @Override
        public Contacto corrente() {
            return iteradorDeContactos.corrente();
        }

        @Override
        public boolean podeAvancar() {
            return iteradorDeContactos.podeAvancar() ||
                    iteradorGestores.podeAvancar();
        }

        @Override
        public Contacto avancar() {
            if (!iteradorDeContactos.podeAvancar()){
                iteradorDeContactos = iteradorGestores.avancar().iterador();
            }
            return iteradorDeContactos.avancar();
        }

        @Override
        public boolean podeRecuar() {
            return iteradorDeContactos.podeRecuar() ||
                    iteradorGestores.podeRecuar();
        }

        @Override
        public Contacto recuar() {
            if (!iteradorDeContactos.podeRecuar()){
                iteradorDeContactos = iteradorGestores.recuar().iterador();
            }
            return iteradorDeContactos.recuar();
        }
    }
}
