package com.mpoznyak.repository.api;

import com.mpoznyak.model.Shift;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 19:29
 */

public interface ShiftRepository {

    void add(Shift shift);

    void add(Iterable<Shift> shifts);

    void remove(Shift shift);

    void remove(Long id);

    void update(Shift shift);

    List<Shift> query();

}
