package com.mpoznyak.service.api;

import com.mpoznyak.dto.rest.CargoDTORest;
import com.mpoznyak.model.Cargo;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 16:45
 */

public interface CargoService {

    List<Cargo> getAllCargoes();

    List<CargoDTORest> getAllCargoesDTORest();
}
