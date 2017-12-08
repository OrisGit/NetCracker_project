package model.import_export;

import model.dao.DAO;
import model.dao.DAOException;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;

public abstract class ImportManager<T> {

    protected final static Logger logger = Logger.getLogger("ImportManager");
    protected DAO dao;
    protected Class<?> clazz;

    public ImportManager(DAO dao, Class<?> clazz) {
        this.dao = dao;
        this.clazz = clazz;
    }

    public abstract void importEntityFromFile(String path) throws FileNotFoundException;

    public abstract void importEntityFromString(String jsonString);

    public abstract void importListFromFile(String path, Type type);

    public abstract void importListFromString(String jsonString, Type type);

    protected void importEntities(Object ... entities){
        for(Object entity : entities){
            importEntity(entity);
        }
    }

    protected void importEntity(Object entity){
        try {
            dao.replicate(entity);
        } catch (DAOException e) {
            logger.warning("Ошибка во время импорта массива объектов: "+e.getMessage());
        }
    }
}
