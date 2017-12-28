package model.import_export;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Класс-менеджер предоставляющий методы для экспорта сущностей в файл или строку в формате JSON.
 * @param <T> тип экспортируемой сущности
 */
public class JsonExportManager<T> extends ExportManager<T>{

    /**
     * Экземпляр маршализатора.
     */
    private Gson gson;

    /**
     * Создаёт экземпляр менеджера c форматируемым или не форматируемым выводом.
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
     * @param entities список сущностей для экспорта.
     * @param path путь к файлу.
     * @throws ExportException ошибка экспорта.
     */
    @Override
    public void exportToFile(String path, List<T> entities) throws ExportException {
        if(entities.size()!=0){
            try {
                FileWriter fileWriter = new FileWriter(path);
                gson.toJson(entities, fileWriter);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                logger.warning("Не возможно выполнить экспорт в JSON файл из-за ошибки ввода-вывода: "+e);
                throw new ExportException("Не возможно выполнить экспорт в JSON файл из-за ошибки ввода-вывода.",e);
            }
        }
    }

    /**
     * Экспортирует список сущностей в строку в формате JSON. Если список пуст то возвращает null;
     * @param entities список сущностей для экспорта.
     * @return возвращает строку в формате JSON или null если список пуст.
     */
    @Override
    public String exportToString(List<T> entities){
        if(entities.size()!=0)
            return gson.toJson(entities);
        return null;
    }
}