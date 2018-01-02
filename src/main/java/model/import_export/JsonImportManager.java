package model.import_export;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import model.dao.DAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

/**
 * Класс-менеджер предоставляющий методы для импорта сущностей из файла или строки в формате JSON.
 * @param <T> тип импортируемой сущности
 */
public class JsonImportManager<T> extends ImportManager<T> {

    /**
     * Экземпляр демаршализатора.
     */
    private Gson gson;
    /**
     * Класс массива сущностей.
     */
    private Class<T[]> type;

    /**
     * Конструктор принимаюший DAO менеджер определённой сущности и класс массива сущностей.
     * @param dao DAO менеджер сушности.
     * @param type класс массива сущностей.
     */
    public JsonImportManager(DAO<T> dao, Class<T[]> type) {
        super(dao);
        gson = new Gson();
        this.type = type;
    }

    /**
     * Импортирует список сущностей из файла в формате JSON.
     * @param path путь к файлу.
     * @throws ImportException ошибка импорта.
     */
    @Override
    public void importFromFile(String path) throws ImportException {
        try{
            T[] array = gson.fromJson(new FileReader(path),type);
            if(array!=null){
                List<T> objects = Arrays.asList(array);
                importEntities(objects);
            }
        }catch (JsonSyntaxException e){
            logger.warning("Не возможно выполнить импорт из JSON файла т.к. информация в файле не соответствует формату JSON: "+e);
            throw new ImportException("Не возможно выполнить импорт из JSON файла т.к. информация в файле не соответствует формату JSON.",e);
        }catch (JsonIOException e){
            logger.warning("Не возможно выполнить импорт из JSON файла из-за ошибки ввода-вывода: "+e);
            throw new ImportException("Не возможно выполнить импорт из JSON файла из-за ошибки ввода-вывода.",e);
        } catch (FileNotFoundException e) {
            logger.warning(String.format("Не возможно выполнить импорт из JSON файла т.к. файл по пути: %s не найден: ", path)+e);
            throw new ImportException(String.format("Не возможно выполнить импорт из JSON файла т.к. файл по пути: %s не найден", path),e);
        }
    }

    /**
     * Импортирует список сущностей из строки в формате JSON.
     * @param jsonString строка в формате JSON.
     * @throws ImportException ошибка импорта.
     */
    @Override
    public void importFromString(String jsonString) throws ImportException {
        try{
            T[] array = gson.fromJson(jsonString, type);
            if(array!=null){
                List<T> objects = Arrays.asList(array);
                importEntities(objects);
            }
        }catch (JsonSyntaxException e){
            logger.warning("Не возможно выполнить импорт из JSON строки т.к. информация в файле не соответствует формату JSON: "+e);
            throw new ImportException("Не возможно выполнить импорт из JSON строки т.к. информация в файле не соответствует формату JSON.",e);
        }
    }
}
