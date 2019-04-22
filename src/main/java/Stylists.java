import java.util.List;
import org.sql2o.*;

public class Stylists {
    private String name;
    private  String description;
    private int id;


    @Override
    public boolean equals(Object otherStylists){
        if(!(otherStylists instanceof Stylists)){
            return  false;
        }
        else {
         Stylists newStylist = (Stylists) otherStylists;
         return  this.getName().equals(newStylist.getName())&&
                  this.getDescription().equals(newStylist.getDescription())                                        &&
                 this.getId()==newStylist.getId();
        }
    }

    public Stylists(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public  String getDescription(){
        return description;
    }
    public int getId(){
        return id;
    }

}
