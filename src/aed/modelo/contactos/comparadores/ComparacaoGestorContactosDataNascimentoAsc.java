package aed.modelo.contactos.comparadores;

import aed.Comparacao;
import aed.modelo.contactos.GestorContactosDataNascimento;

public enum ComparacaoGestorContactosDataNascimentoAsc implements Comparacao<GestorContactosDataNascimento> {
    CRITERIO;


    @Override
    public int comparar(GestorContactosDataNascimento o1, GestorContactosDataNascimento o2) {
        return o1.compareTo(o2);
    }
}
