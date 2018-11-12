package com.mpoznyak.service;

import com.mpoznyak.configuration.Initializer;
import com.mpoznyak.configuration.RepositoryConfig;
import com.mpoznyak.configuration.WebConfig;
import com.mpoznyak.model.Driver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by Max Poznyak
 * on 11/12/18  at 13:45
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, WebConfig.class})
@WebAppConfiguration
public class DriverServiceTest {

    @Autowired
    private DriverService driverService;

    @Test
    public void testGetDriverById() {
        Driver driver = driverService.getDriverForId(2L);
        assertEquals("Harry", driver.getFirstName());
    }
}
