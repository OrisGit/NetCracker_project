package controller;

import event.EventObject;
import model.dao.*;
import model.entities.*;
import model.import_export.Exporter;
import model.import_export.FormatType;
import model.import_export.Importer;
import model.interfaces.*;
import view.*;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.Vector;

public class ControllerImpl implements Controller{

    public final static String NAME = "server/controller";

    private DrugDAO drugDAO = new DrugDAOImpl();
    private DrugstoreDAO drugstoreDAO = new DrugstoreDAOImpl();
    private PEffectDAO pEffectDAO = new PEffectDAOImpl();
    private TEffectDAO tEffectDAO = new TEffectDAOImpl();
    private PriceDAO priceDAO = new PriceDAOImpl();
    private Vector<View> views = new Vector<>();

    public ControllerImpl() {
    }

    @Override
    public void actionPerfomed(EventObject eo, View stub) {
        try{
            switch(eo.getEventType()){
                case GET_ALL_DRUGS:
                    stub.displayDrugs(drugDAO.getAll());
                    break;
                case GET_ALL_DRUGSTORES:
                    stub.displayDrugstores(drugstoreDAO.getAll());
                    break;
                case GET_DRUGS_WITH_PARAMETERS:
                    break;
                case GET_DRUGSTORE_WITH_PARAMETERS:
                    break;
                case ADD_DRUG:
                    drugDAO.add((DrugEntity) eo.getEventSource());
                    for(View view : views){
                        if(!view.equals(stub))
                            view.displayDrugs(drugDAO.getAll());
                    }
                    break;
                case DELETE_DRUG:
                    drugDAO.delete((DrugEntity) eo.getEventSource());
                    for(View view : views){
                        if(!view.equals(stub))
                            view.displayDrugs(drugDAO.getAll());
                    }
                    break;
                case UPDATE_DRUG:
                    drugDAO.update((DrugEntity) eo.getEventSource());
                    for(View view : views){
                        if(!view.equals(stub))
                            view.displayDrugs(drugDAO.getAll());
                    }
                    break;
                case ADD_DRUGSTORE:
                    drugstoreDAO.add((DrugstoreEntity) eo.getEventSource());
                    for(View view : views){
                        if(!view.equals(stub))
                            view.displayDrugstores(drugstoreDAO.getAll());
                    }
                    break;
                case DELETE_DRUGSTORE:
                    drugstoreDAO.delete((DrugstoreEntity) eo.getEventSource());
                    for(View view : views){
                        if(!view.equals(stub))
                            view.displayDrugstores(drugstoreDAO.getAll());
                    }
                    break;
                case UPDATE_DRUGSTORE:
                    drugstoreDAO.update((DrugstoreEntity) eo.getEventSource());
                    for(View view : views){
                        if(!view.equals(stub))
                            view.displayDrugstores(drugstoreDAO.getAll());
                    }
                    break;
                case ADD_P_EFFECT:
                    pEffectDAO.add((PharmachologicEffectEntity) eo.getEventSource());
                    for(View view : views){
                        if(!view.equals(stub))
                            view.displayPharmacologicEffects(pEffectDAO.getAll());
                    }
                    break;
                case ADD_T_EFFECT:
                    tEffectDAO.add((TherapeuticEffectEntity) eo.getEventSource());
                    for(View view : views){
                        if(!view.equals(stub))
                            view.displayTherapeuticEffects(tEffectDAO.getAll());
                    }
                    break;
                case GET_ALL_P_EFFECTS:
                    stub.displayPharmacologicEffects(pEffectDAO.getAll());
                    break;
                case GET_ALL_T_EFFECTS:
                    stub.displayTherapeuticEffects(tEffectDAO.getAll());
                    break;
                case GET_ALL_PRICES:
                    stub.displayPrices(priceDAO.getAll());
                    break;
                case ADD_PRICE:
                    priceDAO.add((PriceEntity) eo.getEventSource());
                    for(View view : views){
                        if(!view.equals(stub))
                            view.displayPrices(priceDAO.getAll());
                    }
                    break;
                case DELETE_PRICE:
                    priceDAO.delete((PriceEntity) eo.getEventSource());
                    for(View view : views){
                        if(!view.equals(stub))
                            view.displayPrices(priceDAO.getAll());
                    }
                    break;
                case UPDATE_PRICE:
                    priceDAO.update((PriceEntity) eo.getEventSource());
                    for(View view : views){
                        if(!view.equals(stub))
                            view.displayPrices(priceDAO.getAll());
                    }
                    break;
                case EXPORT:
                    if(eo.getEventSource() instanceof Map){
                        Map<String, Object> prop = (Map<String, Object>)eo.getEventSource();
                        if(prop.containsKey("path") && prop.containsKey("type")){
                            String data = Exporter.export((FormatType) prop.get("type"),true);
                            if(data!=null){
                                stub.export(data,(String)prop.get("path"));
                                break;
                            }

                        }
                    }
                    stub.displayError("Ошибка экспорта");
                    break;
                case IMPORT:
                    if(eo.getEventSource() instanceof Map){
                        Map<String, Object> prop = (Map<String, Object>)eo.getEventSource();
                        if(prop.containsKey("data") && prop.containsKey("type")){
                            String data = (String) prop.get("data");
                            FormatType type = (FormatType)prop.get("type");
                            Importer._import(data,type);
                            break;
                        }
                    }
                    stub.displayError("Ошибка импорта");
                    break;
            }
        }catch (Exception e){
            try {
                stub.displayError(e.getMessage());
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }

    }

    @Override
    public void registerClient(View view) {
        views.add(view);
        try {
            view.setUserRequestSelectListener(this);
            view.run();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
