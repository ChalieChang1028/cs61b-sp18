public class Planet{
    
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    
    /*Don't use Math.pow, which will result in slower code*/
    public  double calcDistance(Planet p){
        
        double x = this.xxPos - p.xxPos;
        double y = this.yyPos - p.yyPos;

        return Math.sqrt(x*x + y*y);
    }

    /*NOTE: Do not use Math.abs to fix sign issues with these methods. This will cause issues later when drawing planets.*/
    public double calcForceExertedBy(Planet p){
        
        double distance = this.calcDistance(p);

        return (G * this.mass * p.mass)/(distance*distance); 
    }

    public double calcForceExertedByX(Planet p){
        
        double posxgap = p.xxPos - this.xxPos;

        return this.calcForceExertedBy(p) * posxgap / this.calcDistance(p) ;
    }

    public double calcForceExertedByY(Planet p){
        
        double posygap = p.yyPos - this.yyPos;

        return this.calcForceExertedBy(p) * posygap / this.calcDistance(p) ;
    }

    /*  enhanced for loops*/
    public double calcNetForceExertedByX(Planet [] planets){
        double sumx = 0;
        for (Planet member : planets){
            if(this.equals(member)){
                continue;
            }
            sumx = sumx + this.calcForceExertedByX(member);
        }
        return sumx;
    }

    public double calcNetForceExertedByY(Planet [] planets){
        double sumy = 0;
        for (Planet member : planets){
            if(this.equals(member)){
                continue;
            }
            sumy = sumy + this.calcForceExertedByY(member);
        }
        return sumy;
    }

    public void update(double dt,double fX,double fY){
        double ax = fX/this.mass;
        double ay = fY/this.mass;

        this.xxVel = this.xxVel + ax*dt;
        this.yyVel = this.yyVel + ay*dt;
        this.xxPos = this.xxPos + dt*this.xxVel;
        this.yyPos = this.yyPos + dt*this.yyVel;
    }

    public void draw(){
        String filename = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, filename);
    }

}