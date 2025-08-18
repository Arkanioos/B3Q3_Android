package maxm.androidb3.androidb3.features.classSelection.contract;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import maxm.androidb3.androidb3.features.classSelection.data.entity.ClassEntity;
import maxm.androidb3.androidb3.features.classSelection.model.Class;
import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;

import java.util.List;

public interface ClassContract {
    interface View {
        void closeDialog();
        void showError(String details);
        void showDialogError(String details);
        void loadStudentActivity(Class clickedItem);
    }

    interface Presenter {
        void onViewCreated(Intent intent);
        void onListCreated(RecyclerView list);
        void onAddDialogConfirm(String input);
    }

    interface Repository {
        List<ClassEntity> getAllById(long yearId) throws DaoOperationException;
        long insert(ClassEntity classes) throws DaoOperationException;
    }
}
