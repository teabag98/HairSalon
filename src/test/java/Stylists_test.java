import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

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
    public void equals_returnsTrueIfNamesAreTheSame(){
Stylists firstStylist = new Stylists("eric","supertalented");
        Stylists secondStylist = new Stylists("eric","supertalented");
        assertTrue(firstStylist.equals(secondStylist));

    }

    @Test
    public void save_addsNewStylistToTheDatabase(){
        Stylists myStylist = new Stylists("mary","always smiling");
        myStylist.save();
        assertTrue(Stylists.all().get(0).equals(myStylist));
     }

     @Test
    public void all_returnsAllInstancesOfStylists(){
        Stylists firstStylist = new Stylists("Tatu","cool");
        firstStylist.save();
        Stylists secondStylist = new Stylists("vilan","fastest worker");
        secondStylist.save();
        assertEquals(true,Stylists.all().get(0).equals(firstStylist));
    assertEquals(true, Stylists.all().get(1).equals(secondStylist));
     }

    @Test
    public void getName_categoryInstantiatesWithName_Home() {
        Stylists myStylist = new Stylists("ric", "best-barber");
        assertEquals("ric", myStylist.getName());
    }

    @Test
    public void getId_assignsIdToObject() {
        Stylists myStylist = new Stylists("kiki","awesome girl");
        myStylist.save();

    }

    @Test
    public void getClients_retrievesAllTasksFromDatabase_TasksList(){
        Stylists myStylist = new Stylists("Ho","crazy");
        myStylist.save();
        Clients firstClient = new Clients("Mow", myStylist.getId());
        firstClient.save();
        Clients secondClient = new Clients("Mow", myStylist.getId());
        secondClient.save();

        Clients[] client = new Clients[] {firstClient,secondClient};

        assertTrue(myStylist.getClients().containsAll(Arrays.asList(client)));

    }

    @Test
    public void find_returnsCategoryWithSameId_secondCategory() {
        Stylists firstStylist = new Stylists("cuty","blak eyes");
        firstStylist.save();
        Stylists secondStylist = new Stylists("gorz", "tall");
        secondStylist.save();
        assertEquals(Stylists.find(secondStylist.getId()),secondStylist);

    }
}