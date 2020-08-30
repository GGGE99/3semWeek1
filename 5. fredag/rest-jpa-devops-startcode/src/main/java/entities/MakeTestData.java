/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import facades.FacadeExample;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author marcg
 */
public class MakeTestData {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    public static void main(String[] args) {
        facades.FacadeExample facade = facades.FacadeExample.getFacadeExample(EMF);
        BankCustomer b1 = new BankCustomer("Ib", "Hansen", "15936", 15874, 5, "idk");
        BankCustomer b2 = new BankCustomer("Hans", "Hansen", "11235", 12345, 4, "idk");
        BankCustomer b3 = new BankCustomer("Bo", "Jensen", "124789", 53321211, 1, "idk");
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(b1);
            em.persist(b3);
            em.persist(b2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
        System.out.println(facade.getCustomerByID(1));
        System.out.println(facade.getCustomerByName("Hans"));

    }

}
