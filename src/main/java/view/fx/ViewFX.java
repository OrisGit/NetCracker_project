package view.fx;

import event.Event;
import event.EventObjectImpl;
import event.UserRequestSelectListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.*;
import model.import_export.Importer;
import view.View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ViewFX implements View, Initializable {
    private static Stage STAGE;
    @FXML
    private TableView tableDrugs;
    @FXML
    private TableView tableDrugstores;
    @FXML
    private TableView tablePrices;
    private UserRequestSelectListener selectListener;
    private List<DrugEntity> drugs;
    private List<DrugstoreEntity> drugstores;
    private List<PriceEntity> prices;
    private List<PharmachologicEffectEntity> pharmachologicEffects;
    private List<TherapeuticEffectEntity> therapeuticEffects;

    public static void setStage(Stage stage) {
        ViewFX.STAGE = stage;
    }

    @Override
    public void setUserRequestSelectListener(UserRequestSelectListener userRequestSelectListener) {
        selectListener = userRequestSelectListener;
    }

    @Override
    public void displayDrugs(List<DrugEntity> drugs) {
        this.drugs = drugs;
        ObservableList<Drug> data = tableDrugs.getItems();
        data.clear();

        LinkedList<Drug> drugList = Mapper.fromAll(drugs);

        data.addAll(drugList);
    }

    @Override
    public void displayDrugstores(List<DrugstoreEntity> drugstores) {
        this.drugstores = drugstores;
        ObservableList<Drugstore> data = tableDrugstores.getItems();
        data.clear();

        LinkedList<Drugstore> drugstoreList = Mapper.fromAll(drugstores);

        data.addAll(drugstoreList);
    }

    @Override
    public void displayPrices(List<PriceEntity> prices) {
        this.prices = prices;
        ObservableList<Price> data = tablePrices.getItems();
        data.clear();

        LinkedList<Price> priceList = Mapper.fromAll(prices);

        data.addAll(priceList);
    }

    @Override
    public void displayPharmacologicEffects(List<PharmachologicEffectEntity> pharmachologicEffects) {
        this.pharmachologicEffects = pharmachologicEffects;
    }

    @Override
    public void displayTherapeuticEffects(List<TherapeuticEffectEntity> therapeuticEffects) {
        this.therapeuticEffects = therapeuticEffects;
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
    public void export(String data, String path) {
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(data);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            displayError("Ошибка записи экспортированных данных в файл по пути: "+path);
        }
    }

    @Override
    public void run() {
        showAllDrugs();
        showAllDrugstores();
        showAllPrices();
        selectListener.actionPerfomed(new EventObjectImpl(null, Event.GET_ALL_P_EFFECTS));
        selectListener.actionPerfomed(new EventObjectImpl(null, Event.GET_ALL_T_EFFECTS));
    }


    public void showAllDrugs() {
        EventObjectImpl<Object> eo = new EventObjectImpl<>(null, Event.GET_ALL_DRUGS);
        selectListener.actionPerfomed(eo);
    }

    public void showAllDrugstores() {
        EventObjectImpl<Object> eo = new EventObjectImpl<>(null, Event.GET_ALL_DRUGSTORES);
        selectListener.actionPerfomed(eo);
    }

    public void showAllPrices() {
        EventObjectImpl<Object> eo = new EventObjectImpl<>(null, Event.GET_ALL_PRICES);
        selectListener.actionPerfomed(eo);
    }

    public void addPharmacologicEffect() {
        Dialog<PharmachologicEffectEntity> dialog = new Dialog<>();
        dialog.setTitle("Новый фармакологический эффект");
        dialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField name = new TextField();
        TextArea description = new TextArea();

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Описание:"), 0, 1);
        grid.add(description, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                if (!name.getText().isEmpty()) {
                    return new PharmachologicEffectEntity(name.getText(), description.getText());
                }
            }
            return null;
        });

        Optional<PharmachologicEffectEntity> result = dialog.showAndWait();

        result.ifPresent(pharmachologicEffectEntity -> {
            EventObjectImpl<PharmachologicEffectEntity> eo = new EventObjectImpl<>(result.get(), Event.ADD_P_EFFECT);
            selectListener.actionPerfomed(eo);
            selectListener.actionPerfomed(new EventObjectImpl<Object>(null, Event.GET_ALL_P_EFFECTS));
        });
    }

    public void addTherapeuticEffect() {
        Dialog<TherapeuticEffectEntity> dialog = new Dialog<>();
        dialog.setTitle("Новый терапевтический эффект");
        dialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField name = new TextField();
        TextArea description = new TextArea();

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Описание:"), 0, 1);
        grid.add(description, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                if (!name.getText().isEmpty()) {
                    return new TherapeuticEffectEntity(name.getText(), description.getText());
                }
            }
            return null;
        });

        Optional<TherapeuticEffectEntity> result = dialog.showAndWait();

        result.ifPresent(therapeuticEffectEntity -> {
            EventObjectImpl<TherapeuticEffectEntity> eo = new EventObjectImpl<>(result.get(), Event.ADD_T_EFFECT);
            selectListener.actionPerfomed(eo);
            selectListener.actionPerfomed(new EventObjectImpl<Object>(null, Event.GET_ALL_T_EFFECTS));
        });
    }

    @FXML
    public void addDrug() {

        Dialog<DrugEntity> dialog = createDrugDialog();

        Optional<DrugEntity> result = dialog.showAndWait();

        result.ifPresent(drugEntity -> {
            EventObjectImpl<DrugEntity> eo = new EventObjectImpl<>(result.get(), Event.ADD_DRUG);
            selectListener.actionPerfomed(eo);
        });
    }

    @FXML
    public void updateDrug() {
        int indexSelectElement = tableDrugs.getSelectionModel().getSelectedIndex();

        if (indexSelectElement != -1) {
            Dialog<DrugEntity> dialog = createDrugDialog();

            Node content = dialog.getDialogPane().getContent();

            setTextInTextControl(content, "#name", drugs.get(indexSelectElement).getName());
            setTextInTextControl(content, "#releaseForm", drugs.get(indexSelectElement).getReleaseForm());
            setTextInTextControl(content, "#manufacturer", drugs.get(indexSelectElement).getManufacturer());
            setTextInTextControl(content, "#activeIngredient", drugs.get(indexSelectElement).getActiveIngredient());
            setTextInTextControl(content, "#description", drugs.get(indexSelectElement).getDescription());
            setSelectInSelectorControl(content, "#pEffectSelector",
                    drugs.get(indexSelectElement).getPharmachologicEffect(), pharmachologicEffects);
            setSelectInSelectorControl(content, "#tEffectSelector",
                    drugs.get(indexSelectElement).getTherapeuticEffect(), therapeuticEffects);

            Optional<DrugEntity> result = dialog.showAndWait();

            result.ifPresent(drugEntity -> {
                DrugEntity drug = result.get();
                drug.setId(drugs.get(indexSelectElement).getId());
                EventObjectImpl<DrugEntity> eo = new EventObjectImpl<>(drug, Event.UPDATE_DRUG);
                selectListener.actionPerfomed(eo);
            });
        } else {
            displayError("Выберите строку");
        }
    }

    @FXML
    public void deleteDrug() {
        int index = tableDrugs.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            EventObjectImpl<DrugEntity> eo = new EventObjectImpl<>(drugs.get(index), Event.DELETE_DRUG);
            selectListener.actionPerfomed(eo);
        }
    }

    @FXML
    public void addDrugstore() {

        Dialog<DrugstoreEntity> dialog = createDrugstoreDialog();

        Optional<DrugstoreEntity> result = dialog.showAndWait();

        result.ifPresent(drugstoreEntity -> {
            EventObjectImpl<DrugstoreEntity> eo = new EventObjectImpl<>(result.get(), Event.ADD_DRUGSTORE);
            selectListener.actionPerfomed(eo);
        });
    }

    @FXML
    public void updateDrugstore() {
        int indexSelectElement = tableDrugstores.getSelectionModel().getSelectedIndex();

        if (indexSelectElement != -1) {
            Dialog<DrugstoreEntity> dialog = createDrugstoreDialog();

            Node content = dialog.getDialogPane().getContent();

            setTextInTextControl(content, "#name", drugstores.get(indexSelectElement).getName());
            setTextInTextControl(content, "#district", drugstores.get(indexSelectElement).getDistrict());
            setTextInTextControl(content, "#street", drugstores.get(indexSelectElement).getStreet());
            setTextInTextControl(content, "#building", drugstores.get(indexSelectElement).getBuilding());
            setTextInTextControl(content, "#phone", String.valueOf(drugstores.get(indexSelectElement).getPhone()));
            setTextInTextControl(content, "#hours", drugstores.get(indexSelectElement).getWorkingHours());
            setBooleanInCheckbox(content, "#isRoundTheClock", drugstores.get(indexSelectElement).getIsRoundTheClock()!=0);

            Optional<DrugstoreEntity> result = dialog.showAndWait();

            result.ifPresent(drugstoreEntity -> {
                DrugstoreEntity drugstore = result.get();
                drugstore.setId(drugstores.get(indexSelectElement).getId());
                EventObjectImpl<DrugstoreEntity> eo = new EventObjectImpl<>(drugstore, Event.UPDATE_DRUGSTORE);
                selectListener.actionPerfomed(eo);
            });
        } else {
            displayError("Выберите строку");
        }
    }

    @FXML
    public void deleteDrugstore() {
        int index = tableDrugstores.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            EventObjectImpl<DrugstoreEntity> eo = new EventObjectImpl<>(drugstores.get(index), Event.DELETE_DRUGSTORE);
            selectListener.actionPerfomed(eo);
        }
    }

    @FXML
    public void addPrice() {
        Dialog<PriceEntity> dialog = createPriceDialog();

        Optional<PriceEntity> result = dialog.showAndWait();

        result.ifPresent(priceEntity -> {
            EventObjectImpl<PriceEntity> eo = new EventObjectImpl<>(result.get(), Event.ADD_PRICE);
            selectListener.actionPerfomed(eo);
        });
    }

    @FXML
    public void updatePrice() {
        int indexSelectElement = tablePrices.getSelectionModel().getSelectedIndex();

        if (indexSelectElement != -1) {
            Dialog<PriceEntity> dialog = createPriceDialog();

            Node content = dialog.getDialogPane().getContent();

            setTextInTextControl(content, "#cost", String.valueOf(prices.get(indexSelectElement).getCost()));
            setSelectInTable(content, "#drug", prices.get(indexSelectElement).getDrug(), drugs);
            setSelectInTable(content, "#drugstore", prices.get(indexSelectElement).getDrugstore(), drugstores);

            Optional<PriceEntity> result = dialog.showAndWait();

            result.ifPresent(priceEntity -> {
                PriceEntity price = result.get();
                EventObjectImpl<PriceEntity> eo = new EventObjectImpl<>(price, Event.UPDATE_PRICE);
                selectListener.actionPerfomed(eo);
            });
        } else {
            displayError("Выберите строку");
        }
    }

    @FXML
    public void deletePrice() {
        int index = tablePrices.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            EventObjectImpl<PriceEntity> eo = new EventObjectImpl<>(prices.get(index), Event.DELETE_PRICE);
            selectListener.actionPerfomed(eo);
        }
    }

    @FXML
    public void showExportWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/export_window.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage exportWindow = new Stage();
            ExportWindowController.STAGE = exportWindow;
            ExportWindowController.LISTENER = selectListener;
            exportWindow.setScene(new Scene(root));
            exportWindow.initModality(Modality.WINDOW_MODAL);
            exportWindow.initOwner(STAGE);
            exportWindow.setTitle("Экспорт");
            exportWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showImportWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/import_window.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage importWindow = new Stage();
            ImportWindowController.STAGE = importWindow;
            ImportWindowController.LISTENER = selectListener;
            importWindow.setScene(new Scene(root));
            importWindow.initModality(Modality.WINDOW_MODAL);
            importWindow.initOwner(STAGE);
            importWindow.setTitle("Импорт");
            importWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeDrugstoreTable();
        initializeDrugTable();
        initializePriceTable();
    }

    private void initializeDrugTable() {
        tableDrugs.getColumns().clear();
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

        tableDrugs.getColumns().addAll(nameCol, formCol, manufacturerCol, ingredientCol, pEffectCol, tEffectCol, descCol);

    }

    private void initializeDrugstoreTable() {
        tableDrugstores.getColumns().clear();

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

        tableDrugstores.getColumns().addAll(nameCol, districtCol, addressCol, phoneCol, hoursCol, boolCol);
    }

    private void initializePriceTable() {
        tablePrices.getColumns().clear();

        TableColumn<Price, String> drugCol = new TableColumn<>("Препарат");
        drugCol.setCellValueFactory(
                cellData -> cellData.getValue().drugProperty());

        TableColumn<Price, String> drugstoreCol = new TableColumn<>("Аптека");
        drugstoreCol.setCellValueFactory(
                cellData -> cellData.getValue().drugstoreProperty());

        TableColumn<Price, String> costCol = new TableColumn<>("Цена");
        costCol.setCellValueFactory(
                cellData -> cellData.getValue().costProperty().asString());

        tablePrices.getColumns().addAll(drugCol, drugstoreCol, costCol);
    }

    private Dialog<DrugEntity> createDrugDialog() {
        Dialog<DrugEntity> dialog = new Dialog<>();
        dialog.setTitle("Новый препарат");
        dialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField name = new TextField();
        TextField releaseForm = new TextField();
        TextField manufacturer = new TextField();
        TextField activeIngredient = new TextField();
        TextArea description = new TextArea();
        description.setPrefWidth(350);

        ListView<PharmachologicEffectEntity> pEffectSelector = new ListView<>();
        pEffectSelector.setPrefSize(300, 100);

        pEffectSelector.setCellFactory(
                new Callback<ListView<PharmachologicEffectEntity>, ListCell<PharmachologicEffectEntity>>() {
                    @Override
                    public ListCell<PharmachologicEffectEntity> call(ListView<PharmachologicEffectEntity> param) {

                        return new ListCell<PharmachologicEffectEntity>() {
                            @Override
                            public void updateItem(PharmachologicEffectEntity item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item.getName());
                                } else {
                                    setText(null);
                                }
                            }

                        };
                    }
                });

        pEffectSelector.getItems().addAll(pharmachologicEffects);

        Button addPEffectBtn = new Button("Добавить...");
        addPEffectBtn.setOnAction(event -> {
            addPharmacologicEffect();
            pEffectSelector.getItems().clear();
            pEffectSelector.getItems().addAll(pharmachologicEffects);
        });

        ListView<TherapeuticEffectEntity> tEffectSelector = new ListView<>();
        tEffectSelector.setPrefSize(300, 100);

        tEffectSelector.setCellFactory(
                new Callback<ListView<TherapeuticEffectEntity>, ListCell<TherapeuticEffectEntity>>() {
                    @Override
                    public ListCell<TherapeuticEffectEntity> call(ListView<TherapeuticEffectEntity> param) {

                        return new ListCell<TherapeuticEffectEntity>() {
                            @Override
                            public void updateItem(TherapeuticEffectEntity item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(String.format("%s: %s", item.getName(), item.getDescription()));
                                } else {
                                    setText(null);
                                }
                            }

                        };
                    }
                });

        tEffectSelector.getItems().addAll(therapeuticEffects);

        Button addTEffectBtn = new Button("Добавить...");
        addTEffectBtn.setOnAction(event -> {
            addTherapeuticEffect();
            tEffectSelector.getItems().clear();
            tEffectSelector.getItems().addAll(therapeuticEffects);
        });

        name.setId("name");
        releaseForm.setId("releaseForm");
        manufacturer.setId("manufacturer");
        activeIngredient.setId("activeIngredient");
        description.setId("description");
        pEffectSelector.setId("pEffectSelector");
        tEffectSelector.setId("tEffectSelector");

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Форма выпуска:"), 0, 1);
        grid.add(releaseForm, 1, 1);
        grid.add(new Label("Производитель:"), 0, 2);
        grid.add(manufacturer, 1, 2);
        grid.add(new Label("Активный ингредиент:"), 0, 3);
        grid.add(activeIngredient, 1, 3);
        grid.add(new Label("Фармакологический эффект:"), 0, 4);
        grid.add(new HBox(5, pEffectSelector, addPEffectBtn), 1, 4);
        grid.add(new Label("Терапевтический эффект:"), 0, 5);
        grid.add(new HBox(5, tEffectSelector, addTEffectBtn), 1, 5);
        grid.add(new Label("Описание:"), 0, 6);
        grid.add(description, 1, 6);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                if (!name.getText().isEmpty() && !releaseForm.getText().isEmpty() && !manufacturer.getText().isEmpty()
                        && !activeIngredient.getText().isEmpty() && !pEffectSelector.getSelectionModel().isEmpty()
                        && !tEffectSelector.getSelectionModel().isEmpty()) {
                    return new DrugEntity(name.getText(), releaseForm.getText(), manufacturer.getText(),
                            activeIngredient.getText(), pEffectSelector.getSelectionModel().getSelectedItem(),
                            tEffectSelector.getSelectionModel().getSelectedItem(), description.getText());
                }
            }
            return null;
        });

        return dialog;
    }

    private Dialog<DrugstoreEntity> createDrugstoreDialog() {
        Dialog<DrugstoreEntity> dialog = new Dialog<>();
        dialog.setTitle("Новая аптека");
        dialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField name = new TextField();
        TextField district = new TextField();
        TextField street = new TextField();
        TextField building = new TextField();
        TextField phone = new TextField();
        TextField hours = new TextField();
        CheckBox isRoundTheClock = new CheckBox();

        name.setId("name");
        district.setId("district");
        street.setId("street");
        building.setId("building");
        phone.setId("phone");
        hours.setId("hours");
        isRoundTheClock.setId("isRoundTheClock");

        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Район:"), 0, 1);
        grid.add(district, 1, 1);
        grid.add(new Label("Улица:"), 0, 2);
        grid.add(street, 1, 2);
        grid.add(new Label("Дом:"), 0, 3);
        grid.add(building, 1, 3);
        grid.add(new Label("Телефон:"), 0, 4);
        grid.add(phone, 1, 4);
        grid.add(new Label("Часы работы:"), 0, 5);
        grid.add(hours, 1, 5);
        grid.add(new Label("Крулосуточная:"), 0, 6);
        grid.add(isRoundTheClock, 1, 6);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                if (!name.getText().isEmpty() && !district.getText().isEmpty() && !street.getText().isEmpty()
                        && !building.getText().isEmpty() && !phone.getText().isEmpty() && !hours.getText().isEmpty()) {
                    return new DrugstoreEntity(name.getText(), district.getText(), street.getText(), building.getText(),
                            Long.valueOf(phone.getText()), hours.getText(),
                            (short) (isRoundTheClock.isSelected() ? 1 : 0));
                }
            }
            return null;
        });

        return dialog;
    }

    private Dialog<PriceEntity> createPriceDialog() {
        Dialog<PriceEntity> dialog = new Dialog<>();
        dialog.setTitle("");
        dialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField cost = new TextField();
        TableView drugsTable = tableDrugs;
        TableView drugstoresTable = tableDrugstores;
        drugsTable.setPrefSize(350, 200);
        drugstoresTable.setPrefSize(350, 200);

        cost.setId("cost");
        drugsTable.setId("drug");
        drugstoresTable.setId("drugstore");

        grid.add(drugsTable, 0, 0);
        grid.add(drugstoresTable, 1, 0);
        grid.add(new HBox(5, new Label("Цена:"), cost), 0, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                if (!cost.getText().isEmpty() && !drugsTable.getSelectionModel().isEmpty()
                        && !drugstoresTable.getSelectionModel().isEmpty()) {
                    return new PriceEntity(
                            drugs.get(drugsTable.getSelectionModel().getSelectedIndex()),
                            drugstores.get(drugstoresTable.getSelectionModel().getSelectedIndex()),
                            Long.valueOf(cost.getText()));
                }
            }
            return null;
        });

        return dialog;
    }

    private void setTextInTextControl(Node node, String id, String text) {
        Node element = node.getScene().lookup(id);
        if (element instanceof TextInputControl) {
            ((TextInputControl) element).setText(text);
        }
    }

    private void setBooleanInCheckbox(Node node, String id, Boolean value) {
        Node element = node.getScene().lookup(id);
        if (element instanceof CheckBox) {
            ((CheckBox) element).setSelected(value);
        }
    }

    private void setSelectInSelectorControl(Node node, String id, Object entity, List listEntity) {
        Node element = node.getScene().lookup(id);
        if (element instanceof ListView) {
            //TODO: подумать над этим
            for (int i = 0; i < listEntity.size(); i++) {
                if (listEntity.get(i).equals(entity)) {
                    ((ListView) element).getSelectionModel().select(i);
                }
            }
        }
    }

    private void setSelectInTable(Node node, String id, Object entity, List listEntity) {
        Node element = node.getScene().lookup(id);
        if (element instanceof TableView) {
            //TODO: подумать над этим
            for (int i = 0; i < listEntity.size(); i++) {
                if (listEntity.get(i).equals(entity)) {
                    ((TableView) element).getSelectionModel().select(i);
                }
            }
        }
    }
}
