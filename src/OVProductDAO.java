import java.sql.SQLException;

public interface OVProductDAO {
    public Boolean save(OVChipkaart ov,Product product)throws SQLException;


    public  Boolean delete(OVChipkaart ov, Product product) throws SQLException;
}
