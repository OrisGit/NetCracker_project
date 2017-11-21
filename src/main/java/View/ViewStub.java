package View;

import Event.ActionListener;
import Event.*;
import Event.EventObject;
import Event.EventObjectImpl;
import Model.Entities.DrugEntity;
import Model.Entities.DrugstoreEntity;
import Prameters.Parameters;
import Prameters.ParametersBuilder;

import java.util.List;
import java.util.Scanner;

public class ViewStub implements View {

    Scanner sc = new Scanner(System.in);

    ActionListener actionListener;

    @Override
    public void setActionListener(ActionListener actionListener) {
        if(actionListener!=null){
            this.actionListener = actionListener;
        }
    }

    @Override
    public void displayDrugs(List<DrugEntity> drugs) {
        drugs.forEach(System.out::println);
    }

    @Override
    public void displayPharmacies(List<DrugstoreEntity> drugstores) {
        drugstores.forEach(System.out::println);
    }

    @Override
    public void displayError(String message) {
        System.out.println(message);
    }

    @Override
    public void run() {
        System.out.println("Введите 1");
        if(sc.next().equals("1")){
            ParametersBuilder builder = new ParametersBuilder();
            Parameters parameters = builder.createParameters();
            EventObject eventObject = new EventObjectImpl(parameters,Event.GET_DRUGS);
            actionListener.actionPerfomed(eventObject);
        }
    }
}
