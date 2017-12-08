package model.import_export;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
Класс предоставляет методы для экспорта базы данных в строку или файл в формате JSON.
 @see ExportManager
 */
public class JsonExportManager extends ExportManager{

    /**
     * Экземпляр маршализатора.
     */
    private Gson gson;

    /**
     * Создаёт экземпляр менеджера форматируемым или не форматируемым выводом.
     * @param format устанавливает форматировать (true) или не форматировать (false) вывод.
     */
    public JsonExportManager(boolean format) {
        if(format)
            gson = new GsonBuilder().setPrettyPrinting().create();
        else
            gson = new Gson();
    }


    /**
     * Экспортирует список сущностей в файл в формате JSON. Если список пуст то файл не создаётся.
     * @param object список сущностей для экспорта.
     * @param path путь к файлу.
     * @throws IOException возникает в случае ошибки доступа к файлу или записи в файл.
     */
    @Override
    public void exportToFile(Object object, String path) throws IOException {
        if(!(object instanceof List) || ((List) object).size() != 0){
            FileWriter fileWriter = new FileWriter(path);
            try {
                gson.toJson(object, fileWriter);
            }catch (JsonIOException e){
                throw new IOException(e);
            }
            fileWriter.flush();
            fileWriter.close();
        }
    }

    /**
     * Экспортирует список сущностей в строку в формате JSON. Если список пуст то возвращает null;
     * @param object список сущностей для экспорта.
     * @return возвращает строку в формате JSON или null если список пуст.
     */
    @Override
    public String exportToString(Object object){
        if(!(object instanceof List) || ((List) object).size() != 0)
            return gson.toJson(object);
        return null;
    }
}