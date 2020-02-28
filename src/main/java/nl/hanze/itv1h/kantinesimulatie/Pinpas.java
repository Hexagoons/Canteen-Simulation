package nl.hanze.itv1h.kantinesimulatie;

import nl.hanze.itv1h.kantinesimulatie.exceptions.TeWeinigGeldException;

public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     *
     * @param kredietlimiet
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet = kredietlimiet;
    }

    /**
     *
     * @param tebetalen
     * @throws TeWeinigGeldException
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        if(tebetalen > saldo + kredietlimiet)
            throw new TeWeinigGeldException();

        saldo -= tebetalen;
    }
}
