package com.mpoznyak.service;

import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Road;
import com.mpoznyak.repository.api.RoadRepository;
import com.mpoznyak.service.api.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Max Poznyak
 * on 31/10/2018  at 00:37
 */

@Service
public class RoadServiceImpl implements RoadService {

    @Autowired
    private RoadRepository roadRepository;

    @Override
    @Loggable
    @Transactional
    public List<Road> getRoads() {
        return roadRepository.query();
    }

}
