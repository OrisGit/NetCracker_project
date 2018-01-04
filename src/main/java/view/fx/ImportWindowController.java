package view.fx;

import event.Event;
import event.EventObject;
import event.EventObjectImpl;
import event.UserRequestSelectListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.import_export.FormatType;
import model.import_export.marshalling.UnmarshallingException;
import model.import_export.marshalling.AbstractUnmarshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ImportWindowController implements Initializable {

    @FXML
    public TextField text_path;
    private File file;
    static Stage STAGE;
    static UserRequestSelectListener LISTENER;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void clickFileChooseBtn(){
        FileChooser fc = new FileChooser();
        file = fc.showOpenDialog(STAGE);
        if(file == null){
            text_path.setText("Пожалуйста выберите файл");
        }else{
            text_path.setText(file.getAbsolutePath());
        }
    }

    @FXML
    public void clickImportBtn() throws UnmarshallingException {
        FormatType type;
        if(getFileExtension(file).toLowerCase().equals("json")){
            type = FormatType.JSON;
        }else if(getFileExtension(file).toLowerCase().equals("xml")){
            type = FormatType.XML;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Не верное расширение файла");
            alert.show();
            return;
        }

        byte[] bytes = null;
        String data = null;
        try {
            bytes = Files.readAllBytes(file.toPath());
            data = new String(bytes,"UTF-8");
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Не возможно считать данные из файла");
            alert.show();
            return;
        }

        Map<String, Object> prop = new HashMap<>();
        prop.put("data",data);
        prop.put("type",type);

        EventObject<Map<String,Object>> eo = new EventObjectImpl<>(prop, Event.IMPORT);
        LISTENER.actionPerfomed(eo);
        STAGE.close();
    }


    private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
