package com.mpoznyak.repository;

import com.mpoznyak.model.City;
import com.mpoznyak.repository.api.CityRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 19:35
 */
@Repository
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<City> query() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<City> query = criteriaBuilder.createQuery(City.class);
        Root<City> cityRoot = query.from(City.class);
        query.select(cityRoot);
        TypedQuery<City> typedQuery = entityManager.createQuery(query);
        List<City> cities = typedQuery.getResultList();
        return cities;
    }
}
