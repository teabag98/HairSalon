import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;



public class ClientTest {
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
        public void Client_instantiatesCorrectly_true() {
            Client newClient = new Client("eric", "male", 792533748, 1);
            assertEquals(true, newClient instanceof Client);
        }
        @Test
        public void all_returnsAllInstancesOfClient_true() {
            Client firstClient = new Client("ericoo", "male",792533748, 1);
            firstClient.save();
            Client secondClient = new Client("abunuwas", "Male", 700518365, 2);
            secondClient.save();
            assertEquals(true, Client.all().get(0).equals(firstClient));
            assertEquals(true, Client.all().get(1).equals(secondClient));
        }

        @Test
        public void getId_clientInstantiatesWithId() {
            Client newClient = new Client("caroline", "Female", 792533748, 1);
            newClient.save();
            assertTrue(newClient.getId() > 0);
        }

        @Test
        public void find_returnsClientWithSameId_secondClient() {
            Client firstClient = new Client("Janet", "Female", 792533748, 1);
            firstClient.save();
            Client secondClient = new Client("Rene", "Male", 700518365, 2);
            secondClient.save();
            assertEquals(secondClient, Client.find(secondClient.getId()));
        }

        @Test
        public void equals_returnsTrueIfPropertiesAretheSame() {
            Client firstClient = new Client("Janet", "Female", 792533748, 1);
            Client secondClient = new Client("Janet", "Female", 792533748, 1);
            assertTrue(firstClient.equals(secondClient));
        }

        @Test
        public void save_returnsTrueIfPropertiesAretheSame() {
            Client newClient = new Client("Janet", "Female", 792533748, 1);
            newClient.save();
            assertTrue(Client.all().get(0).equals(newClient));
        }

        @Test
        public void save_assignsIdToObject() {
            Client newClient = new Client("Janet", "Female", 792533748, 1);
            newClient.save();
            Client savedClient = Client.all().get(0);
            assertEquals(newClient.getId(), savedClient.getId());
        }

        @Test
        public void save_savesStylistIdIntoDB_true() {
            Stylist newStylist = new Stylist("Sami","awesome");
            newStylist.save();
            Client newClient = new Client("Janet", "Female", 792533748, newStylist.getId());
            newClient.save();
            Client savedClient = Client.find(newClient.getId());
            assertEquals(savedClient.getStylistId(), newStylist.getId());
        }

        @Test
        public void update_updatesClientProperties_true() {
            Client newClient = new Client("Janet", "Female", 792533748, 1);
            newClient.save();
            newClient.update("Betty", "Female", 122069);
            assertEquals("Betty", Client.find(newClient.getId()).getName());
            assertEquals("Female", Client.find(newClient.getId()).getGender());
            assertEquals(122069, Client.find(newClient.getId()).getCellphone());

        }

        @Test
        public void delete_deletesClient_true() {
            Client newClient = new Client("Janet", "Female", 792533748, 1);
            newClient.save();
            int newClientId = newClient.getId();
            newClient.delete();
            assertEquals(null, Client.find(newClientId));
        }
    }