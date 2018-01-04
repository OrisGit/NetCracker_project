package view.fx;

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
import java.net.URL;
import java.util.ResourceBundle;

public class ImportWindowController implements Initializable {

    @FXML
    public TextField text_path;
    private File file;
    public static Stage STAGE;

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
        AbstractUnmarshaller abstractUnmarshaller = null;
        if(getFileExtension(file).toLowerCase().equals("ajax")){

        }else if(getFileExtension(file).toLowerCase().equals("xml")){
            abstractUnmarshaller = getImportManager(FormatType.XML);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Не верное расширение файла");
            alert.show();
        }
        ///abstractUnmarshaller.importFromFile(file.getAbsolutePath());
    }

    private AbstractUnmarshaller getImportManager(FormatType type){
        String tableType = null;
        if(type.equals(FormatType.XML)){
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document document = db.parse(file);
                Element root = document.getDocumentElement();
                NodeList children = root.getChildNodes();
                Node child = root.getFirstChild();
                tableType = child.getNodeName();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //TODO дописать
        }

//        switch (tableType){
//            case "drugEntity":
//                return new XmlUnmarshaller<DrugEntity>(new DrugDAOImpl(), DrugEntity.class);
//        }

        return null;
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
