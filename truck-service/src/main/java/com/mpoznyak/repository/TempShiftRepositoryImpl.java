package com.mpoznyak.repository;

import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.TempShift;
import com.mpoznyak.repository.api.TempShiftRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 11/11/18  at 03:30
 */

@Repository
public class TempShiftRepositoryImpl implements TempShiftRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Loggable
    public void add(TempShift tempShift) {
        add(Collections.singletonList(tempShift));
    }

    @Override
    @Loggable
    public void add(Iterable<TempShift> tempShifts) {
        for (TempShift tempShift : tempShifts) {
            entityManager.persist(tempShift);
        }
    }

    @Override
    @Loggable
    public void remove(TempShift tempShift) {
        entityManager.remove(tempShift);
    }

    @Override
    @Loggable
    public void update(TempShift tempShift) {
        TempShift tempShiftDb = entityManager.find(TempShift.class, tempShift.getId());
        tempShiftDb.setStartDate(tempShift.getStartDate());
        tempShiftDb.setStartTempShift(tempShift.getStartTempShift());
    }

    @Override
    @Loggable
    public List<TempShift> query() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TempShift> query = criteriaBuilder.createQuery(TempShift.class);
        Root<TempShift> tempShiftRoot = query.from(TempShift.class);
        query.select(tempShiftRoot);
        TypedQuery<TempShift> typedQuery = entityManager.createQuery(query);
        List<TempShift> tempShifts = typedQuery.getResultList();
        return tempShifts;
    }
}
