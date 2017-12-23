package controller;

import event.UserRequestSelectListener;
import event.EventObject;
import model.dao.DrugDAOImpl;
import model.dao.DrugstoreDAOImpl;
import model.entities.DrugEntity;
import model.entities.DrugstoreEntity;
import model.interfaces.DrugDAO;
import model.interfaces.DrugstoreDAO;
import view.*;

public class Controller implements UserRequestSelectListener {

    DrugDAO drugDAO = new DrugDAOImpl();
    DrugstoreDAO drugstoreDAO = new DrugstoreDAOImpl();
    View view;

    public Controller(View view) {
        this.view = view;
    }

    public void run(){
        view.setUserRequestSelectListener(this);
        view.run();
    }

    @Override
    public void actionPerfomed(EventObject eo) {

        try{
            switch(eo.getEventType()){
                case GET_ALL_DRUGS: view.displayDrugs(drugDAO.getAll());break;
                case GET_ALL_DRUGSTORE: view.displayDrugstores(drugstoreDAO.getAll()); break;
                case GET_DRUGS_WITH_PARAMETERS:break;
                case GET_DRUGSTORE_WITH_PARAMETERS:break;
                case ADD_DRUG:break;
                case DELETE_DRUG:
                    drugDAO.delete((DrugEntity) eo.getEventSource());
                    view.displayDrugs(drugDAO.getAll());
                    break;
                case UPDATE_DRUG:break;
                case ADD_DRUGSTORE:break;
                case DELTE_DRUGSTORE:
                    drugstoreDAO.delete((DrugstoreEntity) eo.getEventSource());
                    view.displayDrugstores(drugstoreDAO.getAll());
                    break;
                case UPDATE_DRUGSTORE:break;
            }
        }catch (Exception e){
            view.displayError(e.getMessage());
        }

    }
}
