package com.kombi.remote.mock;

import com.kombi.remote.model.Car;
import com.kombi.remote.model.Key;
import com.kombi.remote.repository.CarRepository;
import com.kombi.remote.repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;

@Service
public class DBMock {

    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    private CarRepository carRepository;

    @PostConstruct
    public void instantiateTestDatabase() throws ParseException {

        Key key = new Key();
        Car car = new Car();

        car.setTag("DXF5887");

        key.setCar(car);
        key.setStatus(0);
        key.setLatitude("-21.775613");
        key.setLongitude("-48.170834");

        carRepository.save(car);
        keyRepository.save(key);
    }
}