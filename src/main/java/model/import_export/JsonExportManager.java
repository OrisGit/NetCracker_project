package model.import_export;

import com.google.gson.Gson;
import model.import_export.interfaces.ExportManager;

import java.io.FileWriter;
import java.io.IOException;

public class JsonExportManager implements ExportManager{

    private Gson gson;

    public JsonExportManager() {
        gson = new Gson();
    }

    @Override
    public void exportToFile(Object object, String path) throws IOException {
        gson.toJson(object, new FileWriter(path));
    }

    @Override
    public String exportToString(Object object){
        return gson.toJson(object);
    }
}
