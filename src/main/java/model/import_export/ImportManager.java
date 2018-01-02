package model.import_export;

import model.dao.DAO;
import model.dao.DAOException;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;

public abstract class ImportManager<T> {

    protected final static Logger logger = Logger.getLogger("ImportManager");
    protected DAO<T> dao;

    public ImportManager(DAO<T> dao) {
        this.dao = dao;
    }

    public abstract void importFromFile(String path) throws ImportException;

    public abstract void importFromString(String str) throws ImportException;

    protected void importEntities(List<T> entities){
        for(T entity : entities){
            try {
                dao.replicate(entity);
            } catch (DAOException e) {
                logger.warning("Не возможно выполнить импорт сущности т.к. возникла ошибка при добавлении сушности в базу данных: "+e);
            }
        }
    }
}
