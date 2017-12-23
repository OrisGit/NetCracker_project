package view.fx;

import event.Event;
import event.EventObjectImpl;
import event.UserRequestSelectListener;
import event.UserRequestUpdateListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;
import model.entities.DrugEntity;
import model.entities.DrugstoreEntity;
import model.entities.TherapeuticEffectEntity;
import model.import_export.ExportManager;
import model.import_export.JsonExportManager;
import parameters.ParametersBuilder;
import view.View;
import model.dao.DAOException;
import model.dao.TEffectDAOImpl;
import model.import_export.*;

import model.interfaces.TEffectDAO;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ViewFX implements View {

    private UserRequestSelectListener selectListener;
    private UserRequestUpdateListener updateListener;

    @FXML private ToggleButton selector;
    @FXML private TextField name;
    @FXML private TextField param;
    @FXML private Button searchBtn;
    @FXML private Button showAllBtn;
    @FXML private Button XMLExportBtn;
    @FXML private Button JSONExportBtn;
    @FXML private Button addBtn;
    @FXML private Button deleteBtn;
    @FXML private Button updateBtn;
    @FXML private TableView table;


    @Override
    public void setUserRequestSelectListener(UserRequestSelectListener userRequestSelectListener) {
        this.selectListener = selectListener;
    }

    @Override
    public void displayDrugs(List<DrugEntity> drugs) {
        TableView<Drug> drugTable = (TableView<Drug>) table;
        ObservableList<Drug> data = drugTable.getItems();
        data.clear();

        LinkedList<Drug> drugList = Mapper.fromAll(drugs);

        data.addAll(drugList);
    }

    @Override
    public void displayPharmacies(List<DrugstoreEntity> drugstores) {
        TableView<Drugstore> drugstoresTable = (TableView<Drugstore>) table;
        ObservableList<Drugstore> data = drugstoresTable.getItems();
        data.clear();

        LinkedList<Drugstore> drugstoreList = Mapper.fromAll(drugstores);

        data.addAll(drugstoreList);
    }

    @Override
    public void displayError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void run() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewTest.class.getResource("/pharmacy_catalogue.fxml"));
        Parent root;
        try {
            root = loader.load();
            loader.setController(this);
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void update() {
        if (selector.isSelected()) {

            selector.setText("Поиск аптек");
            name.setPromptText("Название аптеки");
            param.setPromptText("Район");
            searchBtn.setOnAction(event -> showPharmacies());
            showAllBtn.setOnAction(event -> showAllPharmacies());
            addBtn.setOnAction(event -> addDrug());
            //deleteBtn.setOnAction(event -> deleteDrug());
            //updateBtn.setOnAction(event -> updateDrug());

            table.getColumns().clear();
            //todo сократить добавление столбцов
            TableColumn<Drugstore, String> nameCol = new TableColumn<>("Название");
            nameCol.setCellValueFactory(
                    cellData -> cellData.getValue().nameProperty());

            TableColumn<Drugstore, String> districtCol = new TableColumn<>("Район");
            districtCol.setCellValueFactory(
                    cellData -> cellData.getValue().districtProperty());

            TableColumn<Drugstore, String> addressCol = new TableColumn<>("Адрес");
            addressCol.setCellValueFactory(
                    cellData -> cellData.getValue().addressProperty());

            TableColumn<Drugstore, String> phoneCol = new TableColumn<>("Телефон");
            phoneCol.setCellValueFactory(
                    cellData -> cellData.getValue().phoneProperty());

            TableColumn<Drugstore, String> hoursCol = new TableColumn<>("Часы работы");
            hoursCol.setCellValueFactory(
                    cellData -> cellData.getValue().workingHoursProperty());

            TableColumn<Drugstore, Boolean> boolCol = new TableColumn<>("Круглосуточная");
            boolCol.setCellValueFactory(
                    cellData -> cellData.getValue().isRoundTheClockProperty());
            boolCol.setCellFactory(
                    column -> {
                        CheckBoxTableCell c = new CheckBoxTableCell<>();
                        c.setDisable(true);
                        return c;
                    });

            table.getColumns().addAll(nameCol, districtCol, addressCol, phoneCol, hoursCol, boolCol);

        } else {

            selector.setText("Поиск лекарств");
            name.setPromptText("Название лекарства");
            param.setPromptText("Активный ингридиент");
            searchBtn.setOnAction(event -> showDrugs());
            showAllBtn.setOnAction(event -> showAllDrugs());
            //addBtn.setOnAction(event -> addDrugstore());
            //deleteBtn.setOnAction(event -> deleteDrugstore());
            //updateBtn.setOnAction(event -> updateDrugstore());

            table.getColumns().clear();

            TableColumn<Drug, String> nameCol = new TableColumn<>("Название");
            nameCol.setCellValueFactory(
                    cellData -> cellData.getValue().nameProperty());

            TableColumn<Drug, String> formCol = new TableColumn<>("Форма выпуска");
            formCol.setCellValueFactory(
                    cellData -> cellData.getValue().releaseFormProperty());

            TableColumn<Drug, String> manufacturerCol = new TableColumn<>("Производитель");
            manufacturerCol.setCellValueFactory(
                    cellData -> cellData.getValue().manufacturerProperty());

            TableColumn<Drug, String> ingredientCol = new TableColumn<>("Активный ингредиент");
            ingredientCol.setCellValueFactory(
                    cellData -> cellData.getValue().activeIngredientProperty());

            TableColumn<Drug, String> pEffectCol = new TableColumn<>("Фармакологический эффект");
            pEffectCol.setCellValueFactory(
                    cellData -> cellData.getValue().pharmacologicalEffectProperty());

            TableColumn<Drug, String> tEffectCol = new TableColumn<>("Терапевтический эффект");
            tEffectCol.setCellValueFactory(
                    cellData -> cellData.getValue().therapeuticEffectProperty());

            TableColumn<Drug, String> descCol = new TableColumn<>("Описание");
            descCol.setCellValueFactory(
                    cellData -> cellData.getValue().descriptionProperty());

            table.getColumns().addAll(nameCol, formCol, manufacturerCol, ingredientCol, pEffectCol, tEffectCol, descCol);
        }
    }

    @FXML
    public void showDrugs() {
        if (!name.getText().isEmpty() || !param.getText().isEmpty()) {

            ParametersBuilder parameters = new ParametersBuilder();
            parameters.setDrugName((name.getText()));
            parameters.setDrugActiveIngredient(param.getText());

            EventObjectImpl eo = new EventObjectImpl(parameters.createParameters(), Event.GET_DRUGS_WITH_PARAMETERS);
            selectListener.actionPerfomed(eo);
        }
    }

    @FXML
    public void showPharmacies() {
        if (!name.getText().isEmpty() || !param.getText().isEmpty()) {

            ParametersBuilder parameters = new ParametersBuilder();
            parameters.setDrugstoreName((name.getText()));
            parameters.setDrugstoreAddressDistrict(param.getText());

            EventObjectImpl eo = new EventObjectImpl(parameters.createParameters(), Event.GET_DRUGSTORE_WITH_PARAMETERS);
            selectListener.actionPerfomed(eo);
        }
    }

    @FXML
    public void showAllDrugs() {
        EventObjectImpl eo = new EventObjectImpl(null, Event.GET_ALL_DRUGS);
        selectListener.actionPerfomed(eo);
    }

    @FXML
    public void showAllPharmacies() {
        EventObjectImpl eo = new EventObjectImpl(null, Event.GET_ALL_DRUGSTORE);
        selectListener.actionPerfomed(eo);
    }

    @FXML
    public void exportToJSON(){
        ExportManager<TherapeuticEffectEntity> exportManager = new JsonExportManager<>(true);
        TEffectDAO daoManager = new TEffectDAOImpl();
        //TODO проверить файл
        File file = new File("E:/export.json");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            exportManager.exportToFile("E:/export.json",daoManager.getAll());
        } catch (ExportException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exportToXML(){
        ExportManager<TherapeuticEffectEntity> exportManager = new XmlExportManager<>(TherapeuticEffectEntity.class,true);
        TEffectDAO daoManager = new TEffectDAOImpl();
        //TODO проверить файл
        File file = new File("E:/export.xml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            exportManager.exportToFile("E:/export.xml",daoManager.getAll());
        } catch (ExportException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void addDrug() {
        //show dialogue
        //get entity from dialogue
        //add

        EventObjectImpl eo = new EventObjectImpl(new DrugEntity(), Event.ADD_DRUG);
        selectListener.actionPerfomed(eo);
    }
}
