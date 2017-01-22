package ResultService;

import java.util.ArrayList;
import IndividualService.Individual;
/**
 * Created by blank on 1/10/2017.
 */


public class FitnessFunction {

    private Individual x;
    double upperLimit;
    double lowerLimit;
    private double[] v = new double[2];
    private Coordinates currentCoords;
    private ArrayList<Coordinates> coords = new ArrayList<>();
    private static final double rMin = 30.0;
    private static final double rMax = 40.0;

    class Coordinates
    {
        private double r;
        private double phi;

        Coordinates(double r1, double phi1){
            r = r1;
            phi = phi1;
        }

        public double getPhi() {
            return phi;
        }

        public double getR() {
            return r;
        }
    }

    class CoordinatesXY
    {
        private double coordX;
        private double coordY;

        CoordinatesXY(double coordX, double coordY)
        {
            this.coordX = coordX;
            this.coordY = coordY;
        }

        public double getCoordX() {
            return coordX;
        }

        public double getCoordY() {
            return coordY;
        }
    }

    public FitnessFunction(Individual x1, int size){
        double scale = size/(2.0*rMax);
        double initialR = 35.0*scale;
        upperLimit = rMax*scale;
        lowerLimit = rMin*scale;
        //System.out.println("initalR = "+initialR);
        currentCoords = new Coordinates(initialR,0);
        x = x1;
        this.v[0] = 0;
        this.v[1] = 0;
    }


    private void saveCoords(){
        Coordinates tmp = new Coordinates(currentCoords.r,currentCoords.phi);
        coords.add(tmp);
    }


    private void makeStep(double dt){
        double a0[] = x.getA0();
        double B[][] = x.getB();
        double c[] = x.getC();
        double a_r = a0[0] + B[0][0]*v[0] + B[0][1]*v[1] + c[0]*currentCoords.r;
        //System.out.println("a_r = "+a_r);
        double dv_r = a_r * dt;
        v[0] += dv_r;
        double dr = v[0] * dt;
        currentCoords.r += dr;
        double a_phi = a0[1] + B[1][0]*v[0] + B[1][1]*v[1] + c[1]*currentCoords.r;
        double dv_phi = a_phi * dt;
        v[1] += dv_phi;
        double dphi = (v[1] * dt);// / currentCoords.r;
        currentCoords.phi += dphi;
    }

    private Coordinates getIntersectionPoint()
    {
        Coordinates prevCoords = coords.get(coords.size() - 1);
        double x0 = prevCoords.r * Math.cos(prevCoords.phi);
        double y0 = prevCoords.r * Math.sin(prevCoords.phi);
        double x1 = currentCoords.r * Math.cos(currentCoords.phi);
        double y1 = currentCoords.r * Math.sin(currentCoords.phi);

        double dx = x1 - x0;
        double dy = y1 - y0;

        // nie uwzgledniamy tutaj srodka bo jest u nas (0,0)
        double A = dx * dx + dy * dy;
        double B = 2 * (dx * x0 + dy * y0);
        double C = x0 * x0 + y0 * y0 - rMin * rMin;

        double det = B * B - 4 * A * C;
        double t;
        if ((A <= 0.0000001) || (det < 0))
            return null;
        else if (det == 0)
        {
            // jedno rozwiazanie
            t = -B / (2 * A);
            double intersectionX = x0 + t * dx;
            double intersectionY = y0 + t * dy;
            return getPolarCoordinates(intersectionX, intersectionY);
        }
        else
        {
            // dwa rozwiazania
            t = (-B + Math.sqrt(det)) / (2 * A);
            CoordinatesXY intersection1 = new CoordinatesXY(x0 + t * dx, y0 + t * dy);
            t = (-B - Math.sqrt(det)) / (2 * A);
            CoordinatesXY intersection2 = new CoordinatesXY(x0 + t * dx, y0 + t * dy);
            double dst1 = Distance(intersection1.coordX - x0, intersection1.coordY - y0);
            double dst2 = Distance(intersection2.coordX - x0, intersection2.coordY - y0);
            if(dst1 < dst2)
                return getPolarCoordinates(intersection1.coordX, intersection1.coordY);
            else
                return getPolarCoordinates(intersection2.coordX, intersection2.coordY);
        }
    }

    private static double Distance(double dx, double dy)
    {
        return Math.sqrt(dx * dx + dy * dy);
    }

    private Coordinates getPolarCoordinates(double x, double y)
    {
        double r = Math.sqrt(x * x + y * y);
        double phi = calculatePhi(x, y);;
        return new Coordinates(r, phi);
    }

    private static double calculatePhi(double x, double y)
    {
        double a = y/x;
        if(x > 0 && y >= 0)
            return Math.atan(a);
        else if (x > 0 && y < 0)
            return Math.atan(a) + 2 * Math.PI;
        else if (x < 0)
            return Math.atan(a) + Math.PI;
        else if (x == 0 && y > 0)
            return Math.PI / 2;
        else if (x == 0 && y < 0)
            return 3 * Math.PI/2;
        else
            return 0;
    }

    public double testParameters() {
        double dt = 0.001;
        double time = 0.0;
        for (;;) {
            makeStep(dt);
            if(currentCoords.r > upperLimit)
            {
                currentCoords.r = upperLimit;
                saveCoords();
                break;
            }

            if(currentCoords.r < lowerLimit)
            {
                currentCoords.r = lowerLimit;
                saveCoords();
                break;
            }

            /*if(coords.size() > 0)
            {
                Coordinates intersectionPoint = getIntersectionPoint();
                if (intersectionPoint != null)
                {
                    //System.out.println(intersectionPoint.r + " " + intersectionPoint.phi);
                    currentCoords.r = intersectionPoint.r;
                    currentCoords.phi = intersectionPoint.phi;
                    saveCoords();
                    break;
                }
            }*/

            saveCoords();
            /*System.out.println( "v[0]= " + v[0] + "\n" +
                                "v[1]= " + v[1] + "\n" +
                                "r   = " + currentCoords.r + "\n" +
                                "phi = " + currentCoords.phi + "\n");*/
            if (currentCoords.phi > 2*Math.PI || currentCoords.phi < (-2*Math.PI)) break;



            time += dt;
        }
        double distance = currentCoords.phi/(2*Math.PI);
        if(distance > 1.0)
                distance = 1.0;
        //System.out.println("Distance: "+distance);
        //System.out.println("Time: "+ time);
        return ((distance*distance)/time) * (distance == 1.0 ? 1.0 : 0.7);
    }

    public ArrayList<Coordinates> getCoords(){
        return coords;
    }

    public void printarray(){
        for(Coordinates c: coords){
            System.out.println(c.r + " " + c.phi);
        }
    }
}

