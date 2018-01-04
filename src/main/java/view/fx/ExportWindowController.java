package view.fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.dao.*;
import model.import_export.marshalling.MarshallingException;
import model.import_export.FormatType;


import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ExportWindowController implements Initializable{

    static Stage STAGE;
    private File path;

    @FXML
    public TextField text_path;
    @FXML
    public ComboBox<String> table_chosser;
    public RadioButton rbtn_json;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> tables = new LinkedList<>();
        tables.add("Препараты");
        tables.add("Аптеки");
        tables.add("Терапевтические эффекты");
        tables.add("Фармакологические эффекты");
        tables.add("Прайс лист");
        table_chosser.getItems().addAll(tables);
        table_chosser.getSelectionModel().select(0);
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

       List listEntity = null;

//       ExportManagerFactory ef = null;
//       String fileName = null;
//       switch (table_chosser.getSelectionModel().getSelectedItem()){
//           case "Препараты":
//               ef = new ExportManagerFactory<DrugEntity>(new DrugEntity());
//               fileName = "drugs";
//               listEntity = new DrugDAOImpl().getAll();
//               break;
//           case "Аптеки":
//               ef = new ExportManagerFactory<DrugstoreEntity>(new DrugstoreEntity());
//               fileName = "drugstores";
//               listEntity = new DrugstoreDAOImpl().getAll();
//               break;
//           case "Терапевтические эффекты":
//               ef = new ExportManagerFactory<TherapeuticEffectEntity>(new TherapeuticEffectEntity());
//               fileName = "TherapeuticEffects";
//               listEntity = new TEffectDAOImpl().getAll();
//               break;
//           case "Фармакологические эффекты":
//               ef = new ExportManagerFactory<PharmachologicEffectEntity>(new PharmachologicEffectEntity());
//               fileName = "PharmachologicEffects";
//               listEntity = new PEffectDAOImpl().getAll();
//               break;
//           case "Прайс лист":
//               ef = new ExportManagerFactory<PriceEntity>(new PriceEntity());
//               fileName = "Price";
//               listEntity = new PriceDAOImpl().getAll();
//               break;
//       }

       String fileExt;
       FormatType type;
       if(rbtn_json.isSelected()){
           type = FormatType.JSON;
           fileExt = ".json";
       }else{
           type = FormatType.XML;
           fileExt = ".xml";
       }

//       AbstractMarshaller em = ef.getExportManager(type,true);
//
//       em.exportToFile(String.format("%s\\%s%s",path.getAbsolutePath(),fileName,fileExt),listEntity);
       STAGE.close();
   }
}
