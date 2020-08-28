package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class CustomerFacade {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        
        facade.addCustomer("New", "User");
        for (Customer c : facade.allCustomers()) {
            System.out.println(c.getFirstName() + " " + c.getLastName() + " made at " + c.getCreated());
        }
        System.out.println(facade.getNumberOfCustomers());
        for (Customer c : facade.findByLastName("Ekstrom")) {
            System.out.println(c.getFirstName() + " " + c.getLastName() + " made at " + c.getCreated());
        }
        System.out.println(facade.findByID(2).getFirstName());
    }

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static CustomerFacade instance;

    public CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer addCustomer(String fName, String lName) {
        Customer customer = new Customer(fName, lName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return null;
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select customer from Customer customer", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("Select customer from Customer customer", Customer.class);
            return query.getResultList().size();
        } finally {
            em.close();
        }
    }
    
    public List<Customer> findByLastName(String name){
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Select customer from Customer customer where customer.lastName = :name");
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
