import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {

    private Connection conn;



    public ProductDAOPsql(Connection cn){
        conn = cn;

    }

    @Override
    public Boolean save(Product product) throws SQLException {
        String insertQuery =
                "INSERT INTO product (product_nummer, naam, beschrijving, prijs) VALUES (?, ?, ?, ?)";

        // Create a PreparedStatement to safely insert data
        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

        // Set values for the placeholders
        preparedStatement.setInt(1, product.getProductnummer()); // Assuming getId returns the ID
        preparedStatement.setString(2, product.getNaam());
        preparedStatement.setString(3, product.getBeschrijving());
        preparedStatement.setDouble(4, product.getPrijs());



        int rowsAffected = preparedStatement.executeUpdate();
        // Check if the insertion was successful
        return rowsAffected > 0;

    }

    @Override
    public Boolean update(Product product) throws SQLException {

        String updateQuery =
                "UPDATE product SET  naam = ?, beschrijving = ?, prijs = ? WHERE product_nummer = ?";

        // Create a PreparedStatement to safely update data
        PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);

        // Set values for the placeholders

        preparedStatement.setString(1, product.getNaam());
        preparedStatement.setString(2, product.getBeschrijving());
        preparedStatement.setDouble(3, product.getPrijs());
        preparedStatement.setInt(4, product.getProductnummer());


        // Execute the UPDATE query
        int rowsAffected = preparedStatement.executeUpdate();

        // Check if the update was successful
        return rowsAffected > 0;
    }

    @Override
    public Boolean delete(Product product) throws SQLException {
        String deletequery =
                "DELETE FROM product WHERE product_nummer = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(deletequery);
        preparedStatement.setInt(1,product.getProductnummer());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    }


    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ov) throws SQLException {
        String selectQuery = "SELECT * FROM product JOIN ov_chipkaart_product ON product.product_nummer = ov_chipkaart_product.product_nummer JOIN ov_chipkaart ON ov_chipkaart.kaart_nummer = ov_chipkaart_product.kaart_nummer WHERE ov_chipkaart.kaart_nummer = ?";

        // Create a PreparedStatement to fetch data by id
        PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);

        // Set the value for the placeholder
        preparedStatement.setInt(1, ov.getKaartnummer());

        // Execute the SELECT query
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> products = new ArrayList<>();

        while (resultSet.next()) {
            // Create a Reiziger object for each row of retrieved data
            Product product = new Product(0, null, null, 0.0);

            product.setProductnummer(resultSet.getInt("product_nummer"));
            product.setNaam(resultSet.getString("naam"));
            product.setBeschrijving(resultSet.getString("beschrijving"));
            product.setPrijs(resultSet.getDouble("prijs"));
            products.add(product);
        }

        return products;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String selectQuery = "SELECT * FROM product";

        // Create a PreparedStatement to fetch all data
        PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);

        // Execute the SELECT query
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Product> producten = new ArrayList<>();

        while (resultSet.next()) {
            Product product = new Product(0, null, null, 0.0);

            product.setProductnummer(resultSet.getInt("product_nummer"));
            product.setNaam(resultSet.getString("naam"));
            product.setBeschrijving(resultSet.getString("beschrijving"));
            product.setPrijs(resultSet.getDouble("prijs"));


            producten.add(product);
        }

        return producten;
    }

}
