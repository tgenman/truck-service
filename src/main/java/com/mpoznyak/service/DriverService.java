package com.mpoznyak.service;

import com.mpoznyak.model.Driver;
import com.mpoznyak.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 02:33
 */
@Service
@Transactional
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public void processNewDriverData(Driver driver) {
        driverRepository.add(driver);
    }

}
