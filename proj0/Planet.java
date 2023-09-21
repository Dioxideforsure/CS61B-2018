public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    static final double graCon = 6.67e-11; //The gravitational constant G = 6.67 * 10 ^ -11

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt(square(xxPos - p.xxPos) + square(yyPos - p.yyPos));
    }

    public double square(double n) {
        return n * n;
    }   //Use square function to simplify the calcDistance function in Square compute.

    public double calcForceExertedBy(Planet p) {
        return graCon * mass * p.mass / (square(xxPos - p.xxPos) + square(yyPos - p.yyPos));
    }

    public double calcForceExertedByX(Planet p) {
        double xForce; // Easy to conflict if xForce is negative.
        xForce = calcForceExertedBy(p) * (xxPos - p.xxPos) / calcDistance(p);
        if (xForce >= 0) {
            return xForce;
        }
        else return -xForce;
    }

    public double calcForceExertedByY(Planet p) {
        double yForce; // Easy to conflict if yForce is negative.
        yForce = calcForceExertedBy(p) * (yyPos - p.yyPos) / calcDistance(p);
        if (yForce >= 0) {
            return yForce;
        }
        else return -yForce;
    }

    public double calcNetForceExertedByX(Planet[] allplanet) {
        double xNetForce = 0; // Initialize the net force in x array, which is 0.
        if (allplanet == null || allplanet.length <= 1) {
            return xNetForce;
        } // Considering the allplanet would be empty or inaccessible.
        for (Planet planet : allplanet) {
            if (!(this.equals(planet))) {
                xNetForce += calcForceExertedByX(planet);
            } // Ignore the calculation between planet and itself.
        }
        return xNetForce;
    }

    public double calcNetForceExertedByY(Planet[] allplanet) {
        double yNetForce = 0;// Initialize the net force in y array, which is 0.
        if (allplanet == null || allplanet.length <= 1) {
            return yNetForce;
        }
        for (Planet planet : allplanet) {
            if (!(this.equals(planet))) {
                yNetForce += calcForceExertedByY(planet);
            }
        }
        return yNetForce;
    }

    public void update(double dt, double xF, double yF) {
        double ax = xF / this.mass;
        double ay = yF / this.mass; // Create the pattern of acceleration in x and y arrays.
        this.xxVel = this.xxVel + ax * dt;
        this.yyVel = this.yyVel + ay * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
       StdDraw.picture(this.xxPos, this.yyPos, "image//" + imgFileName);// Print each planet on proper position from the image.
    }
}
