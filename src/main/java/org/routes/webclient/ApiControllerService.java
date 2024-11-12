package org.routes.webclient;

import org.routes.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ApiControllerService {

    private final WebClient webClient;
    private final String URI = "https://hackdiversity.xyz";

    @Autowired
    public ApiControllerService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(URI).build(); // Initialize WebClient as a field
    }

    public Mono<List<Route>> getRoutes(String sessionId) {
        return this.webClient.get()
                .uri("/api/navigation/routes")
                .header("Authorization", sessionId)
                .retrieve()
                .bodyToFlux(Route.class)
                .collectList();
    }

    public Mono<List<Route>> getMockRoutes(String sessionId) {
        return this.webClient.get()
                .uri("/api/test/mockRoutes")
                .header("Authorization", sessionId)
                .retrieve()
                .bodyToFlux(Route.class)
                .collectList();
    }

    public Mono<String> submitSortedRoutes(String sessionId, List<Route> sortedRoutes) {
        return this.webClient.post()
                .uri("/api/navigation/sorted_routes")
                .header("Authorization", sessionId)
                .header("Content-Type", "application/json")
                .bodyValue(sortedRoutes)
                .retrieve()
                .bodyToMono(String.class);
    }
}
