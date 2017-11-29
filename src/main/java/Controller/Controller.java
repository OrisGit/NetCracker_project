package Controller;

import Event.UserRequestSelectListener;
import Event.EventObject;
import Model.DAO.DrugDAOImpl;
import Model.DAO.DrugstoreDAOImpl;
import Model.Interfaces.DrugDAO;
import Model.Interfaces.DrugstoreDAO;
import View.*;

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
