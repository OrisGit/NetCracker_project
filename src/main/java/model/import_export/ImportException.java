package model.import_export;

public class ImportException extends Exception{
    public ImportException(Exception e) {
        super("Ошибка импорта", e);
    }

    public ImportException(String message) {
        super(message);
    }
}
