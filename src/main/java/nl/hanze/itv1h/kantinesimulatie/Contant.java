package nl.hanze.itv1h.kantinesimulatie;

import nl.hanze.itv1h.kantinesimulatie.exceptions.TeWeinigGeldException;

public class Contant extends Betaalwijze {

    /**
     *
     * @param tebetalen
     * @throws TeWeinigGeldException
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        if(tebetalen > saldo)
            throw new TeWeinigGeldException();

        saldo -= tebetalen;
    }
}