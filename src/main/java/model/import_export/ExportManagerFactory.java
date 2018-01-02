package model.import_export;

public class ExportManagerFactory<T>{

    private Object entity;
    public ExportManagerFactory(Object entity) {
        this.entity = entity;
    }

    public ExportManager getExportManager(FormatType type, boolean format){
        if(type.equals(FormatType.XML)){
            return new XmlExportManager<T>(entity.getClass(),format);
        }else{
            return new JsonExportManager<T>(format);
        }
    }
}
