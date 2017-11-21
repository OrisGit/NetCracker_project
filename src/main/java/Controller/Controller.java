package Controller;

import Event.ActionListener;
import Event.*;
import Event.EventObject;
import Model.DAO.DAOException;
import Model.DAO.DrugDAOImpl;
import Model.Entities.DrugEntity;
import View.*;

import java.util.List;

public class Controller implements ActionListener{
    View view = new ViewStub();
    DrugDAOImpl drugDAO = new DrugDAOImpl();

    public void run(){
        view.setActionListener(this);
        view.run();
    }

    @Override
    public void actionPerfomed(EventObject eo) {
        if(eo.getEventType().equals(Event.GET_DRUGS)){
            try {
                List<DrugEntity> drugs = drugDAO.getAll(DrugEntity.class);
                view.displayDrugs(drugs);
            } catch (DAOException e) {
                view.displayError(e.getMessage());
            }
        }
    }
}
