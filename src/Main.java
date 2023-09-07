import java.sql.*;
import java.util.List;

public class Main {
    private Connection connection;

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
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r.getNaam());
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Update  gegevens van de zojuist aangemaakte reiziger
        sietske.setVoorletters("S.");
        sietske.setTussenvoegsel("de");
        sietske.setAchternaam("Boers-Van Hoorn");
        System.out.print("[Test] Eerst: " + reizigers.get(reizigers.size() - 1));
        rdao.update(sietske);
        reizigers = rdao.findAll();
        System.out.println(", na ReizigerDAO.update(): " + reizigers.get(reizigers.size() - 1) + "\n");

        // Verwijderen van de zojuist aangemaakte reiziger
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.delete() ");
        rdao.delete(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
    }

    public static void main(String[] args) throws SQLException {

        ReizigerDAOPsql rdao = new ReizigerDAOPsql(getConnection());
    testReizigerDAO(rdao);
    }
}