package com.example.androidb3.features.studentSelection.contract;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidb3.features.database.model.exception.DaoOperationException;
import com.example.androidb3.features.studentSelection.data.entity.StudentEntity;

import java.util.List;

public interface StudentContract {
    interface View {
        void closeDialog();
        void showDialogError(String details);
        void showError(String details);
    }

    interface Presenter {
        void onAddDialogConfirm(String input);
        void onListCreated(RecyclerView list);
        void onViewCreated(Intent intent);
    }

    interface Repository {
        void insertAll(StudentEntity... students) throws DaoOperationException;
        List<StudentEntity> getAllByYearId(long yearId) throws DaoOperationException;
    }
}
