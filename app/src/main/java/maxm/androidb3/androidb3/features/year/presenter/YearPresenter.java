package maxm.androidb3.androidb3.features.year.presenter;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import maxm.androidb3.androidb3.features.common.model.AdapterHandler;
import maxm.androidb3.androidb3.features.common.model.ListItem;
import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.year.contract.YearContract;
import maxm.androidb3.androidb3.features.year.data.entity.YearEntity;
import maxm.androidb3.androidb3.features.year.model.Year;
import maxm.androidb3.androidb3.features.year.model.exception.EmptyDialogInputException;
import maxm.androidb3.androidb3.features.common.model.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class YearPresenter implements YearContract.Presenter, AdapterHandler {
    private ListAdapter adapter = null;
    private YearContract.View view = null;
    private YearContract.Repository repository = null;

    public YearPresenter(YearContract.View view, YearContract.Repository repository){
        this.view = view;
        this.repository = repository;
    }

    public void onAddDialogConfirm(String input){
        try{
            if(input.isEmpty()) throw new EmptyDialogInputException("Le champ doit être rempli.");
            Year year = new Year(input);
            long insertedId = repository.insert(year.toEntity());
            year.setId(insertedId);
            adapter.addItem(year);
            view.closeDialog();
        } catch (EmptyDialogInputException | DaoOperationException e) {
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("ERROR_YEAR", details);
            view.showDialogError(details);
        }
    }

    public void onListCreated(RecyclerView list){
        adapter = new ListAdapter(new ArrayList<>(0), this);
        list.setAdapter(adapter);
    }

    public void onViewCreated(){
        try{
            List<YearEntity> entities = repository.getAll();
            for (YearEntity entity : entities){
                if(!entity.isValid()) continue;
                adapter.addItem(entity.toModel());
            }
        } catch (DaoOperationException e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("ERROR_YEAR", details);
            view.showError(details);
        }
    }

    public void onItemClicked(ListItem item){
        Year year = (Year)item;
        view.loadClassActivity(year);
    }
}
