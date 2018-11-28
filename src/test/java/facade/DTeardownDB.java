
import entity.Hotel;
import entity.Role;
import entity.User;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dradrach
 */
public class DTeardownDB {
    @Test    
    public void setupDB() {
        System.out.println("Teardown DB");
        assert(true);
        
//        HashMap<String, Object> puProperties = new HashMap<>();
//        puProperties.put("javax.persistence.sql-load-script-source", "scripts/ClearDB.sql");
//        Persistence.generateSchema("pu", puProperties);
    }
}
