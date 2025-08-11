package com.example.androidb3.features.yearSelection.contract;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidb3.features.database.model.exception.DaoOperationException;
import com.example.androidb3.features.yearSelection.data.entity.YearEntity;
import com.example.androidb3.features.yearSelection.model.Year;

import java.util.List;

public interface YearContract {
    interface View {
        void showDialogError(String details);
        void closeDialog();
        void showError(String details);
        void loadClassActivity(Year clickedItem);
    }

    interface Presenter {
        void onAddDialogConfirm(String input);
        void onListCreated(RecyclerView list);
        void onViewCreated();
    }

    interface Repository {
        long insert(YearEntity year) throws DaoOperationException;
        List<YearEntity> getAll() throws DaoOperationException;
    }
}
