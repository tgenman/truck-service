package com.mpoznyak.repository;

import com.mpoznyak.model.Cargo;
import com.mpoznyak.repository.api.CargoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 06/11/2018  at 18:36
 */

@Repository
public class CargoRepositoryImpl implements CargoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Cargo cargo) {
        add(Collections.singletonList(cargo));
    }

    @Override
    public void add(Iterable<Cargo> cargoes) {
        for (Cargo cargo : cargoes) {
            entityManager.persist(cargo);
        }
    }

    @Override
    public void remove(Cargo cargo) {
        Query query = entityManager.createQuery("UPDATE Cargo d SET d.deleted = true " +
                "WHERE d.id = :id");
        query.setParameter("id", cargo.getId());
        query.executeUpdate();
    }

    @Override
    public void remove(Long id) {
        Cargo cargo = new Cargo();
        cargo.setId(id);
        remove(cargo);
    }

    public void update(Cargo cargo) {
        Cargo cargoDb = entityManager.find(Cargo.class, cargo.getId());
        cargoDb.setName(cargo.getName());
        cargoDb.setStatus(cargo.getStatus());
        cargoDb.setWeight(cargo.getWeight());
    }

    @Override
    public List<Cargo> query() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cargo> query = criteriaBuilder.createQuery(Cargo.class);
        Root<Cargo> cargoRoot = query.from(Cargo.class);
        query.select(cargoRoot);
        TypedQuery<Cargo> typedQuery = entityManager.createQuery(query);
        List<Cargo> cargoes = typedQuery.getResultList();
        return cargoes;
    }

    @Override
    public List<Cargo> queryExisted() {

        Query query = entityManager.createQuery("SELECT d FROM Cargo d WHERE d.deleted = false ");
        List<Cargo> cargoes = query.getResultList();
        return cargoes;
    }
}
