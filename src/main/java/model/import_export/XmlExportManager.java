package model.import_export;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;

public class XmlExportManager extends ExportManager{

    private Class<?> clazz;

    public XmlExportManager(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void exportToFile(Object object, String path) throws IOException {
        if(object instanceof List<?>){
            object = new Entities((List<Object>)object);
        }
        File file = new File(path);
        try {
            getMarshaller(object.getClass()).marshal(object,file);
        } catch (JAXBException e) {
            logger.warning("Ошибка экспорта в XML файл: "+e.getMessage());
        }
    }

    @Override
    public String exportToString(Object object) {
        if(object instanceof List<?>){
            object = new Entities((List<Object>)object);
        }
        StringWriter sw = new StringWriter();
        try {
            getMarshaller(object.getClass()).marshal(object,sw);
            return sw.toString();
        } catch (JAXBException e) {
            logger.warning("Ошибка экспорта в XML строку: "+e.toString());
            return null;
        }
    }

    public Marshaller getMarshaller(Class<?> object) throws JAXBException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entities.class,object.getClass(),clazz);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            return marshaller;
        } catch (JAXBException e) {
            logger.warning("Ошибка во время создания маршализатора: "+e.getMessage());
            throw e;
        }
    }
}
