package myapp.cdi;
import javax.enterprise.inject.Produces;

public class HomeFactory {

    @Produces
    @Place(country = "USA", town = "New-York")
    public Home franceHome() {
        Home home = new Home();
        home.setPlace("USA, New-York");
        System.err.println("produces ... " + home);
        return home;
    }

    @Produces
    @Place(country = "Chine", town = "Pekin")
    public Home chinaHome() {
        Home home = new Home();
        home.setPlace("Pekin, Chine");
        System.err.println("produces ... " + home);
        return home;
    }

}