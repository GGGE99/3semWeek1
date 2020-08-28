package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import entity.Animal;
import java.util.List;
import javax.ws.rs.PathParam;

@Path("animals_db")
public class AnimalFromDB {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @GET
    @Path("animals")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @GET
    @Path("animalbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalsById(@PathParam("id") int id) {
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            Animal animal = animals.get(id);
            String jsonString = GSON.toJson(animal);
            return jsonString;
        } catch (Exception e) {
            String err = "{\"err\":404}";
            return err;
        }
    }

    @GET
    @Path("animalbytype/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalsByType(@PathParam("type") String type) {
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a where a.type = :type", Animal.class);
            query.setParameter("type", type);
            Animal animal = query.getSingleResult();
            String jsonString = GSON.toJson(animal);
            return jsonString;
        } catch (Exception e) {
            String err = "{\"err\":404}";
            return err;
        }
    }

    @GET
    @Path("random_animal")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalsByType() {
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();

            int num = (int) (Math.random() * animals.size());
            System.out.println(num);
            Animal animal = animals.get(num);

            String jsonString = GSON.toJson(animal);
            return jsonString;
        } catch (Exception e) {
            String err = "{\"err\":404}";
            return err;
        }
    }

}
