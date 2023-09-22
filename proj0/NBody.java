import java.util.Arrays;

public class NBody {
    private static final String bgDir = "images//starfield.jpg"; // Set the background directory address.
    public static double readRadius(String dir) {
        In in = new In(dir);
        int count = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String dir) {
        In in = new In(dir);
        int count = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[count];
        for (int i = 0; i < count; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);// Creating a "planet" array to put each planet properties.
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        System.out.println(Arrays.stream(planets).toList());
        double radius = readRadius(filename);
        StdDraw.enableDoubleBuffering(); // Make sure the effect would load in buffering.
        StdDraw.setScale(-radius, radius); // Set the universe scale to make appropriate
        StdDraw.clear(); // Clear the screen.
        double time = 0;
        Double[] xForce = new Double[planets.length];
        Double[] yForce = new Double[planets.length]; // Creating arrays for each planet to save each planet gravitational force.
        while (time <= T) {
            for (int i = 0; i < planets.length ; i++) {
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }// Calculate each changed planet force from gravitational force. And save them in arrays.
            for (int i = 0; i < planets.length ; i++) {
                planets[i].update(dt, xForce[i], yForce[i]);
            }// Update each planet position after getting force.
            StdDraw.picture(0, 0, bgDir); // Draw the background each time to update.
            for (Planet planet : planets) {
                planet.draw();
            }// Draw the planets.
            StdDraw.show();// Show the planets from the buffering.
            StdDraw.pause(10);// Don't get blinking for short vision stay.
            time += dt;// Adding time to proceeding.
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
