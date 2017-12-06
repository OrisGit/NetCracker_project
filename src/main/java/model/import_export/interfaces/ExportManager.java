package model.import_export.interfaces;

import java.io.IOException;

public interface ExportManager {
    void exportToFile(Object object, String path) throws IOException;
    String exportToString(Object object);
}
