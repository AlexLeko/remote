package com.kombi.remote.repository;

import com.kombi.remote.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

}
