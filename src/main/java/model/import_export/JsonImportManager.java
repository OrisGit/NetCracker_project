package model.import_export;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import model.dao.DAO;
import model.dao.DAOException;
import model.import_export.interfaces.ImportManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class JsonImportManager<T> implements ImportManager {

    private Gson gson;
    private DAO dao;
    private Class<Object> clazz;
    private final static Logger logger = Logger.getLogger("JsonImportManager");

    public JsonImportManager(DAO dao, Class<Object> clazz) {
        this.dao = dao;
        this.clazz = clazz;
        gson = new Gson();
    }

    @Override
    public void importEntityFromFile(String path){
        try{
            Object obj = gson.fromJson(new FileReader(path),clazz);
            importEntity(obj);
        }catch (JsonSyntaxException e){
            logger.warning("Информация в файле не соответствует формату JSON: "+e.getMessage());
        }catch (JsonIOException e){
            logger.warning("Ошибка ввода вывода: "+e.getMessage());
        } catch (FileNotFoundException e) {
            logger.warning(String.format("Файл по пути: %s не найден", path));
        }

    }

    @Override
    public void importEntityFromString(String jsonString){
        try{
            Object obj = gson.fromJson(jsonString,clazz);
            importEntity(obj);
        }catch (JsonSyntaxException e){
            logger.warning("Информация в файле не соответствует формату JSON: "+e.getMessage());
        }
    }

    @Override
    public void importListFromFile(String path, Type type){
        try{
            List<T> objects = gson.fromJson(new FileReader(path), type);
            importEntities(objects);
        }catch (JsonSyntaxException e){
            logger.warning("Информация в файле не соответствует формату JSON: "+e.getMessage());
        }catch (JsonIOException e){
            logger.warning("Ошибка ввода вывода: "+e.getMessage());
        } catch (FileNotFoundException e) {
            logger.warning(String.format("Файл по пути: %s не найден", path));
        }
    }

    @Override
    public void importListFromString(String jsonString, Type type){
        try{
            List<T> objects = gson.fromJson(jsonString, type);
            importEntities(objects);
        }catch (JsonSyntaxException e){
            logger.warning("Информация в файле не соответствует формату JSON: "+e.getMessage());
        }
    }

    private void importEntities(List<T> entities){
        for(Object entity : entities){
            importEntity(entity);
        }
    }

    private void importEntity(Object entity){
        try {
            dao.replicate(entity);
        } catch (DAOException e) {
            logger.warning("Ошибка во время импорта массива объектов: "+e.getMessage());
        }
    }
}
