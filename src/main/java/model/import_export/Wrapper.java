package model.import_export;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

public class Wrapper<T> {

    private List<T> entities;

    public Wrapper() {
        entities = new ArrayList<>();
    }

    public Wrapper(List<T> entities) {
        this.entities = entities;
    }

    @XmlAnyElement(lax = true)
    public List<T> getEntities() {
        return entities;
    }
}