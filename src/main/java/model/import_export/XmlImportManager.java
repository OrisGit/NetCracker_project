package model.import_export;

import model.dao.DAO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.lang.reflect.Type;

public class XmlImportManager<T> extends ImportManager<T> {

    public XmlImportManager(DAO dao, Class<?> clazz) {
        super(dao, clazz);
    }

    @Override
    public void importEntityFromFile(String path) throws FileNotFoundException {
        File file = new File(path);
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object object = unmarshaller.unmarshal(file);
            importEntity(object);
        } catch (JAXBException e) {
            logger.warning("Ошибка при импорте из xml файла: "+e.getMessage());
        }
    }

    @Override
    public void importEntityFromString(String xmlString) {
        StringReader sr = new StringReader(xmlString);
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object object = unmarshaller.unmarshal(sr);
            importEntity(object);
        } catch (JAXBException e) {
            logger.warning("Ошибка при импорте из xml файла: "+e.getMessage());
        }
    }

    @Override
    public void importListFromFile(String path, Type type) {
        File file = new File(path);
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Wrapper wrapper = (Wrapper)unmarshaller.unmarshal(file);
            importEntity(wrapper.getEntities());
        } catch (JAXBException e) {
            logger.warning("Ошибка при импорте из xml файла: "+e.toString());
        }
    }

    @Override
    public void importListFromString(String xmlString, Type type) {
        StringReader sr = new StringReader(xmlString);
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class,clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Wrapper wrapper = (Wrapper)unmarshaller.unmarshal(sr);
            importEntities(wrapper.getEntities());
        } catch (JAXBException e) {
            logger.warning("Ошибка при импорте из xml файла: "+e.getMessage());
        }
    }
}
