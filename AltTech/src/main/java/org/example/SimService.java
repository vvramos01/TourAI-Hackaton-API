package org.example;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class SimService {

    private final Random rnd = new Random();

    // retorna um mapa com lotacao e clima simulados para demo
    public Map<String, Object> getPoiStatus(Long poiId) {
        Map<String, Object> m = new HashMap<>();
        m.put("lotacao", 10 + rnd.nextInt(90)); // 10..99
        m.put("clima", rnd.nextBoolean() ? "ensolarado" : "chuva");
        return m;
    }
}
