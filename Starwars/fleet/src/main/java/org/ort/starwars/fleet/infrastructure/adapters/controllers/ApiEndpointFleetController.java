package org.ort.starwars.fleet.infrastructure.adapters.controllers;

import java.util.List;

import org.ort.starwars.fleet.domain.models.Mission;
import org.ort.starwars.fleet.domain.models.entities.Starship;
import org.ort.starwars.fleet.application.services.FleetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiEndpointFleetController {

    private final FleetService fleetService;

    @PostMapping("/fleet")
    public List<Starship> post(@RequestBody Mission mission) {
        return fleetService.forMission(mission);
    }
}
