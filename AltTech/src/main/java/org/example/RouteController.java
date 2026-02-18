package org.example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    @GetMapping("/test")
    public String test() {
        return "TourAI API funcionando!";
    }
}