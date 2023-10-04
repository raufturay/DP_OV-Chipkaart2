import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OVProductDAOPsql implements OVProductDAO{
    private Connection connection;

    private ProductDAO productDAO;

    public OVProductDAOPsql(Connection conn, ProductDAO pdao){
        connection = conn;
        productDAO = pdao;

    }
    @Override
    public Boolean save(OVChipkaart ov, Product product) throws SQLException {
        productDAO.save(product);
        String insertQuery =
                "INSERT INTO ov_chipkaart_product (kaart_nummer, product_nummer) VALUES (?, ?)";

        // Create a PreparedStatement to safely insert data
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        // Set values for the placeholders
        preparedStatement.setInt(1, ov.getKaartnummer()); // Assuming getId returns the ID
        preparedStatement.setInt(2, product.getProductnummer());



        int rowsAffected = preparedStatement.executeUpdate();
        // Check if the insertion was successful
        return rowsAffected > 0;
    }



    @Override
    public Boolean delete(OVChipkaart ov, Product product) throws SQLException {
        productDAO.delete(product);
        String deletequery =
                "DELETE FROM ov_chipkaart_product WHERE product_nummer = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deletequery);
        preparedStatement.setInt(1,product.getProductnummer());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    }
}
