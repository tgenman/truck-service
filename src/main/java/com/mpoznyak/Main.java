package com.mpoznyak;

import com.mpoznyak.configuration.Constants;
import com.mpoznyak.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Manager manager = new Manager();
        manager.setFirstName("M");
        manager.setLastName("M");
        manager.setEmail("email");
        manager.setPhoneNumber("+456789");
        em.persist(manager);
        transaction.commit();
        em.close();
        emf.close();
    }
}
