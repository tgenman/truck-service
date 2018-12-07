package com.mpoznyak.repository.api;

import com.mpoznyak.model.Road;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 19:25
 */
public interface RoadRepository {

    List<Road> query();
}
