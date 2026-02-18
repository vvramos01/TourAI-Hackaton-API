package org.example;


import org.example.Poi;
import org.example.PoiRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private final PoiRepository poiRepo;
    private final SimService simService;

    public RouteService(PoiRepository poiRepo, SimService simService) {
        this.poiRepo = poiRepo;
        this.simService = simService;
    }

    public List<Poi> generate(List<String> prefs, double userLat, double userLon, int desiredCount) {

        List<Poi> all = poiRepo.findAll();
        if (all.isEmpty()) return List.of();

        Map<Poi, Double> score = new HashMap<>();
        for (Poi p: all) {
            double prefMatch = prefs.contains(p.getCategory()) ? 1.0 : 0.2;
            var status = simService.getPoiStatus(p.getId());
            double lot = ((Number) status.get("lotacao")).doubleValue();
            double dist = distance(userLat, userLon, p.getLat(), p.getLon());
            double sc = 5 * prefMatch + 2 * (1.0 / (dist + 0.1)) + 3 * (1.0 / (lot + 1));
            score.put(p, sc);
        }

        List<Poi> sorted = score.entrySet().stream()
                .sorted(Map.Entry.<Poi, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<Poi> chosen = sorted.subList(0, Math.min(desiredCount, sorted.size()));
        return nearestOrder(userLat, userLon, chosen);
    }

    private List<Poi> nearestOrder(double lat, double lon, List<Poi> list) {
        List<Poi> rest = new ArrayList<>(list);
        List<Poi> path = new ArrayList<>();
        double cx = lat, cy = lon;
        while (!rest.isEmpty()) {
            Poi nearest = null;
            double best = Double.MAX_VALUE;
            for (Poi p : rest) {
                double d = distance(cx, cy, p.getLat(), p.getLon());
                if (d < best) { best = d; nearest = p; }
            }
            path.add(nearest);
            rest.remove(nearest);
            cx = nearest.getLat(); cy = nearest.getLon();
        }
        return path;
    }

    private double distance(double a, double b, double c, double d) {
        double dx = a - c, dy = b - d;
        return Math.sqrt(dx*dx + dy*dy);
    }
}
