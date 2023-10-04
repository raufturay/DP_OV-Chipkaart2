import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OVChipkaart {
    private int kaartnummer;
    private Date geldig_tot;

    private int klasse;

    private double saldo;

    private int  reizigerid;

    private List<Integer> productenids = new ArrayList<Integer>();


    public OVChipkaart(int kn, Date gtot, int k, double s, int r){
        kaartnummer = kn;
        geldig_tot = gtot;
        klasse = k;
        saldo = s;
        reizigerid = r;

    }

    public List<Integer> getProductenids() {
        return productenids;
    }

    public double getSaldo() {
        return saldo;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public int getKaartnummer() {
        return kaartnummer;
    }

    public int getKlasse() {
        return klasse;
    }

    public int getReizigerID() {
        return reizigerid;
    }

    public void setKaartnummer(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public void setReizigerid(int reizigerid) {
        this.reizigerid = reizigerid;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
