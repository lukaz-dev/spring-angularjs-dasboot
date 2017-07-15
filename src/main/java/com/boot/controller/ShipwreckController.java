package com.boot.controller;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class ShipwreckController {

    @Autowired
    private ShipwreckRepository shipwreckRepository;

    @GetMapping("shipwrecks")
    public List<Shipwreck> list() {
        return shipwreckRepository.findAll();
    }

    @PostMapping("shipwrecks")
    public Shipwreck create(@RequestBody Shipwreck shipwreck) {
        return shipwreckRepository.saveAndFlush(shipwreck);
    }

    @GetMapping("shipwrecks/{id}")
    public Shipwreck get(@PathVariable Long id) {
        return shipwreckRepository.findOne(id);
    }

    @PutMapping("shipwrecks/{id}")
    public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
        Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
        BeanUtils.copyProperties(shipwreck, existingShipwreck);
        return shipwreckRepository.saveAndFlush(existingShipwreck);
    }

    @DeleteMapping("shipwrecks/{id}")
    public Shipwreck delete(@PathVariable Long id) {
        Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
        shipwreckRepository.delete(existingShipwreck);
        return existingShipwreck;
    }
}
