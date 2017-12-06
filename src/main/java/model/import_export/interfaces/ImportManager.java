package model.import_export.interfaces;

import model.dao.DAO;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;

public interface ImportManager {

    void importEntityFromFile(String path) throws FileNotFoundException;

    void importEntityFromString(String jsonString);

    void importListFromFile(String path, Type type);

    void importListFromString(String jsonString, Type type);
}
