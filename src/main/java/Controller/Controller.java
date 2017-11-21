package Controller;

import Event.ActionListener;
import Event.*;
import Event.EventObject;
import Model.DAO.DrugDAOImpl;
import Model.DAO.DrugstoreDAOImpl;
import Model.Interfaces.*;
import Model.Entities.DrugEntity;
import View.*;

import java.util.List;

public class Controller implements ActionListener{
    View view = new ViewStub();
    DrugDAOImpl drugDAO = new DrugDAOImpl();
    DrugstoreDAOImpl drugstoreDAO = new DrugstoreDAOImpl();

    public void run(){
        view.setActionListener(this);
        view.run();
    }

    @Override
    public void actionPerfomed(EventObject eo) {

        try{
            switch(eo.getEventType()){
                case GET_ALL_DRUGS: view.displayDrugs(drugDAO.getAll());break;
                case GET_ALL_DRUGSTORE: view.displayPharmacies(drugstoreDAO.getAll()); break;
                case GET_DRUGS_WITH_PARAMETERS:break;
                case GET_DRUGSTORE_WITH_PARAMETERS:break;
                case ADD_DRUG:break;
                case DELTE_DRUG:break;
                case UPDATE_DRUG:break;
                case ADD_DRUGSTORE:break;
                case DELTE_DRUGSTORE:break;
                case UPDATE_DRUGSTORE:break;
            }
        }catch (Exception e){
            view.displayError(e.getMessage());
        }

    }
}
