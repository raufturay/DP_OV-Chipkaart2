import java.util.Date;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Adres adres;

    public Reiziger(int code,String vl,String tv,String an,Date gd,Adres adr){
        id = code;
        voorletters = vl;
        tussenvoegsel = tv;
        achternaam = an;
        geboortedatum = gd;
        adres = adr;
    }

    public int getId() {
        return id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getNaam(){
        return voorletters + " " + tussenvoegsel + " " + achternaam;
    }
    public Adres getAdres(){
        return adres;
    }
    public String toString(){
        return voorletters + " " + tussenvoegsel +" "+ achternaam +" " + geboortedatum +" "+ adres.toString() ;
    }

}
