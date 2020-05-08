public class TestPlanet {
    public static void main(String[] args) {
        checkPlanet();
    }

    private static void checkPlanet() {
        String imgFileName = "jupiter.gif";
        Planet p = new Planet(1.0, 2.0, 3.0, 4.0, 5.0, imgFileName);
        Planet q = new Planet(6.0, 7.0, 8.0, 9.0, 10.0, imgFileName);
        System.out.println(p.calcDistance(q));
        System.out.println(q.calcDistance(p));
    }
}