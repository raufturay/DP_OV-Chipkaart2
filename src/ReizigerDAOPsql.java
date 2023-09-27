import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ReizigerDAOPsql implements  ReizigerDAO {

    private Connection conn;
    private AdresDAO adresdao;


   public  ReizigerDAOPsql(Connection connection, AdresDAO adao){
    conn = connection;
    adresdao = adao;
   }
    @Override
    public Boolean save(Reiziger reiziger)throws SQLException {
        String insertQuery =
                "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) VALUES (?, ?, ?, ?, ?)";

        // Create a PreparedStatement to safely insert data
        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

        // Set values for the placeholders
        preparedStatement.setInt(1, reiziger.getId()); // Assuming getId returns the ID
        preparedStatement.setString(2, reiziger.getVoorletters());
        preparedStatement.setString(3, reiziger.getTussenvoegsel());
        preparedStatement.setString(4, reiziger.getAchternaam());
        preparedStatement.setDate(5, new java.sql.Date(reiziger.getGeboortedatum().getTime()));

        int rowsAffected = preparedStatement.executeUpdate();


        // Check if the insertion was successful
        adresdao.save(reiziger.getAdres(), reiziger.getId());
      return rowsAffected > 0;


    }


    @Override
    public Boolean update(Reiziger reiziger) throws SQLException {
        String updateQuery =
                "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?";

        // Create a PreparedStatement to safely update data
        PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);

        // Set values for the placeholders
        preparedStatement.setString(1, reiziger.getVoorletters());
        preparedStatement.setString(2, reiziger.getTussenvoegsel());
        preparedStatement.setString(3, reiziger.getAchternaam());
        preparedStatement.setDate(4, new java.sql.Date(reiziger.getGeboortedatum().getTime()));
        preparedStatement.setInt(5, reiziger.getId());


        // Execute the UPDATE query
        int rowsAffected = preparedStatement.executeUpdate();
        adresdao.update(reiziger.getAdres());

        // Check if the update was successful
        return rowsAffected > 0;
    }

    @Override
    public Boolean delete(Reiziger reiziger) throws SQLException {
        adresdao.delete(reiziger.getAdres());
        String deletequery =
                "DELETE FROM reiziger WHERE reiziger_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(deletequery);

        preparedStatement.setInt(1, reiziger.getId());
        int rowsAffected = preparedStatement.executeUpdate();
        ;
        return rowsAffected > 0;

    }

    @Override
    public Reiziger findbyid(int id) throws SQLException {
        String selectQuery = "SELECT * FROM reiziger WHERE reiziger_id = ?";

        // Create a PreparedStatement to fetch data by id
        PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);

        // Set the value for the placeholder
        preparedStatement.setInt(1, id);

        // Execute the SELECT query
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Create a Reiziger object from the retrieved data
            Reiziger reiziger = new Reiziger(0, null, null, null,null,null);
            reiziger.setId(resultSet.getInt("reiziger_id"));
            reiziger.setVoorletters(resultSet.getString("voorletters"));
            reiziger.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
            reiziger.setAchternaam(resultSet.getString("achternaam"));
            reiziger.setGeboortedatum(resultSet.getDate("geboortedatum"));
            reiziger.setAdres(adresdao.findbyReiziger(reiziger));

            return reiziger;
        } else {
            return null; // No data found with the specified id
        }
    }

    @Override
    public List<Reiziger> findbyGBdatum(String datum) throws SQLException {
        String selectQuery = "SELECT * FROM Reiziger WHERE geboortedatum = ?";

        // Create a PreparedStatement to fetch data by geboortedatum
        PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);

        // Set the value for the placeholder
        preparedStatement.setDate(1, java.sql.Date.valueOf(datum));

        // Execute the SELECT query
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Reiziger> reizigers = new ArrayList<>();

        while (resultSet.next()) {
            // Create a Reiziger object for each row of retrieved data
            Reiziger reiziger = new Reiziger(0, null, null, null,null,null);

            reiziger.setId(resultSet.getInt("id"));
            reiziger.setVoorletters(resultSet.getString("voorletters"));
            reiziger.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
            reiziger.setAchternaam(resultSet.getString("achternaam"));
            reiziger.setGeboortedatum(resultSet.getDate("geboortedatum"));
            reiziger.setAdres(adresdao.findbyReiziger(reiziger));
            reizigers.add(reiziger);
        }

        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        String selectQuery = "SELECT * FROM Reiziger";

        // Create a PreparedStatement to fetch all data
        PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);

        // Execute the SELECT query
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Reiziger> reizigers = new ArrayList<>();

        while (resultSet.next()) {
            // Create a Reiziger object for each row of retrieved data
            Reiziger reiziger = new Reiziger(0, null, null, null,null,null);

            reiziger.setId(resultSet.getInt("reiziger_id"));
            reiziger.setVoorletters(resultSet.getString("voorletters"));
            reiziger.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
            reiziger.setAchternaam(resultSet.getString("achternaam"));
            reiziger.setGeboortedatum(resultSet.getDate("geboortedatum"));
            reiziger.setAdres(adresdao.findbyReiziger(reiziger));

            reizigers.add(reiziger);
        }

        return reizigers;
    }
}