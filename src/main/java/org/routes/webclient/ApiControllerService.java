package org.routes.webclient;

import org.routes.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ApiControllerService {

    @Autowired
    private final WebClient.Builder webClientBuilder;

    private final String URI = "https://hackdiversity.xyz";

    public ApiControllerService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<List<Route>> getRoutes(String sessionId) {
        WebClient webClient = webClientBuilder.baseUrl(URI).build();

        return webClient.get()
                .uri("/api/navigation/routes")
                .header("Authorization", sessionId)
                .retrieve()
                .bodyToFlux(Route.class)
                .collectList();
    }
}
