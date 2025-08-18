package maxm.androidb3.androidb3.features.student.contract;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.student.data.entity.StudentEntity;
import maxm.androidb3.androidb3.features.student.model.Student;
import maxm.androidb3.androidb3.features.test.model.Test;

import java.util.List;

public interface StudentContract {
    interface View {
        void closeDialog();
        void showDialogError(String details);
        void showError(String details);
        void loadNotesActivity(Student student, Test test);
    }

    interface Presenter {
        void onAddDialogConfirm(String firstname, String lastname, String registration);
        void onListCreated(RecyclerView list);
        void onViewCreated(Intent intent);
    }

    interface Repository {
        long insert(StudentEntity student) throws DaoOperationException;
        List<StudentEntity> getAllByYearId(long yearId) throws DaoOperationException;
        long getYearIdForTest(long testId) throws DaoOperationException;

    }
}
