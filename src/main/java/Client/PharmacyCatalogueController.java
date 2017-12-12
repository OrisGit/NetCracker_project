package Client;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class PharmacyCatalogueController {
    @FXML
    private ToggleButton selector;
    @FXML
    private TextField name;
    @FXML
    private TextField param;
    @FXML
    private Button searchBtn;
    @FXML
    private Button showAllBtn;
    @FXML
    private AnchorPane tableContainer;


    @FXML
    public void update() {
        if (selector.isSelected()) {

            selector.setText("Поиск аптек");
            name.setPromptText("Название аптеки");
            param.setPromptText("Район");
            searchBtn.setOnAction(event -> displayDrugstore());
            showAllBtn.setOnAction(event -> displayAllDrugstores());

            tableContainer.getChildren().clear();
            TableView<DrugstoreFX> drugstoresList = new TableView<>();
            drugstoresList.setEditable(true);
            drugstoresList.setPrefWidth(600);

            TableColumn<DrugstoreFX, String> nameCol = new TableColumn<>("Название");
            nameCol.setCellValueFactory(
                    cellData -> cellData.getValue().nameProperty());

            TableColumn<DrugstoreFX, String> districtCol = new TableColumn<>("Район");
            districtCol.setCellValueFactory(
                    cellData -> cellData.getValue().districtProperty());

            TableColumn<DrugstoreFX, String> addressCol = new TableColumn<>("Адрес");
            addressCol.setCellValueFactory(
                    cellData -> cellData.getValue().addressProperty());

            TableColumn<DrugstoreFX, String> phoneCol = new TableColumn<>("Телефон");
            phoneCol.setCellValueFactory(
                    cellData -> cellData.getValue().phoneProperty());

            TableColumn<DrugstoreFX, String> hoursCol = new TableColumn<>("Часы работы");
            hoursCol.setCellValueFactory(
                    cellData -> cellData.getValue().workingHoursProperty());

            TableColumn<DrugstoreFX, Boolean> boolCol = new TableColumn<>("Круглосуточная");
            boolCol.setCellValueFactory(
                    cellData -> cellData.getValue().isRoundTheClockProperty());
            boolCol.setCellFactory(
                    column -> {
                        CheckBoxTableCell c = new CheckBoxTableCell<>();
                        c.setDisable(true);
                        return c;
                    });

            drugstoresList.getColumns().addAll(nameCol, districtCol, addressCol, phoneCol, hoursCol, boolCol);
            tableContainer.getChildren().add(drugstoresList);

        } else {

            selector.setText("Поиск лекарств");
            selector.setText("Поиск аптек");
            name.setPromptText("Название лекарства");
            param.setPromptText("Активный ингридиент");
            searchBtn.setOnAction(event -> displayDrug());
            showAllBtn.setOnAction(event -> displayAllDrugs());

            tableContainer.getChildren().clear();
            TableView<DrugFX> drugList = new TableView<>();
            drugList.setEditable(true);
            drugList.setPrefWidth(600);

            TableColumn<DrugFX, String> nameCol = new TableColumn<>("Название");
            nameCol.setCellValueFactory(
                    cellData -> cellData.getValue().nameProperty());

            TableColumn<DrugFX, String> formCol = new TableColumn<>("Форма выпуска");
            formCol.setCellValueFactory(
                    cellData -> cellData.getValue().releaseFormProperty());

            TableColumn<DrugFX, String> manufacturerCol = new TableColumn<>("Производитель");
            manufacturerCol.setCellValueFactory(
                    cellData -> cellData.getValue().manufacturerProperty());

            TableColumn<DrugFX, String> ingredientCol = new TableColumn<>("Активный ингредиент");
            ingredientCol.setCellValueFactory(
                    cellData -> cellData.getValue().activeIngredientProperty());

            TableColumn<DrugFX, String> pEffectCol = new TableColumn<>("Фармакологический эффект");
            pEffectCol.setCellValueFactory(
                    cellData -> cellData.getValue().pharmacologicalEffectProperty());

            TableColumn<DrugFX, String> tEffectCol = new TableColumn<>("Терапевтический эффект");
            tEffectCol.setCellValueFactory(
                    cellData -> cellData.getValue().therapeuticEffectProperty());

            TableColumn<DrugFX, String> descCol = new TableColumn<>("Описание");
            descCol.setCellValueFactory(
                    cellData -> cellData.getValue().descriptionProperty());

            drugList.getColumns().addAll(nameCol, formCol, manufacturerCol, ingredientCol, pEffectCol, tEffectCol, descCol);
            tableContainer.getChildren().add(drugList);
        }
    }

    public void displayAllDrugs() {
        TableView<DrugFX> drugTable = (TableView<DrugFX>) tableContainer.getChildren().get(0);
        ObservableList<DrugFX> data = drugTable.getItems();

        ArrayList<DrugFX> drugList = new ArrayList<>();
        drugList.add(new DrugFX("name", "releaseForm", "manufacturer", "activeIngredient",
                "pharmacologicalEffect", "therapeuticEffect", "descriptionLink"));
        drugList.add(new DrugFX("name", "releaseForm", "manufacturer", "activeIngredient",
                "pharmacologicalEffect", "therapeuticEffect", "descriptionLink"));

        data.clear();
        data.addAll(drugList);
    }

    public void displayDrug() {
        if (!name.getText().isEmpty() || !param.getText().isEmpty()) {
            TableView<DrugFX> drugTable = (TableView<DrugFX>) tableContainer.getChildren().get(0);
            ObservableList<DrugFX> data = drugTable.getItems();

            ArrayList<DrugFX> drugList = new ArrayList<>();
            drugList.add(new DrugFX(name.getText(), "releaseForm", "manufacturer", param.getText(),
                    "pharmacologicalEffect", "therapeuticEffect", "descriptionLink"));

            data.clear();
            data.addAll(drugList);
        }
    }

    public void displayAllDrugstores() {
        TableView<DrugstoreFX> drugstoresTable = (TableView<DrugstoreFX>) tableContainer.getChildren().get(0);
        ObservableList<DrugstoreFX> data = drugstoresTable.getItems();

        ArrayList<DrugstoreFX> drugstoreList = new ArrayList<>();
        drugstoreList.add(new DrugstoreFX("name", "district", "street", "building", "phone", "working hours", true));
        drugstoreList.add(new DrugstoreFX("name", "district", "street", "building", "phone", "working hours", false));

        data.clear();
        data.addAll(drugstoreList);
    }

    public void displayDrugstore() {
        if (!name.getText().isEmpty() || !param.getText().isEmpty()) {
            TableView<DrugstoreFX> drugstoresTable = (TableView<DrugstoreFX>) tableContainer.getChildren().get(0);
            ObservableList<DrugstoreFX> data = drugstoresTable.getItems();

            ArrayList<DrugstoreFX> drugstoreList = new ArrayList<>();
            drugstoreList.add(new DrugstoreFX(name.getText(), param.getText(), "street", "building", "phone",
                    "working hours", true));

            data.clear();
            data.addAll(drugstoreList);
        }
    }
}
