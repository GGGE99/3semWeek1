package rest;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.AnimalNoDB;

@Path("animals")
public class AnimalDemoesource {

    @Context
    private UriInfo context;


    public AnimalDemoesource() {
    }

   
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        //TODO return proper representation object
        return "Vuf… (Message from a dog)";

    }

    @GET
    @Path("/animal_list")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson1() {
        //TODO return proper representation object
        return "[Dog, Cat, Mouse, Bird]";

    }
    
    @GET
    @Path("/animal")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson3() {
        AnimalNoDB animal = new AnimalNoDB("Dog", "Vuf!");

        //TODO return proper representation object
        return new Gson().toJson(animal);

    }

 
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
