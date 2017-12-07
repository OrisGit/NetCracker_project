package model.import_export;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import model.dao.DAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonImportManager<T> extends ImportManager<T> {

    private Gson gson;

    public JsonImportManager(DAO dao, Class<?> clazz) {
        super(dao,clazz);
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
}
