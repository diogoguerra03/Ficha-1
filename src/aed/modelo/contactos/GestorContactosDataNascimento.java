package aed.modelo.contactos;

import aed.colecoes.iteraveis.IteradorIteravelDuplo;
import aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseOrdemMaximaOrdenada;
import aed.modelo.contactos.comparadores.ComparacaoContactoPorPrimeiroNomeAscSegPorDataNascimentoDesc;

import java.util.Objects;

public class GestorContactosDataNascimento {
    private Data dataNascimento;
    private ListaDuplaCircularBaseOrdemMaximaOrdenada<Contacto> contactos;

    public GestorContactosDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
        contactos = new ListaDuplaCircularBaseOrdemMaximaOrdenada<>
                (ComparacaoContactoPorPrimeiroNomeAscSegPorDataNascimentoDesc.CRITERIO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestorContactosDataNascimento that = (GestorContactosDataNascimento) o;
        return Objects.equals(dataNascimento, that.dataNascimento) && Objects.equals(contactos, that.contactos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataNascimento, contactos);
    }

    public int compareTo(GestorContactosDataNascimento o2) {
        return dataNascimento.compareTo(o2.dataNascimento);
    }

    public void inserir(Contacto contacto) {
        contactos.inserir(contacto);
    }

    public Contacto remover(Contacto contacto){
        return contactos.remover(contacto);
    }

    public boolean isVazia() {
        return contactos.isVazia();
    }

    public IteradorIteravelDuplo<Contacto> iterador() {
        return contactos.iterador();
    }
}
