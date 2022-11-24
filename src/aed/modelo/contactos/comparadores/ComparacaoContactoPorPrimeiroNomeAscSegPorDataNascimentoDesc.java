package aed.modelo.contactos.comparadores;

import aed.Comparacao;
import aed.modelo.contactos.Contacto;

public enum ComparacaoContactoPorPrimeiroNomeAscSegPorDataNascimentoDesc implements Comparacao<Contacto> {
    CRITERIO;

    @Override
    public int comparar(Contacto o1, Contacto o2) {
        int cp = o1.getPrimeiroNome().compareTo(o2.getPrimeiroNome());
        if (cp!=0){
            return cp;
        }

        return o1.getUltimoNome().compareTo(o2.getUltimoNome());
    }
}
