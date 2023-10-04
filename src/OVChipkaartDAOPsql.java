import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {

    private Connection connection;



    public OVChipkaartDAOPsql(Connection conn){
        connection = conn;

    }

    @Override
    public List<OVChipkaart> findbyreiziger(Reiziger reiziger) throws SQLException {
        String findquery =
                "SELECT * FROM ov_chipkaart WHERE reiziger_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(findquery);
        preparedStatement.setInt(1, reiziger.getId());

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<OVChipkaart> kaarten = new ArrayList<>();

        while (resultSet.next()) {
            // Create a Reiziger object for each row of retrieved data
            OVChipkaart ov = new OVChipkaart(0, null, 0, 0.0, 0);

            ov.setKaartnummer(resultSet.getInt("kaart_nummer"));
            ov.setGeldig_tot(resultSet.getDate("geldig_tot"));
            ov.setKlasse(resultSet.getInt("klasse"));
            ov.setSaldo(resultSet.getDouble("saldo"));
            ov.setReizigerid(reiziger.getId());
            kaarten.add(ov);
            return kaarten;
        }
        return null;
    }

    public Boolean save(OVChipkaart ov) throws SQLException {
        String insertQuery =
                "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo,reiziger_id) VALUES (?, ?, ?, ?, ?)";

        // Create a PreparedStatement to safely insert data
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        // Set values for the placeholders
        preparedStatement.setInt(1, ov.getKaartnummer()); // Assuming getId returns the ID
        preparedStatement.setDate(2, (Date) ov.getGeldig_tot());
        preparedStatement.setInt(3, ov.getKlasse());
        preparedStatement.setDouble(4, ov.getSaldo());
        preparedStatement.setInt(5, ov.getReizigerID());


        int rowsAffected = preparedStatement.executeUpdate();
        // Check if the insertion was successful
        return rowsAffected > 0;


    }

    public Boolean update(OVChipkaart ov) throws SQLException {
        String updateQuery =
                "UPDATE ov_chipkaart SET  geldig_tot = ?, klasse = ?, saldo = ? WHERE kaart_nummer = ?";

        // Create a PreparedStatement to safely update data
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

        // Set values for the placeholders

        preparedStatement.setDate(1, (Date) ov.getGeldig_tot());
        preparedStatement.setInt(2, ov.getKlasse());
        preparedStatement.setDouble(3, ov.getSaldo());
        preparedStatement.setInt(4, ov.getKaartnummer());


        // Execute the UPDATE query
        int rowsAffected = preparedStatement.executeUpdate();

        // Check if the update was successful
        return rowsAffected > 0;
    }

    @Override
    public Boolean delete(OVChipkaart ov) throws SQLException {
        String deletequery =
                "DELETE FROM ov_chipkaart WHERE kaart_nummer = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deletequery);
        preparedStatement.setInt(1,ov.getKaartnummer());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;

    }
}
