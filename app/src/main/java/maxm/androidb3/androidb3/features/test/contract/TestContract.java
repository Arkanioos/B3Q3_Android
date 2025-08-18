package maxm.androidb3.androidb3.features.test.contract;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.test.data.entity.TestEntity;
import maxm.androidb3.androidb3.features.test.model.Test;

public interface TestContract {
    interface View{
        void closeDialog();
        void closeParentDialog();
        void showDialogError(String details);
        void showError(String details);
        void loadStudentActivity(Test test);
        void openParentDialog();
    }

    interface Presenter{
        void onAddDialogConfirm(String input, double numberInput, boolean isChecked);
        void onListCreated(RecyclerView list);
        void onViewCreated(Intent intent);
        void onParentListCreated(RecyclerView list);
        void onParentDialogShown();
    }

    interface Repository{
        long insert(TestEntity test) throws DaoOperationException;
        List<TestEntity> getAllByClassId(long classId) throws DaoOperationException;
    }
}
