import event.*;
import event.EventObject;
import event.EventObjectImpl;
import model.entities.DrugEntity;
import model.entities.DrugstoreEntity;
import parameters.Parameters;
import parameters.ParametersBuilder;
import view.View;

import java.util.List;
import java.util.Scanner;

public class ViewStub implements View{

    Scanner sc = new Scanner(System.in);

    UserRequestSelectListener userRequestSelectListener;

    public void setUserRequestSelectListener(UserRequestSelectListener userRequestSelectListener) {
        if(userRequestSelectListener !=null){
            this.userRequestSelectListener = userRequestSelectListener;
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
            EventObject eventObject = new EventObjectImpl(parameters,Event.GET_ALL_DRUGSTORE);
            userRequestSelectListener.actionPerfomed(eventObject);
        }else{
            ParametersBuilder builder = new ParametersBuilder();
            Parameters parameters = builder.createParameters();
            EventObject eventObject = new EventObjectImpl(parameters,Event.GET_ALL_DRUGS);
            userRequestSelectListener.actionPerfomed(eventObject);
        }
    }
}
