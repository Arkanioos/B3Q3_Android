package maxm.androidb3.androidb3.features.test.presenter;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import maxm.androidb3.androidb3.common.keys.IntentExtraKeys;
import maxm.androidb3.androidb3.features.classSelection.model.Class;
import maxm.androidb3.androidb3.features.common.model.AdapterHandler;
import maxm.androidb3.androidb3.features.common.model.ListAdapter;
import maxm.androidb3.androidb3.features.common.model.ListItem;
import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.test.contract.TestContract;
import maxm.androidb3.androidb3.features.test.data.entity.TestEntity;
import maxm.androidb3.androidb3.features.test.model.Test;
import maxm.androidb3.androidb3.features.year.model.exception.EmptyDialogInputException;

public class TestPresenter implements TestContract.Presenter, AdapterHandler {

    private boolean isParent = false;
    private ListAdapter adapter = null;
    private ListAdapter dialogAdapter = null;
    private TestContract.View view = null;
    private TestContract.Repository repository = null;
    private Class currentClass = null;
    private Test tempTest = null;

    public TestPresenter(TestContract.View view, TestContract.Repository repository){
        this.view = view;
        this.repository = repository;
    }

    public void onAddDialogConfirm(String input, double numberInput, boolean isChecked){
        try{
            if(input.isEmpty()) throw new EmptyDialogInputException("Le champ doit être rempli.");
            tempTest = new Test(input, numberInput, currentClass.getId(), -1, null);
            if(isChecked){
                isParent = true;
                Log.d("TEST","TRYING TO OPEN PARENT DIALOG");
                view.openParentDialog();
            }
            else{
                isParent = false;
                long insertedId = repository.insert(tempTest.toEntity());
                tempTest.setId(insertedId);
                adapter.addItem(tempTest);
                tempTest = null;
                view.closeDialog();
            }
        } catch (EmptyDialogInputException | DaoOperationException e) {
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("ERROR_STUDENT", details);
            view.showDialogError(details);
        }
    }

    private void onParentDialogConfirmed(Test test){
        try{
            tempTest.setParentId(test.getId());
            tempTest.setParentName(test.getName());
            Log.d("TEST_TEST", "INSERTED TEST HAS PARENT ID = " + test.getParentId());
            long insertedId = repository.insert(tempTest.toEntity());
            tempTest.setId(insertedId);
            adapter.addItem(tempTest);
            tempTest = null;
            isParent = false;
            view.closeParentDialog();
        } catch ( DaoOperationException e) {
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("ERROR_STUDENT", details);
            view.showDialogError(details);
        }
    }

    public void onListCreated(RecyclerView list){
        adapter = new ListAdapter(new ArrayList<>(0), this);
        list.setAdapter(adapter);
    }

    public void onParentListCreated(RecyclerView list){
        dialogAdapter = new ListAdapter(new ArrayList<>(0), this);
        list.setAdapter(dialogAdapter);
    }

    public void onParentDialogShown(){
        List<ListItem> items = adapter.getItems();
        for(ListItem item : items){
            Log.d("TEST_TEST", "ADDING FOLLOWING ITEM TO DIALOG ADAPTER : " + item.toString());
            dialogAdapter.addItem(item);
        }
    }

    public void onViewCreated(Intent intent){ // TO ADAPT
        try{
            currentClass = (Class)intent.getSerializableExtra(IntentExtraKeys.CLASS_KEY);
            List<TestEntity> entities = repository.getAllByClassId(currentClass.getId());
            for (TestEntity entity : entities){
                if(!entity.isValid()) continue;
                Test test = entity.toModel();
                adapter.addItem(test);
            }
        } catch (DaoOperationException e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("ERROR_STUDENT", details);
            view.showError(details);
        }
    }

    public void onItemClicked(ListItem item){
        Test test = (Test)item;
        if(tempTest == null){
            view.loadStudentActivity(test);
        } else {
            onParentDialogConfirmed(test);
        }
    }

}
