package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.DB;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import facades.EmployeeFacade;
import java.util.Map;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Path("employee")
public class RenameMeResource {

    //NOTE: Change Persistence unit name according to your setup
    private static DB db = DB.getDb();
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu", (Map) db.getMemberProps().get("prud"));
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        String json = GSON.toJson(facade.getAllEmployees());
        System.out.println(json);
        return json;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo(@PathParam("id") int id) {
        String json = GSON.toJson(facade.getEmployeeById(id));
        System.out.println(json);
        return json;
    }

    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo1() {
        String json = GSON.toJson(facade.getEmployeesWithHighestSalary());
        System.out.println(json);
        return json;
    }

    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo1(@PathParam("name") String name) {
        String json = GSON.toJson(facade.getEmployeesByName(name));
        System.out.println(json);
        return json;
    }

}
