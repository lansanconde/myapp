package monapp;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "params", eager = false)
@ApplicationScoped
public class ApplicationParameters {

    Map<String, String> levels = new LinkedHashMap<String, String>();

    @PostConstruct
    void init() {
        levels.put("--", "");
        levels.put("Débutant", "D");
        levels.put("Introduction", "I");
        levels.put("Avancé", "A");
        levels.put("Expert", "E");
        System.out.println("Init " + this);
    }

    public Map<String, String> getLevels() {
        return levels;
    }

}