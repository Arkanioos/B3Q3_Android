package maxm.androidb3.androidb3.features.student.presenter;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import maxm.androidb3.androidb3.common.keys.IntentExtraKeys;
import maxm.androidb3.androidb3.features.common.model.AdapterHandler;
import maxm.androidb3.androidb3.features.common.model.ListItem;
import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.student.contract.StudentContract;
import maxm.androidb3.androidb3.features.student.data.entity.StudentEntity;
import maxm.androidb3.androidb3.features.student.model.Student;
import maxm.androidb3.androidb3.features.classSelection.model.Class;
import maxm.androidb3.androidb3.features.test.model.Test;
import maxm.androidb3.androidb3.features.year.model.exception.EmptyDialogInputException;
import maxm.androidb3.androidb3.features.common.model.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentPresenter implements StudentContract.Presenter, AdapterHandler {
    private ListAdapter adapter = null;
    private StudentContract.View view = null;
    private StudentContract.Repository repository = null;
    private Test currentTest = null;

    public StudentPresenter(StudentContract.View view, StudentContract.Repository repository){
        this.view = view;
        this.repository = repository;
    }

    public void onAddDialogConfirm(String firstname, String lastname, String registration){
        try{
            if(firstname.isEmpty() || lastname.isEmpty() || registration.isEmpty())
                throw new EmptyDialogInputException("Les champs doivent être rempli.");
            long yearId = repository.getYearIdForTest(currentTest.getId());
            Student student = new Student(firstname, lastname, registration, yearId);
            long insertedId = repository.insert(student.toEntity());
            student.setId(insertedId);
            adapter.addItem(student);
            view.closeDialog();
        } catch (EmptyDialogInputException | DaoOperationException e) {
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

    public void onViewCreated(Intent intent){
        try{
            currentTest = (Test)intent.getSerializableExtra(IntentExtraKeys.TEST_KEY);
            long yearId = repository.getYearIdForTest(currentTest.getId());
            List<StudentEntity> entities = repository.getAllByYearId(yearId);
            for (StudentEntity entity : entities){
                if(!entity.isValid()) continue;
                adapter.addItem(entity.toModel());
            }
        } catch (DaoOperationException e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("ERROR_STUDENT", details);
            view.showError(details);
        }
    }

    public void onItemClicked(ListItem item){
        Student student = (Student)item;
        view.loadNotesActivity(student, currentTest);
    }
}
