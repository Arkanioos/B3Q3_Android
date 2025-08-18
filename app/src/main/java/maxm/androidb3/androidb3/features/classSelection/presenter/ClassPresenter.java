package maxm.androidb3.androidb3.features.classSelection.presenter;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import maxm.androidb3.androidb3.common.keys.IntentExtraKeys;
import maxm.androidb3.androidb3.features.classSelection.contract.ClassContract;
import maxm.androidb3.androidb3.features.classSelection.data.entity.ClassEntity;
import maxm.androidb3.androidb3.features.common.model.AdapterHandler;
import maxm.androidb3.androidb3.features.common.model.ListItem;
import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.year.model.Year;
import maxm.androidb3.androidb3.features.year.model.exception.EmptyDialogInputException;
import maxm.androidb3.androidb3.features.common.model.ListAdapter;
import maxm.androidb3.androidb3.features.classSelection.model.Class;

import java.util.ArrayList;
import java.util.List;

public class ClassPresenter implements ClassContract.Presenter, AdapterHandler {
    private ListAdapter adapter = null;
    private ClassContract.View view = null;
    private ClassContract.Repository repository = null;
    private Year currentYear = null;

    public ClassPresenter(ClassContract.View view, ClassContract.Repository repository){
        this.view = view;
        this.repository = repository;
    }

    public void onListCreated(RecyclerView list){
        adapter = new ListAdapter(new ArrayList<>(0), this);
        list.setAdapter(adapter);
    }

    public void onViewCreated(Intent intent){
        try{
            currentYear = (Year)intent.getSerializableExtra(IntentExtraKeys.YEAR_KEY);
            List<ClassEntity> entities = repository.getAllById(currentYear.getId());
            for(ClassEntity entity : entities){
                if(!entity.isValid()) continue;
                adapter.addItem(entity.toModel());
            }
        } catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("ERROR_CLASS", details);
            view.showError(details);
        }
    }

    public void onAddDialogConfirm(String input){
        try{
            if(input.isEmpty()) throw new EmptyDialogInputException("Le champ doit être rempli.");
            Class _class = new Class(input, currentYear.getId());
            long insertedId = repository.insert(_class.toEntity());
            _class.setId(insertedId);
            adapter.addItem(_class);
            view.closeDialog();
        } catch (EmptyDialogInputException | DaoOperationException e) {
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("ERROR_CLASS", details);
            view.showDialogError(details);
        }
    }

    public void onItemClicked(ListItem item){
        Class _class = (Class)item;
        Log.d("TEST_CLASS", "RETRIEVED CLASS : " + _class.getName() + " " + _class.getId());
        view.loadStudentActivity(_class);
    }
}
