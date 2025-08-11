package com.example.androidb3.features.classSelection.presenter;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidb3.common.keys.IntentExtraKeys;
import com.example.androidb3.features.classSelection.contract.ClassContract;
import com.example.androidb3.features.classSelection.data.entity.ClassEntity;
import com.example.androidb3.features.common.model.AdapterHandler;
import com.example.androidb3.features.common.model.ListItem;
import com.example.androidb3.features.database.model.exception.DaoOperationException;
import com.example.androidb3.features.yearSelection.model.Year;
import com.example.androidb3.features.yearSelection.model.exception.EmptyDialogInputException;
import com.example.androidb3.features.yearSelection.view.ListAdapter;
import com.example.androidb3.features.classSelection.model.Class;

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
                if(entity.name == null || entity.name.isEmpty()) continue;
                adapter.addItem(entity.toModel());
            }
        } catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("Error", details);
            view.showError(details);
        }
    }

    public void onAddDialogConfirm(String input){
        try{
            if(input.isEmpty()) throw new EmptyDialogInputException("Le champ doit être rempli.");
            Class _class = new Class(input, currentYear.getId());
            repository.insertAll(_class.toEntity());
            adapter.addItem(_class);
            view.closeDialog();
        } catch (EmptyDialogInputException | DaoOperationException e) {
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("Error", details);
            view.showDialogError(details);
        }
    }

    public void onItemClicked(ListItem item){
        Class _class = (Class)item;
        view.loadStudentActivity(_class);
    }
}
