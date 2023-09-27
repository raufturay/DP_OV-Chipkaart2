import java.sql.SQLException;
import java.util.List;

public interface AdresDAO {


        public Boolean save(Adres adres, int reiziger_id)throws SQLException;

        public  Boolean update(Adres adres) throws SQLException;

        public  Boolean delete(Adres adres) throws SQLException;

        public Adres findbyReiziger(Reiziger reiziger)throws SQLException;
        public List<Adres> findAll()throws SQLException;
    }


