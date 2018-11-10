package com.mpoznyak.repository;

import com.mpoznyak.model.Shift;
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
 * on 27/10/2018  at 14:01
 */

@Repository
public class ShiftRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void add(Shift shift) {
        add(Collections.singletonList(shift));
    }

    public void add(Iterable<Shift> shifts) {
        for (Shift shift : shifts) {
            entityManager.persist(shift);

        }
    }

    public void remove(Shift shift) {
        entityManager.remove(shift);
    }

    public void remove(Long id) {
        Shift shift = entityManager.find(Shift.class, id);
        remove(shift);
    }

    public void update(Shift shift) {
        Shift shiftDb = entityManager.find(Shift.class, shift.getId());
        shiftDb.setMonthStartAt(shift.getMonthStartAt());
        shiftDb.setMonthEndAt(shift.getMonthEndAt());
        shiftDb.setTimeMonthlyElapsed(shift.getTimeMonthlyElapsed());
        shiftDb.setTimeWeeklyElapsed(shift.getTimeWeeklyElapsed());
        shiftDb.setWeeklyRest(shift.getWeeklyRest());

    }

    public List<Shift> query() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shift> query = criteriaBuilder.createQuery(Shift.class);
        Root<Shift> shiftRoot = query.from(Shift.class);
        query.select(shiftRoot);
        TypedQuery<Shift> typedQuery = entityManager.createQuery(query);
        List<Shift> shifts = typedQuery.getResultList();
        return shifts;
    }
}
