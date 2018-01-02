package view;

import event.*;
import model.entities.*;

import parameters.Parameters;
import parameters.ParametersBuilder;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.a7.A7_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.List;
import java.util.Scanner;

public class TextView implements View{
    private Scanner sc = new Scanner(System.in);
    private UserRequestSelectListener userRequestSelectListener;

    public TextView() {
    }

    public void setUserRequestSelectListener(UserRequestSelectListener userRequestSelectListener) {
        if (this.userRequestSelectListener == null) {
            this.userRequestSelectListener = userRequestSelectListener;
        }
    }

    public void displayDrugs(List<DrugEntity> drugs) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("№", "Наименование", "Форма выпуска", "Производитель", "Активный ингредиент",
                "Фармакологический эффект", "Терапевтический эффект").setTextAlignment(TextAlignment.CENTER);
        at.addRule();

        for (DrugEntity i : drugs) {
            at.addRow(i.getId(), i.getName(), i.getReleaseForm(), i.getManufacturer(), i.getActiveIngredient(),
                    i.getPharmachologicEffect(), i.getTherapeuticEffect());
            at.addRule();
        }
        at.getContext().setWidth(140);
        at.getContext().setGrid(A7_Grids.minusBarPlusEquals());

        System.out.println(at.render());
    }

    public void displayDrugstores(List<DrugstoreEntity> drugstores) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("#", "Название", "Район", "Адрес", "Телефон", "График работы", "Круглосуточная").
                setTextAlignment(TextAlignment.CENTER);
        at.addRule();

        for (DrugstoreEntity i : drugstores) {
            at.addRow(i.getId(), i.getName(), getFullAddress(i), i.getPhone(), i.getWorkingHours(), i.getIsRoundTheClock());
            at.addRule();
        }
        at.getContext().setWidth(140);
        at.getContext().setGrid(A7_Grids.minusBarPlusEquals());

        System.out.println(at.render());
    }

    public void displayPrices(List<PriceEntity> prices) {
        //
    }

    public void displayPharmacologicEffects(List<PharmachologicEffectEntity> pharmachologicEffects) {
        //
    }

    public void displayTherapeuticEffects(List<TherapeuticEffectEntity> therapeuticEffects) {
        //
    }

    public void displayError(String message) {
        System.out.println("Ошибка:");
        System.out.println(message);
    }

    public void run() {
        System.out.println("Введите 1");
        if(sc.next().equals("1")){
            ParametersBuilder builder = new ParametersBuilder();
            Parameters parameters = builder.createParameters();
            EventObject eventObject = new EventObjectImpl<Parameters>(parameters, Event.GET_ALL_DRUGS);
            userRequestSelectListener.actionPerfomed(eventObject);
        }
    }

    public void displayDescription(DrugEntity drug) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow(drug.getName());
        at.addRule();
        at.addRow(drug.getDescription());
        at.getContext().setGrid(A7_Grids.minusBarPlusEquals());

        System.out.println(at.render());
    }

    private String getFullAddress (DrugstoreEntity drugstore) {
        StringBuilder sb = new StringBuilder();
        sb.append(drugstore.getDistrict());
        sb.append("район , ");
        sb.append(drugstore.getStreet());
        sb.append(", ");
        sb.append(drugstore.getBuilding());
        return sb.toString();
    }
}