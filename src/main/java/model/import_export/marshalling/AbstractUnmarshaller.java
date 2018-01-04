package model.import_export.marshalling;

import model.import_export.EntityWrapper;

import java.util.logging.Logger;

public abstract class AbstractUnmarshaller {

    protected final static Logger logger = Logger.getLogger("AbstractUnmarshaller");

    public abstract EntityWrapper importFromFile(String path) throws UnmarshallingException;

    public abstract EntityWrapper importFromString(String str) throws UnmarshallingException;
}
