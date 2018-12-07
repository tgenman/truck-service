package com.mpoznyak.repository.api;

import com.mpoznyak.model.Manager;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 19:06
 */
public interface ManagerRepository {

    void add(Manager manager);

    void add(Iterable<Manager> managers);

    void remove(Manager manager);

    void remove(Long id);

    void update(Manager manager);

    List<Manager> query();
}
