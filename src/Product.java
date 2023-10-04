import java.util.ArrayList;
import java.util.List;

public class Product {

    private int productnummer;

    private String naam;

    private String beschrijving;

    private double prijs;

    private List<OVChipkaart> kaarten = new ArrayList<OVChipkaart>();

    public Product(int pn, String nm, String bes, double pr){
        productnummer = pn;
        naam = nm;
        beschrijving = bes;
        prijs = pr;
    }

    public List<OVChipkaart> getKaarten() {
        return kaarten;
    }

    public int getProductnummer() {
        return productnummer;
    }
    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setProductnummer(int productnummer) {
        this.productnummer = productnummer;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productnummer=" + productnummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                ", kaarten=" + kaarten +
                '}';
    }
}
