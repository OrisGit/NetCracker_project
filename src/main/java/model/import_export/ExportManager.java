package model.import_export;

import java.io.IOException;
import java.util.logging.Logger;

public abstract class ExportManager {
    protected static  final Logger logger = Logger.getLogger("ExportManager");
    public abstract void exportToFile(Object object, String path) throws IOException;
    public abstract String exportToString(Object object);
}
