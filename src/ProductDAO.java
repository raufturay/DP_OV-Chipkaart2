import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    public Boolean save(Product product)throws SQLException;

    public  Boolean update(Product product) throws SQLException;

    public  Boolean delete(Product product) throws SQLException;

    public List<Product> findByOVChipkaart(OVChipkaart ov) throws SQLException;
    public List<Product> findAll() throws SQLException;
}
