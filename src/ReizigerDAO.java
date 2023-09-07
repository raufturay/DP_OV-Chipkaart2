import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    public Boolean save(Reiziger reiziger)throws SQLException;

    public  Boolean update(Reiziger reiziger) throws SQLException;

    public  Boolean delete(Reiziger reiziger) throws SQLException;

    public Reiziger Findbyid(int id)throws SQLException;
    public List<Reiziger> FindbyGBdatum(String datum)throws SQLException;
    public List<Reiziger> findAll()throws SQLException;
}
