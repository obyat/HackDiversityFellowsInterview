package org.routes.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.routes.model.Route;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoutesServiceTest {

    private RoutesService routesService;

    @BeforeEach
    public void setUp() {
        routesService = new RoutesService();
    }

    @Test
    public void testFilterAndSortRoutes() {
        Route route1 = new Route("1", "Entrance", "Exhibit A", true, 150);
        Route route2 = new Route("2", "Entrance", "Restroom", true, 50);
        Route route3 = new Route("3", "Exhibit A", "Cafeteria", false, 200);
        Route route4 = new Route("4", "Restroom", "Gift Shop", true, 70);
        Route route5 = new Route("5", "Cafeteria", "Exhibit B", true, 300);

        List<Route> routes = List.of(route1, route2, route3, route4, route5);

        List<Route> sortedRoutes = routesService.filterAndSortRoutes(routes);

        assertEquals(4, sortedRoutes.size(), "There should be 4 accessible routes.");
        assertEquals(route2, sortedRoutes.get(0), "The first route should be the shortest.");
        assertEquals(route4, sortedRoutes.get(1), "The second route should be the second shortest.");
        assertEquals(route1, sortedRoutes.get(2), "The third route should be the third shortest.");
        assertEquals(route5, sortedRoutes.get(3), "The fourth route should be the longest.");
    }

    @Test
    public void testFilterAndSortRoutes_noAccessibleRoutes() {
        Route route1 = new Route("1", "Exhibit A", "Cafeteria", false, 200);
        Route route2 = new Route("2", "Exhibit B", "Restroom", false, 150);

        List<Route> routes = List.of(route1, route2);
        List<Route> sortedRoutes = routesService.filterAndSortRoutes(routes);

        assertTrue(sortedRoutes.isEmpty(), "There should be no accessible routes.");
    }

    @Test
    public void testFilterAndSortRoutes_emptyList() {
        List<Route> sortedRoutes = routesService.filterAndSortRoutes(new ArrayList<>());

        assertTrue(sortedRoutes.isEmpty(), "The result should be empty for an empty input list.");
    }
}
