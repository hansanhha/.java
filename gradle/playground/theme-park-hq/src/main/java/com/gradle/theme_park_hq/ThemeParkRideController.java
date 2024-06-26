package com.gradle.theme_park_hq;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Iterator;

@RestController
public class ThemeParkRideController {

    @GetMapping("/rides")
    public Iterator<ThemeParkRide> getRides() {
        return Arrays.asList(
                new ThemeParkRide("Rollercoaster",
                        "Train ride that speeds you along."),
                new ThemeParkRide("Log flume",
                        "Boat ride with plenty of splashes."),
                new ThemeParkRide("Teacups",
                        "Spinning ride in a giant tea-cup.")
        ).iterator();
    }
}