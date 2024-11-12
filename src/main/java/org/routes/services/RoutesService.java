package org.routes.services;

import org.routes.model.Route;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutesService {

    
    public List<Route> filterAndSortRoutes(List<Route> routes) {
        List<Route> filteredRoutes = new ArrayList<>();
        for (Route route : routes) {
            if (route.isAccessible()) {
                filteredRoutes.add(route);
            }
        }

        return bubbleSort(filteredRoutes);
    }

    private List<Route> bubbleSort(List<Route> routes) {
        int n = routes.size();
        if (routes.isEmpty() || n == 1) {
            return routes;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (routes.get(j).getDistance() > routes.get(j + 1).getDistance()) {
                    Route temp = routes.get(j);
                    routes.set(j, routes.get(j + 1));
                    routes.set(j + 1, temp);
                }
            }
        }
        return routes;
    }
}