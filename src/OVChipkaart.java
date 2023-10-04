import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OVChipkaart {
    private int kaartnummer;
    private Date geldig_tot;

    private int klasse;

    private double saldo;

    private Reiziger reiziger;

    private List<Product> producten = new ArrayList<Product>();


    public OVChipkaart(int kn,Date gtot,int k,double s,Reiziger r){
        kaartnummer = kn;
        geldig_tot = gtot;
        klasse = k;
        saldo = s;
        reiziger = r;

    }

    public List<Product> getProducten() {
        return producten;
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

    public Reiziger getReiziger() {
        return reiziger;
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

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
        if (!reiziger.getKaarten().contains(this)) {
            reiziger.getKaarten().add(this);
        }
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
