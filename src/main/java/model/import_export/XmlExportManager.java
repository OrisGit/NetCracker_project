package model.import_export;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.StringWriter;
import java.util.List;

/**
 * Класс-менеджер предоставляющий методы для экспорта сущностей в файл или строку в формате XML.
 * @param <T> тип экспортируемой сущности
 */
public class XmlExportManager<T> extends ExportManager<T>{

    /**
     * Экземпляр маршализатора.
     */
    private Marshaller marshaller;
    /**
     * Класс маршализуемого объекта.
     */
    private Class<?> clazz;
    /**
     * Указывает применять ли форматирование к выходной строке.
     */
    private boolean format;

    /**
     * Создаёт экземпляр менеджера c форматируемым или не форматируемым выводом.
     *
     * @param clazz Класс маршализуемого объекта.
     * @param format устанавливает форматировать (true) или не форматировать (false) вывод.
     */
    public XmlExportManager(Class<?> clazz, boolean format) {
        this.clazz = clazz;
        this.format = format;
    }

    /**
     * Экспортирует список сущностей в файл в формате XML. Если список пуст то файл не создаётся.
     * @param entities список сущностей для экспорта.
     * @param path путь к файлу.
     * @throws ExportException ошибка экспорта.
     */
    @Override
    public void exportToFile(String path, List<T> entities) throws ExportException {
        if(entities.size()!=0){
            File file = new File(path);
            try {
                getMarshaller().marshal(getJAXBElement(entities),file);
            } catch (JAXBException e) {
                logger.warning("Не возможно выполнить экспорт в XML файл из-за ошибки маршализатора: "+e);
                throw new ExportException("Не возможно выполнить экспорт в XML файл из-за ошибки маршализатора",e);
            }
        }
    }

    /**
     * Экспортирует список сущностей в строку в формате XML. Если список пуст то возвращает null;
     * @param entities список сущностей для экспорта.
     * @return возвращает строку в формате JSON или null если список пуст.
     * @exception ExportException ошибка экспорта.
     */
    @Override
    public String exportToString(List<T> entities) throws ExportException {
        if(entities.size()!=0){
            StringWriter sw = new StringWriter();
            try {
                getMarshaller().marshal(getJAXBElement(entities),sw);
                return sw.toString();
            } catch (JAXBException e) {
                logger.warning("Не возможно выполнить экспорт в XML строку из-за ошибки маршализатора: "+e);
                throw new ExportException("Не возможно выполнить экспорт в XML строку из-за ошибки маршализатора.",e);
            }
        }
        return null;
    }

    /**
     * Возвращает форматированный JAXB элемент для записи.
     * @param entities список сущностей для экспорта.
     * @return форматированный JAXB элемент для записи.
     */
    private JAXBElement<Wrapper> getJAXBElement(List<T> entities){
        QName qName = new QName("Entities");
        Wrapper<T> wrapper = new Wrapper<>(entities);
        JAXBElement<Wrapper> jaxbElement = new JAXBElement<>(qName,Wrapper.class,wrapper);
        return jaxbElement;
    }

    /**
     * Создаёт (если еще не создан) и возвращает Marshaller применяя к нему настроийки в соответствии с
     * переданными в конструкторе параметрами.
     * @return маршализатор с установленными параметрами.
     * @throws ExportException ошибка экспорта.
     */
    private Marshaller getMarshaller() throws ExportException {
        if(marshaller==null){
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Wrapper.class, clazz);
                Marshaller marshaller = jaxbContext.createMarshaller();
                if(format)
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
                marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                return this.marshaller = marshaller;
            } catch (JAXBException e) {
                logger.warning("Не возможно выполнить экспорт в XML из-за ошибки создания маршализатора: "+e.getMessage());
                throw new ExportException("Не возможно выполнить экспорт в XML из-за ошибки создания маршализатора.", e);
            }
        }
        return marshaller;
    }
}
