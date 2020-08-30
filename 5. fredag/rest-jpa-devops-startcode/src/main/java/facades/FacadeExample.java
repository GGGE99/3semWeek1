package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeExample() {
    }

    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerDTO getCustomerByID(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            BankCustomer b = em.find(BankCustomer.class, id);
            return new CustomerDTO(b);
        } finally {
            em.close();
        }
    }

    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BankCustomer> query = em.createQuery("Select e from BankCustomer e Where e.firstName = :name", BankCustomer.class);
            query.setParameter("name", name);
            List<BankCustomer> a = query.getResultList();
            List<CustomerDTO> b = new ArrayList();
            
            for(BankCustomer bc: a){
                b.add(new CustomerDTO(bc));
            }

            return b;
        } finally {
            em.close();
        }
    }

//List<CustomerDTO> getCustomerByName(String name)
//BankCustomer addCustomer(BankCustomer cust)
//List<BankCustomer> getAllBankCustomers()
}
