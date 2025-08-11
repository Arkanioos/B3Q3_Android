package com.example.androidb3.features.yearSelection.presenter;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidb3.features.common.model.AdapterHandler;
import com.example.androidb3.features.common.model.ListItem;
import com.example.androidb3.features.database.model.exception.DaoOperationException;
import com.example.androidb3.features.yearSelection.contract.YearContract;
import com.example.androidb3.features.yearSelection.data.entity.YearEntity;
import com.example.androidb3.features.yearSelection.model.Year;
import com.example.androidb3.features.yearSelection.model.exception.EmptyDialogInputException;
import com.example.androidb3.features.yearSelection.view.ListAdapter;

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
            Log.d("TEST_ID", "GOT AN ID : " + year.getId());
            adapter.addItem(year);
            view.closeDialog();
        } catch (EmptyDialogInputException | DaoOperationException e) {
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("Error", details);
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
                if(entity.year == null || entity.year.isEmpty()) continue;
                adapter.addItem(entity.toModel());
            }
        } catch (DaoOperationException e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("Error", details);
            view.showError(details);
        }
    }

    public void onItemClicked(ListItem item){
        Year year = (Year)item;
        view.loadClassActivity(year);
    }
}
