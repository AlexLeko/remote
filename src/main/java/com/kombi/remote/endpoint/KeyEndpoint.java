package com.kombi.remote.endpoint;

import com.kombi.remote.model.Key;
import com.kombi.remote.services.KeyService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class KeyEndpoint {

    @Autowired
    private KeyService keyService;

    @GetMapping("/status/{id}")
    public ResponseEntity<?> status(@PathParam("id") String id){

        boolean status = false;

        try{
            status = keyService.status(id);
        }catch(NotFoundException nfe){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PatchMapping("/lock/{id}")
    public ResponseEntity<?> lock(@PathParam("id") String id){

        try{
            keyService.lock(id);
        }catch(NotFoundException nfe){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/unlock/{id}")
    public ResponseEntity<?> unLock(@PathParam("id") String id){

        try{
            keyService.unlock(id);
        }catch(NotFoundException nfe){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/locate/{id}")
    public ResponseEntity<?> locate(@PathParam("id") String id){

        Key key = new Key();

        try{
            key =  keyService.locate(id);
        }catch(NotFoundException nfe){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(key, HttpStatus.OK);
    }
}
