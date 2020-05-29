package com.example.apps.apimaps;

public class PlaceCoordinates {
    private Results[] results;

    public Results[] getResults() {
        return results;
    }

    public class Results{
        private String name;
        private Geometry geometry;

        public String getName() {
            return name;
        }

        public Geometry getGeometry() {
            return geometry;
        }
    }

    public class Geometry{
        private Location location;

        public Location getLocation() {
            return location;
        }
    }

    public class Location{
        private double lng;
        private double lat;

        public double getLng() {
            return lng;
        }

        public double getLat() {
            return lat;
        }
    }
}
