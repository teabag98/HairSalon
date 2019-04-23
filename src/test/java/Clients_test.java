import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;



public class Clients_test {
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
    public void save_returnsTrueIfDescriptionsAreSame() {
        Clients myClient = new Clients("richie",1);
        myClient.save();
        assertTrue(Clients.all().get(0).equals(myClient));
    }

    @Test
    public void all_returnsAllInstancesOfClient_true(){
        Clients firstClient = new Clients("Me",1);
        firstClient.save();
        Clients secondClient = new Clients("rice",2);
        secondClient.save();
        assertEquals(true, Clients.all().get(0).equals(firstClient));
        assertEquals(true, Clients.all().get(1).equals(secondClient));
    }

    @Test
    public void save_assignsIdToObject(){
        Clients myclient = new Clients("de gea",1);
        myclient.save();
        Clients savedTask = Clients.all().get(0);
        assertEquals(myclient.getId(),savedTask.getId());
    }

    @Test
    public void getId_clientsInstantiateWithID(){
        Clients myClient = new Clients("dan",1);
        myClient.save();

        assertTrue(myClient.getId()>0);
    }

    @Test
    public void find_returnsClientWithSameId_secondClient(){
        Clients firstClient = new Clients("tee man",1);
        firstClient.save();
        Clients secondClient = new Clients("tea",1);
        secondClient.save();
        assertEquals(Clients.find(secondClient.getId()),secondClient);
    }

//    @Test
//    public void save_savesStylistIdIntoDB_true(){
//        Stylists myStylist= new Stylists("abys","hardworking");
//        myStylist.save();
//
//        Clients myClient = new Clients("richie", myStylist.getId());
//        myClient.save();
//        Stylists savedClient = Stylists.find(myStylist.getId());
//        assertEquals(savedClient.getStylistId(), myStylist.getId());
//    }




}

