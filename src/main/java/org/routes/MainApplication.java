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
    }

    private void fetchAndProcessRoutes(String sessionId) {
        apiController.getRoutes(sessionId)
                .doOnTerminate(() -> System.out.println("GET request completed"))
                .doOnSuccess(routes -> {
                    if (routes != null && !routes.isEmpty()) {
                        System.out.println("Retrieved Routes: " + routes);
                    } else {
                        System.out.println("No routes retrieved or routes are empty.");
                    }
                })
                .subscribe();
    }
}
