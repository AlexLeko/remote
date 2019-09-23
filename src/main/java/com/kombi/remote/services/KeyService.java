package com.kombi.remote.services;

import com.kombi.remote.model.Key;
import com.kombi.remote.repository.KeyRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KeyService {

    @Autowired
    private KeyRepository keyRepository;

    public boolean status(String id) throws NotFoundException{
        Optional<Key> keyOptional = keyRepository.findById(id);
        Key key =  keyOptional.orElse(null);

        if(key == null){
            throw new NotFoundException("Key not found");
        }

        return key.isLocked();
    }

    public void lock(String id) throws NotFoundException{
        Optional<Key> keyOptional = keyRepository.findById(id);
        Key key =  keyOptional.orElse(null);

        if(key == null){
            throw new NotFoundException("Key not found");
        }

        key.lock();
        keyRepository.save(key);
    }

    public void unlock(String id) throws NotFoundException{
        Optional<Key> keyOptional = keyRepository.findById(id);
        Key key =  keyOptional.orElse(null);

        if(key == null){
            throw new NotFoundException("Key not found");
        }

        key.unlock();
        keyRepository.save(key);
    }

    public Key locate(String id) throws NotFoundException{
        Optional<Key> keyOptional = keyRepository.findById(id);
        Key key =  keyOptional.orElse(null);

        if(key == null){
            throw new NotFoundException("Key not found");
        }


        return  changeLocation(key);
    }

    private Key changeLocation(Key key){
        key.setLongitude();
        key.setLatitude();
        keyRepository.save(key);
    }
}
