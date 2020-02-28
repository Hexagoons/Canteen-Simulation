package nl.hanze.itv1h.kantinesimulatie;

/**
 * Datum klasse
 *
 * @since 1.0
 */
public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	/**
	 * Construeert de datum en initialiseert velden volgens doorgegeven parameters
	 *
	 * @param dag
	 * @param maand
	 * @param jaar
	 */
	public Datum(int dag, int maand, int jaar) {
		this();

		if(bestaatDatum(dag, maand, jaar)) {
			this.dag = dag;
			this.maand = maand;
			this.jaar = jaar;
		}
	}

	/**
	 * Construeert datum met standaardwaarden
	 */
	public Datum() {
		this.dag = this.maand = this.jaar = 0;
	}

	/**
	 *
	 * @param maand
	 * @param jaar
	 * @return
	 */
	public static int monthToMaxDays(int maand, int jaar) {
		if(maand == 1 || maand == 3 || maand == 5 || maand == 7 || maand == 8 || maand == 10 || maand == 12)
			return 31;

		if(maand == 2)
			return ((jaar % 400 == 0) || ((jaar % 4 == 0) && (jaar % 100 != 0))) ? 29 : 28;

		return 30;
	}

	/**
	 * Controleerd of gegeven datum een geldige datum is.
	 *
	 * @param dag
	 * @param maand
	 * @param jaar
     *
	 * @return
	 */
	public boolean bestaatDatum(int dag, int maand, int jaar) {
		boolean isValid = (dag >= 1) && (1 <= maand && maand <= 12) && (1900 <= jaar && jaar <= 2100);

		if(isValid)
			isValid = (dag <= Datum.monthToMaxDays(maand, jaar));

		return isValid;
	}
	
	/**
	 * Returns String weergave van datum
     *
	 * @return Datum als String in leesbare vorm
	 */
	public String getDateAsString() {
		return (bestaatDatum(dag, maand, jaar)) ? "" + dag + "-" + maand + "-" + jaar : "Ongeldige datum";
	}

	private void setDate(int dag, int maand, int jaar) {

		if(bestaatDatum(dag, maand, jaar)) {
			this.dag = dag;
			this.maand = maand;
			this.jaar = jaar;
		} else {
			this.dag = this.maand = this.jaar = 0;
		}

	}

    public int getDag() {
        return dag;
    }

    public void setDag(int dag) {
		setDate(dag, maand, jaar);
    }

    public int getMaand() {
        return maand;
    }

    public void setMaand(int maand) {
		setDate(dag, maand, jaar);
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
		setDate(dag, maand, jaar);
    }
}
