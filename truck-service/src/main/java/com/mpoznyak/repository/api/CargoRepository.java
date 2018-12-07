package com.mpoznyak.repository.api;

import com.mpoznyak.model.Cargo;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 18:55
 */
public interface CargoRepository {

    void add(Cargo cargo);

    void add(Iterable<Cargo> cargoes);

    void remove(Cargo cargo);

    void remove(Long id);

    void update(Cargo cargo);

    List<Cargo> query();

    List<Cargo> queryExisted();
}
