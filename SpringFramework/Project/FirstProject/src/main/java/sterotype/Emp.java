package sterotype;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope("prototype")
@Component
public class Emp {
    @Value("23")
    private int id;
    @Value("Name")
    private String name;

    public List<String> getAd() {
        return ad;
    }

    public void setAd(List<String> ad) {
        this.ad = ad;
    }

    @Value("#{ad}")
    private List<String> ad;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
