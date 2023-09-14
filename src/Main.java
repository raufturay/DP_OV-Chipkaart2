import java.sql.*;
import java.util.List;

public class Main {
    private Connection conn;

    public Main() throws SQLException {
    }

    public static Connection getConnection() throws SQLException {


        String url = "jdbc:postgresql://localhost/OV-Chipkaart";
        String username = "postgres";
        String password = "rauf";
        Connection conn = DriverManager.getConnection(url, username, password);

        return conn;
    }

    public static void closeConnection() throws SQLException {
        getConnection().close();
    }

    /**
     * P2. Reiziger DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */


    public static void main(String[] args) throws SQLException {


    AdresDAOPsql adao = new AdresDAOPsql(getConnection());

    ReizigerDAOPsql rdao = new ReizigerDAOPsql(getConnection(),adao);


    testReizigerDAO(rdao);
    testADAO(adao,rdao);

}
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

// Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            Adres adres = r.getAdres();
            String adresInfo = adres != null ? adres.toString() : "No address available";
            System.out.println(r.getNaam() + " | Adres: " + adresInfo);
        }
        System.out.println();

// Maak een nieuwe reiziger aan met adres en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Adres adresSietske = new Adres(77, "1234AB", "1", "Straatnaam", "Woonplaats");
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum), adresSietske);

        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

// Update gegevens van de zojuist aangemaakte reiziger en adres
        sietske.setVoorletters("S.");
        sietske.setTussenvoegsel("de");
        sietske.setAchternaam("Boers-Van Hoorn");
        Adres updatedAdresSietske = new Adres(1, "5678CD", "2", "Nieuwestraat", "NieuweWoonplaats");
        sietske.setAdres(updatedAdresSietske);

        System.out.print("[Test] Eerst: " + reizigers.get(reizigers.size() - 1));
        rdao.update(sietske);
        reizigers = rdao.findAll();
        System.out.println(", na ReizigerDAO.update(): " + reizigers.get(reizigers.size() - 1) + "\n");

// Verwijderen van de zojuist aangemaakte reiziger
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.delete() ");
        rdao.delete(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

    }
    private static void testADAO(AdresDAOPsql adao, ReizigerDAOPsql rdao) throws SQLException {
        System.out.println("\n---------- Test AdresDAO -------------");

        // Create a Reiziger for testing
        String gbdatum = "1981-03-14";
        Adres adres = new Adres(78, "1234AB", "1", "Straatnaam", "Woonplaats");
        Reiziger reiziger = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum), adres);

        // Save the Reiziger with an associated Adres
        rdao.save(reiziger);

        // Test FindbyReiziger
        Adres foundAdres = adao.FindbyReiziger(reiziger);

        if (foundAdres != null) {
            System.out.println("Adres gevonden voor reiziger " + reiziger.getNaam() + ": " + foundAdres.toString());
        } else {
            System.out.println("Adres niet gevonden voor reiziger " + reiziger.getNaam());
        }

        // Clean up by deleting the Reiziger
        rdao.delete(reiziger);
    }

    }