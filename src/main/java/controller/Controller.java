package controller;

import event.UserRequestSelectListener;
import event.EventObject;
import model.dao.*;
import model.entities.*;
import model.interfaces.*;
import view.*;

public class Controller implements UserRequestSelectListener {

    DrugDAO drugDAO = new DrugDAOImpl();
    DrugstoreDAO drugstoreDAO = new DrugstoreDAOImpl();
    PEffectDAO pEffectDAO = new PEffectDAOImpl();
    TEffectDAO tEffectDAO = new TEffectDAOImpl();
    PriceDAO priceDAO = new PriceDAOImpl();
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
                case GET_ALL_DRUGS:
                    view.displayDrugs(drugDAO.getAll());
                    break;
                case GET_ALL_DRUGSTORES:
                    view.displayDrugstores(drugstoreDAO.getAll());
                    break;
                case GET_DRUGS_WITH_PARAMETERS:
                    break;
                case GET_DRUGSTORE_WITH_PARAMETERS:
                    break;
                case ADD_DRUG:
                    drugDAO.add((DrugEntity) eo.getEventSource());
                    view.displayDrugs(drugDAO.getAll());
                    break;
                case DELETE_DRUG:
                    drugDAO.delete((DrugEntity) eo.getEventSource());
                    view.displayDrugs(drugDAO.getAll());
                    break;
                case UPDATE_DRUG:
                    drugDAO.update((DrugEntity) eo.getEventSource());
                    view.displayDrugs(drugDAO.getAll());
                    break;
                case ADD_DRUGSTORE:
                    drugstoreDAO.add((DrugstoreEntity) eo.getEventSource());
                    view.displayDrugstores(drugstoreDAO.getAll());
                    break;
                case DELETE_DRUGSTORE:
                    drugstoreDAO.delete((DrugstoreEntity) eo.getEventSource());
                    view.displayDrugstores(drugstoreDAO.getAll());
                    break;
                case UPDATE_DRUGSTORE:
                    drugstoreDAO.update((DrugstoreEntity) eo.getEventSource());
                    view.displayDrugstores(drugstoreDAO.getAll());
                    break;
                case ADD_P_EFFECT:
                    pEffectDAO.add((PharmachologicEffectEntity) eo.getEventSource());
                    break;
                case ADD_T_EFFECT:
                    tEffectDAO.add((TherapeuticEffectEntity) eo.getEventSource());
                    break;
                case GET_ALL_P_EFFECTS:
                    view.displayPharmacologicEffects(pEffectDAO.getAll());
                    break;
                case GET_ALL_T_EFFECTS:
                    view.displayTherapeuticEffects(tEffectDAO.getAll());
                    break;
                case GET_ALL_PRICES:
                    view.displayPrices(priceDAO.getAll());
                    break;
                case ADD_PRICE:
                    priceDAO.add((PriceEntity) eo.getEventSource());
                    view.displayPrices(priceDAO.getAll());
                    break;
                case DELETE_PRICE:
                    priceDAO.delete((PriceEntity) eo.getEventSource());
                    view.displayPrices(priceDAO.getAll());
                    break;
                case UPDATE_PRICE:
                    priceDAO.update((PriceEntity) eo.getEventSource());
                    view.displayPrices(priceDAO.getAll());
                    break;


            }
        }catch (Exception e){
            view.displayError(e.getMessage());
        }

    }
}
