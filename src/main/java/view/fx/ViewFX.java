package view.fx;

import event.Event;
import event.EventObjectImpl;
import event.UserRequestSelectListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.DrugEntity;
import model.entities.DrugstoreEntity;
import model.entities.TherapeuticEffectEntity;
import model.import_export.ExportManager;
import model.import_export.JsonExportManager;
import view.View;
import model.dao.DAOException;
import model.dao.TEffectDAOImpl;
import model.import_export.*;

import model.interfaces.TEffectDAO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewFX implements View, Initializable {


    @FXML
    private TableView table_drugs;
    @FXML
    private TableView table_drugstores;
    private UserRequestSelectListener selectListener;
    private List<DrugEntity> drugsTable;
    private List<DrugstoreEntity> drugstoresTable;
    private static Stage stage;

    public static void setStage(Stage stage) {
        ViewFX.stage = stage;
    }

    @Override
    public void setUserRequestSelectListener(UserRequestSelectListener userRequestSelectListener) {
        selectListener = userRequestSelectListener;
    }

    @Override
    public void displayDrugs(List<DrugEntity> drugs) {
        drugsTable = drugs;
        TableView<Drug> drugTable = (TableView<Drug>) table_drugs;
        ObservableList<Drug> data = drugTable.getItems();
        data.clear();

        LinkedList<Drug> drugList = Mapper.fromAll(drugs);

        data.addAll(drugList);
    }

    @Override
    public void displayDrugstores(List<DrugstoreEntity> drugstores) {
        drugstoresTable = drugstores;
        TableView<Drugstore> drugstoresTable = (TableView<Drugstore>) table_drugstores;
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
        showAllDrugs();
        showAllDrugstores();
    }


    @FXML
    public void showAllDrugs() {
        EventObjectImpl<Object> eo = new EventObjectImpl<>(null, Event.GET_ALL_DRUGS);
        selectListener.actionPerfomed(eo);
    }

    @FXML
    public void showAllDrugstores() {
        EventObjectImpl<Object> eo = new EventObjectImpl<>(null, Event.GET_ALL_DRUGSTORE);
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

    public void deleteDrug(){
        int index = table_drugs.getSelectionModel().getSelectedIndex();
        if(index!=-1){
            EventObjectImpl<DrugEntity> eo = new EventObjectImpl<>(drugsTable.get(index), Event.DELETE_DRUG);
            selectListener.actionPerfomed(eo);
        }
    }

    public void deleteDrugstore(){
        int index = table_drugstores.getSelectionModel().getSelectedIndex();
        if(index!=-1){
            EventObjectImpl<DrugstoreEntity> eo = new EventObjectImpl<>(drugstoresTable.get(index), Event.DELTE_DRUGSTORE);
            selectListener.actionPerfomed(eo);
        }
    }

    public void addDrug() {
    }

    public void showExportWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/export_window.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage exportWindow = new Stage();
            exportWindow.setScene(new Scene(root));
            exportWindow.initModality(Modality.WINDOW_MODAL);
            exportWindow.initOwner(stage);
            exportWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeDrugstoreTable();
        initializeDrugTable();
    }

    private void initializeDrugTable(){
        table_drugs.getColumns().clear();
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

        table_drugs.getColumns().addAll(nameCol, formCol, manufacturerCol, ingredientCol, pEffectCol, tEffectCol, descCol);

    }

    private void initializeDrugstoreTable(){
        table_drugstores.getColumns().clear();

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

        table_drugstores.getColumns().addAll(nameCol, districtCol, addressCol, phoneCol, hoursCol, boolCol);
    }
}
