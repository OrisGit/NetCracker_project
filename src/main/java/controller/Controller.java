package controller;

import event.UserRequestSelectListener;
import event.EventObject;
import model.dao.DrugDAOImpl;
import model.dao.DrugstoreDAOImpl;
import model.interfaces.DrugDAO;
import model.interfaces.DrugstoreDAO;
import view.*;

public class Controller implements UserRequestSelectListener {
    View view = new TextView();
    DrugDAO drugDAO = new DrugDAOImpl();
    DrugstoreDAO drugstoreDAO = new DrugstoreDAOImpl();

    public void run(){
        view.setUserRequestSelectListener(this);
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
