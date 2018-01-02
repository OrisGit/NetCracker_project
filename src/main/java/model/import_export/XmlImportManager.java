package model.import_export;

import model.dao.DAO;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * Класс-менеджер предоставляющий методы для импорта сущностей из файла или строки в формате XML.
 * @param <T> тип импортируемой сущности
 */
public class XmlImportManager<T> extends ImportManager<T> {

    /**
     * Экземпляр демаршализатора.
     */
    private Unmarshaller unmarshaller;

    /**
     * Класс демаршализуемого объекта.
     */
    private Class<T> clazz;

    /**
     * Конструктор принимаюший DAO менеджер определённой сущности и класс демаршализуемого объекта.
     * @param dao DAO менеджер сушности.
     * @param clazz класс демаршализуемого объекта.
     */
    public XmlImportManager(DAO<T> dao, Class<T> clazz) {
        super(dao);
        this.clazz = clazz;
    }

    /**
     * Импортирует список сущностей из файла в формате XML.
     * @param path путь к файлу.
     * @throws ImportException ошибка импорта.
     */
    @Override
    public void importFromFile(String path) throws ImportException {
        StreamSource streamSource = new StreamSource(path);
        xmlImport(streamSource);
    }


    /**
     * Импортирует список сущностей из строки в формате XML.
     * @param xmlString строка в формате JSON.
     * @throws ImportException ошибка импорта.
     */
    @Override
    public void importFromString(String xmlString) throws ImportException {
        StreamSource streamSource = new StreamSource(new ByteArrayInputStream(xmlString.getBytes(Charset.defaultCharset())));
        xmlImport(streamSource);
    }


    /**
     * Импортирует список сущностей из ресурса в формате XML.
     * @param streamSource ресурс.
     * @throws ImportException ошибка импорта.
     */
    private void xmlImport(StreamSource streamSource) throws ImportException {
        try {
            JAXBElement<Wrapper> jaxbElement = getUnmarshaller().unmarshal(streamSource, Wrapper.class);
            try {
                Wrapper<T> wrapper = (Wrapper<T>) jaxbElement.getValue();
                importEntities(wrapper.getEntities());
            }catch (ClassCastException e){
                logger.warning("Не возможно выполнить импорт из XML ресурса т.к. содержимое ресурса не " +
                        "соответсвует формату XML или формату демаршализуемой сущности: "+e);
                throw new ImportException("Не возможно выполнить импорт из XML ресурса т.к. содержимое ресурса не " +
                        "соответсвует формату XML или формату демаршализуемой сущности.",e);
            }
        } catch (JAXBException e) {
            logger.warning("Не возможно выполнить импорт из XML ресурса из-за ошибки демаршализатора: "+e);
            throw new ImportException("Не возможно выполнить импорт из XML ресурса из-за ошибки демаршализатора.",e);
        }
    }


    /**
     * Создаёт (если еще не создан) и возвращает Unmarshaller применяя к нему настроийки в соответствии с
     * переданными в конструкторе параметрами.
     * @return демаршализатор с установленными параметрами.
     * @throws ImportException ошибка импорта.
     */
    private Unmarshaller getUnmarshaller() throws ImportException {
        if(unmarshaller==null){
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Wrapper.class, clazz);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                return this.unmarshaller = unmarshaller;
            } catch (JAXBException e) {
                logger.warning("Не возможно выполнить импорт из XML из-за ошибки создания демаршализатора: "+e.getMessage());
                throw new ImportException("Не возможно выполнить экспорт из XML из-за ошибки создания демаршализатора.", e);
            }
        }
        return unmarshaller;
    }
}
