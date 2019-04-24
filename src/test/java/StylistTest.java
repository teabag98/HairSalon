import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

    @Before
    public void setUp(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    }

    @After
    public void tearDown(){
        try(Connection con =DB.sql2o.open()) {
            String sql = "DELETE FROM clients *;";
            con.createQuery(sql).executeUpdate();
        }

    }


    @Test
    public void stylist_instantiatesCorrectly_true() {
        Stylist testStylist = new Stylist("ricoo","smart");
        assertEquals(true, testStylist instanceof Stylist);
    }

    @Test
    public void getName_stylistInstantiatesWithName_Aru() {
        Stylist testStylist = new Stylist("ereic","best");
        assertEquals("rich", testStylist.getName());
    }

    @Test
    public void all_returnsAllInstancesOfStylist_true() {
        Stylist firstStylist = new Stylist("tomas","best");
        firstStylist.save();
        Stylist secondStylist = new Stylist("ooloo","experienced");
        secondStylist.save();
        assertEquals(true, Stylist.all().contains(firstStylist));
        assertEquals(true, Stylist.all().contains(secondStylist));
    }

    @Test
    public void getClients_retrievesALlClientsFromDatabase_clientsList() {
        Stylist newStylist = new Stylist("nasire","best");
        newStylist.save();
        Client firstClient = new Client("vvv", "male", 7977777, newStylist.getId());
        firstClient.save();
        Client secondClient = new Client("audrey", "female", 78989, newStylist.getId());
        secondClient.save();
        Client[] clients = new Client[] { firstClient, secondClient };
        assertTrue(newStylist.getClients().containsAll(Arrays.asList(clients)));
    }


    @Test
    public void getId_stylistInstantiatesWithId_1() {
        Stylist testStylist = new Stylist("rew","talented");
        testStylist.save();
        assertTrue(testStylist.getId() > 0);
    }

    @Test
    public void find_returnsStylistWihSameId_secondStylist() {
        Stylist firstStylist = new Stylist("rff","talented");
        firstStylist.save();
        Stylist secondStylist = new Stylist("gut","talented");
        secondStylist.save();
        assertEquals(secondStylist, Stylist.find(secondStylist.getId()));
    }

    @Test
    public void getClients_returnsEmptyList_ArrayList() {
        Stylist testStylist = new Stylist("rivki","talented");
        assertEquals(0, testStylist.getClients().size());
    }

    @Test
    public void equals_returnsTrueIfNamesAretheSame() {
        Stylist firstStylist = new Stylist("dean", "neat");
        Stylist secondStylist = new Stylist("amy","classy");
        assertTrue(firstStylist.equals(secondStylist));
    }

    @Test
    public void save_savesIntoDatabase_true() {
        Stylist newStylist = new Stylist("maggy","awesome");
        newStylist.save();
        assertTrue(Stylist.all().get(0).equals(newStylist));
    }

    @Test
    public void save_assignsIdToObject() {
        Stylist newStylist = new Stylist("doug", "tall");
        newStylist.save();
        Stylist savedStylist = Stylist.all().get(0);
        assertEquals(newStylist.getId(), savedStylist.getId());
    }

}