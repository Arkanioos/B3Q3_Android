package com.example.androidb3.features.studentSelection.presenter;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidb3.common.keys.IntentExtraKeys;
import com.example.androidb3.features.common.model.AdapterHandler;
import com.example.androidb3.features.common.model.ListItem;
import com.example.androidb3.features.database.model.exception.DaoOperationException;
import com.example.androidb3.features.studentSelection.contract.StudentContract;
import com.example.androidb3.features.studentSelection.data.entity.StudentEntity;
import com.example.androidb3.features.studentSelection.model.Student;
import com.example.androidb3.features.yearSelection.model.Year;
import com.example.androidb3.features.yearSelection.model.exception.EmptyDialogInputException;
import com.example.androidb3.features.yearSelection.view.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentPresenter implements StudentContract.Presenter, AdapterHandler {
    private ListAdapter adapter = null;
    private StudentContract.View view = null;
    private StudentContract.Repository repository = null;
    private Year currentYear = null;

    public StudentPresenter(StudentContract.View view, StudentContract.Repository repository){
        this.view = view;
        this.repository = repository;
    }

    public void onAddDialogConfirm(String input){
        try{
            if(input.isEmpty()) throw new EmptyDialogInputException("Le champ doit être rempli.");
            Student student = new Student(input, input, input, currentYear.getId());
            repository.insertAll(student.toEntity());
            adapter.addItem(student);
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

    public void onViewCreated(Intent intent){
        try{
            currentYear = (Year)intent.getSerializableExtra(IntentExtraKeys.YEAR_KEY);
            List<StudentEntity> entities = repository.getAllByYearId(currentYear.getId());
            for (StudentEntity entity : entities){
                if(entity.firstName == null || entity.firstName.isEmpty()) continue;
                if(entity.lastName == null || entity.lastName.isEmpty()) continue;
                if(entity.registration == null || entity.registration.isEmpty()) continue;
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

    }
}
