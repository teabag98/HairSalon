
import java.util.List;

import org.sql2o.*;


public class Clients {
    private String name;
    private boolean removeClient;
    private int id;
    private int stylistId;

    public Clients(String name, int stylistId) {
        this.name = name;
        this.stylistId = stylistId;

    }

    public static List<Clients> all() {
        String sql = "SELECT id, name ,stylistId FROM clients";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Clients.class);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO clients (name) VALUES (:name,stylistId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("stylistId", this.stylistId)
                    .executeUpdate()
                    .getKey();
        }
    }


    public int getStylistId() {
        return stylistId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


}
