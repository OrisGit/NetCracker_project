package view.fx;

import event.Event;
import event.EventObject;
import event.EventObjectImpl;
import event.UserRequestSelectListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.dao.*;
import model.import_export.marshalling.MarshallingException;
import model.import_export.FormatType;
import view.View;


import java.io.File;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;

public class ExportWindowController implements Initializable{

    static Stage STAGE;
    static UserRequestSelectListener LISTENER;
    private File path;
    static View STUB;

    @FXML
    public TextField text_path;
    @FXML
    public RadioButton rbtn_json;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text_path.setText("Пожалуйста выберите путь");
    }

   @FXML
   public void clickPathSelectBtn(){
       DirectoryChooser dc = new DirectoryChooser();
       path = dc.showDialog(STAGE);
       if(path==null){
           text_path.setText("Пожалуйста выберите путь");
       }else{
           text_path.setText(path.getAbsolutePath());
       }
   }

   @FXML
   public void clickExportBtn() throws DAOException, MarshallingException {

       if(text_path.getText().equals("Пожалуйста выберите путь")){
           clickPathSelectBtn();
           return;
       }

       String fileExt;
       FormatType type;
       if(rbtn_json.isSelected()){
           type = FormatType.JSON;
           fileExt = ".json";
       }else{
           type = FormatType.XML;
           fileExt = ".xml";
       }

       String path = String.format("%s\\%s%s",this.path.getAbsolutePath(),"export_data",fileExt);

       Map<String,Object> prop = new HashMap<>();
       prop.put("path", path);
       prop.put("type", type);
       EventObject<Map<String,Object>> eventObject = new EventObjectImpl<>(prop, Event.EXPORT);
       try {
           LISTENER.actionPerfomed(eventObject, STUB);
       } catch (RemoteException e) {
           e.printStackTrace();
       }

       STAGE.close();
   }
}
