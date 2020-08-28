/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author marcg
 */
public class EmployeeFacade {
    private static DB db = DB.getDb();
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu", (Map) db.getMemberProps().get("prud"));
    private static EmployeeFacade instance;
    
    public EmployeeFacade() {
    }

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;

    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {

        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);

        facade.createEmployee("hans", "det en hjort", 1000);
        facade.createEmployee("ip", "det en hjort", 1);

        System.out.println(facade.getEmployeeById(1));

        for (Employee e : facade.getEmployeesByName("ib")) {
            System.out.println(e);
        }
        System.out.println("");
        for (Employee e : facade.getAllEmployees()) {
            System.out.println(e);
        }

        System.out.println("____________________________________");
        System.out.println(facade.getEmployeesWithHighestSalary());

    }

    

    //    createEmployee    
    //    getEmployeeById
    //    getEmployeesByName
//    getAllEmployees
//    getEmployeesWithHighestSalary
    public void createEmployee(String name, String adress, int salary) {
        Employee employee = new Employee(name, adress, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Employee getEmployeeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            return employee;
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("Select e from Employee e", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("Select e FROM Employee e where e.salary=(Select MAX(e.salary) FROM Employee e)", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Select e from Employee e where e.name = :name");
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
