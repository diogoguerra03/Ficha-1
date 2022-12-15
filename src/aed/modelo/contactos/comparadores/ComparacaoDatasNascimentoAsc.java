package aed.modelo.contactos.comparadores;

import aed.Comparacao;
import aed.modelo.contactos.Data;
import aed.modelo.contactos.GestorContactosDataNascimento;
import aed.modelo.contactos.GestorContactosOtimizado;

public enum ComparacaoDatasNascimentoAsc implements Comparacao<Data> {
    CRITERIO;

    @Override
    public int comparar(Data o1, Data o2) {
        return o1.compareTo(o2);
    }

}
