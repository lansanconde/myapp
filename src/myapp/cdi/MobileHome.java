package myapp.cdi;

import javax.inject.Named;

@Named("mobile")
@Place(country = "France", town = "Paris")
public class MobileHome extends Home {

    private static final long serialVersionUID = 1L;

    public String getPlace() {
        String place = super.getPlace();
        return "Mobile " + ((place == null) ? "" : place);
    }

}