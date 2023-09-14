public class Adres {

    private int adres_id;

    private String postcode;

    private String huisnummer;

    private String straat;
    private String woonplaats;



    public Adres(int id, String pc, String hn, String st ,String wp){
        adres_id = id;
        postcode = pc;
        huisnummer = hn;
        straat = st;
        woonplaats = wp;
    }

    public String getStraat() {
        return straat;
    }

    public String getPostcode() {
        return postcode;
    }

    public int getAdres_id() {
        return adres_id;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setAdres_id(int adres_id) {
        this.adres_id = adres_id;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
    public String toString(){
        return   straat + " " + huisnummer + " " + postcode + " " + woonplaats;
    }
}
