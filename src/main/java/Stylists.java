import java.util.List;

import org.sql2o.*;

public class Stylists {
    private String name;
    private String description;
    private int id;


    @Override
    public boolean equals(Object otherStylists) {
        if (!(otherStylists instanceof Stylists)) {
            return false;
        } else {
            Stylists newStylist = (Stylists) otherStylists;
            return this.getName().equals(newStylist.getName()) &&
                    this.getDescription().equals(newStylist.getDescription()) &&
                    this.getId() == newStylist.getId();
        }
    }

    public Stylists(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO stylists (name,description)VALUES(:name,:description)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();

        }

    }

    public static List<Stylists> all() {
        String sql = "SELECT id,name,description FROM stylists";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Stylists.class);
        }
    }

    public List<Clients> getClients() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM stylists where stylistId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Clients.class);
        }


    }

    public static Stylists find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM stylists where id =:id";
            Stylists stylist= con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Stylists.class);
            return stylist;

        }
    }
}
