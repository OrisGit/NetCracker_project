package model.import_export;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "entities")
public class Entities{
    @XmlElement(name = "entity")
    private List<Object> entities;

    public Entities() {
    }

    public Entities(List<Object> entities) {
        this.entities = entities;
    }

    public List<Object> getEntities() {
        return entities;
    }
}