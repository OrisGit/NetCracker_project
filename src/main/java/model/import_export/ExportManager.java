package model.import_export;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public abstract class ExportManager<T> {
    protected static  final Logger logger = Logger.getLogger("ExportManager");
    public abstract void exportToFile(String path, List<T> entities) throws ExportException;
    public abstract String exportToString(List<T> entities) throws ExportException;
}
