package nl.hanze.itv1h.kantinesimulatie;

public class Student extends Persoon {
    private int studentNummer;
    private String studierichting;

   public Student(String bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int studentNummer, String studierichting, Betaalwijze betaalwijze) {
       super(bsn, voornaam, achternaam, geboortedatum, geslacht, betaalwijze);

       this.studentNummer = studentNummer;
       this.studierichting = studierichting;
   }

    public Student(int studentNummer, String studierichting) {
        super();

        this.studentNummer = studentNummer;
        this.studierichting = studierichting;
    }

    public int getStudentNummer() {
        return studentNummer;
    }

    public void setStudentNummer(int studentNummer) {
        this.studentNummer = studentNummer;
    }

    public String getStudierichting() {
        return studierichting;
    }

    public void setStudierichting(String studierichting) {
        this.studierichting = studierichting;
    }

    @Override
    public String toString() {
        return "Student";
    }
}
