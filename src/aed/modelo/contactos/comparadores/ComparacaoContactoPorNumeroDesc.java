package aed.modelo.contactos.comparadores;

import aed.modelo.contactos.Contacto;
import aed.Comparacao;

public enum ComparacaoContactoPorNumeroDesc implements Comparacao<Contacto> {
    CRITERIO;

    @Override
    public int comparar(Contacto o1, Contacto o2) {
        return -Long.compare(o2.getNumeroTelefone(), o2.getNumeroTelefone());
    }
}
