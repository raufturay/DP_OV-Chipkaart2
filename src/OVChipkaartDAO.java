import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {


    public List<OVChipkaart> findbyreiziger(Reiziger reiziger) throws SQLException;
    public Boolean save(OVChipkaart ov)throws SQLException;

    public  Boolean update(OVChipkaart ov) throws SQLException;

    public  Boolean delete(OVChipkaart ov) throws SQLException;
}
