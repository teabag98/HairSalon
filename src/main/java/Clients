
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;



public class Client {

    private String name;
    private String gender;
    private int cellphone;
    private int id;
    private int stylistId;

    public Client(String name, String gender, int cellphone, int stylistId) {

        this.name = name;
        this.gender = gender;
        this.cellphone = cellphone;
        this.stylistId = stylistId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getCellphone() {
        return cellphone;
    }

    public int getId() {
        return id;
    }

    public int getStylistId() {
        return stylistId;
    }

    public static List<Client> all() {
        String sql = "SELECT id, name, gender, cellphone, stylistId FROM clients";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Client.class);
        }
    }

    public static Client find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where id=:id";
            Client client = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
            return client;
        }
    }

    @Override
    public boolean equals(Object otherClient) {
        if (!(otherClient instanceof Client)) {
            return false;
        } else {
            Client newClient = (Client) otherClient;
            return this.getName().equals(newClient.getName()) &&
                    this.getGender().equals(newClient.getGender()) &&
                    this.getCellphone() == newClient.getCellphone() &&
                    this.getId() == newClient.getId() &&
                    this.getStylistId() == newClient.getStylistId();
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO clients (name, gender, cellphone, stylistId) VALUES (:name, :gender, :cellphone, :stylistId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("gender", this.gender)
                    .addParameter("cellphone", this.cellphone)
                    .addParameter("stylistId", this.stylistId)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void update(String name, String gender, int cellphone) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE clients SET name = :name, gender = :gender, cellphone = :cellphone WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("gender", gender)
                    .addParameter("cellphone", cellphone)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM clients WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
