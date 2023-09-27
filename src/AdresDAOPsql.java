import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {

   private Connection connection;


   public AdresDAOPsql(Connection conn){
       connection = conn;
   }
    @Override
    public Boolean save(Adres adres, int reiziger_id)throws SQLException {
        String insertQuery =
                "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats,reiziger_id) VALUES (?, ?, ?, ?, ?, ?)";

        // Create a PreparedStatement to safely insert data
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        // Set values for the placeholders
        preparedStatement.setInt(1, adres.getAdres_id()); // Assuming getId returns the ID
        preparedStatement.setString(2, adres.getPostcode());
        preparedStatement.setString(3, adres.getHuisnummer());
        preparedStatement.setString(4, adres.getStraat());
        preparedStatement.setString(5, adres.getWoonplaats());
        preparedStatement.setInt(6, reiziger_id);

        int rowsAffected = preparedStatement.executeUpdate();

        // Check if the insertion was successful
        return rowsAffected > 0;
    }

    @Override
    public Boolean update(Adres adres) throws SQLException {
        String updateQuery =
                "UPDATE adres SET postcode = ?, huisnummer = ?, straat = ?, woonplaats = ? WHERE adres_id = ?";

        // Create a PreparedStatement to safely update data
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

        // Set values for the placeholders
        preparedStatement.setString(1, adres.getPostcode());
        preparedStatement.setString(2, adres.getHuisnummer());
        preparedStatement.setString(3, adres.getStraat());
        preparedStatement.setString(4, adres.getWoonplaats());
        preparedStatement.setInt(5, adres.getAdres_id());


        // Execute the UPDATE query
        int rowsAffected = preparedStatement.executeUpdate();

        // Check if the update was successful
        return rowsAffected > 0;
    }

    @Override
    public Boolean delete(Adres adres) throws SQLException {
        String deletequery =
                "DELETE FROM adres WHERE adres_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deletequery);
        preparedStatement.setInt(1, adres.getAdres_id());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;

    }

    @Override
    public Adres findbyReiziger(Reiziger reiziger) throws SQLException {
        String findquery =
                "SELECT * FROM adres WHERE reiziger_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(findquery);
        preparedStatement.setInt(1, reiziger.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Create a Reiziger object for each row of retrieved data
            Adres adres = new Adres(0, null, null, null, null);

            adres.setAdres_id(resultSet.getInt("adres_id"));
            adres.setPostcode(resultSet.getString("postcode"));
            adres.setHuisnummer(resultSet.getString("huisnummer"));
            adres.setStraat(resultSet.getString("straat"));
            adres.setWoonplaats(resultSet.getString("woonplaats"));

            return adres;
        }else {
            return null;
        }
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        String selectQuery = "SELECT * FROM adres";

        // Create a PreparedStatement to fetch all data
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

        // Execute the SELECT query
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Adres> adressen = new ArrayList<>();

        while (resultSet.next()) {
            // Create a Reiziger object for each row of retrieved data
            Adres adres = new Adres(0, null, null, null,null);

            adres.setAdres_id(resultSet.getInt("adres_id"));
            adres.setPostcode(resultSet.getString("postcode"));
            adres.setHuisnummer(resultSet.getString("huisnummer"));
            adres.setStraat(resultSet.getString("straat"));
            adres.setWoonplaats(resultSet.getString("woonplaats"));

            adressen.add(adres);
        }

        return adressen;
    }

}
