public class NBody {

    public static void main(String[] args) {
        if (args.length < 2) {
			System.out.println("Please supply two number and path as a command line argument");
		}else{
            double T = Double.parseDouble(args[0]);
            double dt = Double.parseDouble(args[1]);
            String filename = args[2];
            double radius = NBody.readRadius(filename);
            Planet [] planets = NBody.readPlanets(filename);
            //System.out.println(radius);
            StdDraw.setScale(-radius, radius);
		    StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet member : planets){
                member.draw();
            }
            StdDraw.show();

            StdDraw.enableDoubleBuffering();
            double time = 0;
            while(time < T){
                double [] xForces = new double [planets.length];
                double [] yForces = new double [planets.length];
                for(int i = 0; i < planets.length; i++){
                    xForces[i] = planets[i].calcNetForceExertedByX(planets);
                    yForces[i] = planets[i].calcNetForceExertedByY(planets);
                }
                for (int i = 0; i < planets.length; i++){
                    planets[i].update(dt, xForces[i], yForces[i]);
                }
                StdDraw.clear();
                StdDraw.picture(0, 0, "images/starfield.jpg");
                for (Planet member : planets){
                    member.draw();
                }
                StdDraw.show();
                StdDraw.pause(10);
                time = time + dt;
            }
            //final state
            StdOut.printf("%d\n", planets.length);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < planets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
            }
        }	      
    }


    public static double readRadius(String path){
        In in = new In(path);
        int planetnumber = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int planetnumber = in.readInt();
        Planet[] planets = new Planet[planetnumber];
        double radius = in.readDouble();
        for(int i = 0; i < planetnumber;i++){
            double xP,yP,xV,yV,m;
            String imgFilename;
            xP = in.readDouble();
            yP = in.readDouble();
            xV = in.readDouble();
            yV = in.readDouble();
            m = in.readDouble();
            imgFilename = in .readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, imgFilename);
        }
        return planets;
    }

    //public static void  
}