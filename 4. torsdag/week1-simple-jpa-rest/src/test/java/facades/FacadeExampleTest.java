package facades;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FacadeExampleTest {

    // EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
    private static DB db = DB.getDb();
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu", (Map) db.getMemberProps().get("test"));
    private static final EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);

    private static Employee e1 = new Employee("ib", "hjortsvej 21", 123456);
    private static Employee e2 = new Employee("hans", "hjortsvej 22", 123);
    private static Employee e3 = new Employee("bent", "hjortsvej 23", 1);
    private static List<Employee> ems = new ArrayList();

    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        EntityManager em = emf.createEntityManager();

        ems.add(e1);
        ems.add(e2);
        ems.add(e3);

        try {
            for (Employee e : ems) {
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
//        createEmployee();

//        Add code to setup entities for test before running any test methods
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    @BeforeEach
    public void setUp() {
//        Put the test database in a proper state before each test is run
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetEmployeeById() {
        Employee ex = e1;
        Employee ac = facade.getEmployeeById(1);
        assertEquals(ex, ac);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> ex = ems;
        List<Employee> ac = facade.getAllEmployees();
        assertEquals(ex, ac);
    }

    @Test
    public void testGetEmployeesWithHighestSalary() {
        List<Employee> ex = new ArrayList();
        ex.add(e1);
        List<Employee> ac = facade.getEmployeesWithHighestSalary();
        assertEquals(ex, ac);
    }

    @Test
    public void testGetEmployeesByName() {
        List<Employee> ex = new ArrayList();
        ex.add(e3);
        List<Employee> ac = facade.getEmployeesByName("bent");
        assertEquals(ex, ac);
    }

    @Test
    public void createEmployee() {
        Employee e = new Employee("jens", "hjortsvej 24", 123456);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        Employee ex = e;
        Employee ac = facade.getEmployeeById(4);
        assertEquals(ex, ac);
    }

}
