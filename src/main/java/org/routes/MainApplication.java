package org.routes;

import org.routes.model.Route;
import org.routes.webclient.ApiControllerService;
import org.routes.services.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    @Autowired
    private ApiControllerService apiController;

    @Autowired
    private RoutesService routesService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String sessionId = "Bearer d86afe55-bd4a-44a9-9ba5-42d470136580";
        fetchAndProcessRoutes(sessionId);
        submitRoutes(sessionId);
    }

    private void fetchAndProcessRoutes(String sessionId) {
        apiController.getRoutes(sessionId)
                .doOnTerminate(() -> System.out.println("GET request completed"))
                .doOnSuccess(routes -> {
                    if (routes != null && !routes.isEmpty()) {
                        System.out.println("Retrieved Routes: " + routes);

                        List<Route> sortedRoutes = routesService.filterAndSortRoutes(routes);
                        System.out.println("Sorted Routes: " + sortedRoutes);

                    } else {
                        System.out.println("No routes retrieved or routes are empty.");
                    }
                })
                .subscribe();
    }

    private void submitRoutes(String sessionId) {
        apiController.getMockRoutes(sessionId)
                .doOnTerminate(() -> System.out.println("GET request for mock routes completed"))
                .doOnSuccess(routes -> {
                    if (routes != null && !routes.isEmpty()) {
                        System.out.println("Retrieved Mock Routes: " + routes);

                        List<Route> sortedRoutes = routesService.filterAndSortRoutes(routes);
                        System.out.println("Sorted Routes: " + sortedRoutes);

                        apiController.submitSortedRoutes(sessionId, sortedRoutes)
                                .doOnTerminate(() -> System.out.println("POST request for submitting sorted routes completed"))
                                .doOnSuccess(response -> {
                                    System.out.println("Validation Response: " + response);
                                })
                                .doOnError(error -> {
                                    System.err.println("Error during submission: " + error.getMessage());
                                })
                                .subscribe();
                    } else {
                        System.out.println("No mock routes retrieved or routes are empty.");
                    }
                })
                .doOnError(error -> {
                    System.err.println("Error during GET request for mock routes: " + error.getMessage());
                })
                .subscribe();
    }
}