import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Stylists_test {
    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test");
    }


    @After
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String deleteClientQuery = "DELETE FROM clients*";
            String deleteStylistsQuery = "DELETE FROM stylists*";
            con.createQuery(deleteClientQuery).executeUpdate();
            con.createQuery(deleteStylistsQuery).executeUpdate();

        }
    }

    @Test
    public void equals_rerurnsTrueIfNamesAreTheSame(){
Stylists firstStylist = new Stylists("eric","supertalented");
        Stylists secondStylist = new Stylists("eric","supertalented");
        assertTrue(firstStylist.equals(secondStylist));

    }
}