package org.ort.starwars.fleet.gateways;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.ort.starwars.fleet.gateways.dtos.SwapiPageDto;
import org.ort.starwars.fleet.gateways.dtos.SwapiStarshipDto;
import org.ort.starwars.fleet.utils.Parsing;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

@Component
public class SwapiProxy {

    private static final String BASE_URL = "https://swapi.dev/api";

    private final WebClient client;

    public SwapiProxy(WebClient.Builder builder) {
        this.client = builder.baseUrl(BASE_URL).build();
    }

    /** Récupère toutes les pages de /starships. */
    public List<SwapiStarshipDto> fetch() throws ConnectException {
        List<SwapiStarshipDto> all = new ArrayList<>();
        String endpoint = "/starships/";
        try {
            while (endpoint != null) {
                SwapiPageDto page = client.get()
                        .uri(endpoint)
                        .retrieve()
                        .bodyToMono(SwapiPageDto.class)
                        .block();
                if (page == null || page.getResults() == null) {
                    break;
                }
                all.addAll(page.getResults());
                endpoint = Parsing.getEndpoint(BASE_URL, page.getNext());
            }
        } catch (WebClientRequestException e) {
            throw new ConnectException("SWAPI injoignable : " + e.getMessage());
        }
        return all;
    }
}