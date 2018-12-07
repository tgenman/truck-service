package com.mpoznyak.repository.api;

import com.mpoznyak.model.TempShift;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 19:31
 */
public interface TempShiftRepository {

    void add(TempShift tempShift);

    void add(Iterable<TempShift> tempShifts);

    void remove(TempShift tempShift);

    void update(TempShift tempShift);

    List<TempShift> query();
}
