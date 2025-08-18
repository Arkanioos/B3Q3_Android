package maxm.androidb3.androidb3.features.note.contract;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.note.data.entity.NoteEntity;

public interface NoteContract {
    interface View{
        void closeDialog();
        void closeReplaceDialog();
        void showDialogError(String details);
        void showError(String details);
        void setupDialogValues(int minPoints, double maxPoints);
        void setNoteText(String text);
        void showReplaceDialog(double input);
        void closeDeleteDialog();
        void setDeleteButtonVisibility(int visibilityIndex);
        void setAverageText(String text);
    }

    interface Presenter{
        void onAddDialogConfirm(double input);
        void onViewCreated(Intent intent);
        void onDialogShown();
        void onReplaceConfirm(double input);
        void onDeleteConfirm();
    }

    interface Repository{
        long insert(NoteEntity note) throws DaoOperationException;
        long replace(NoteEntity note) throws DaoOperationException;
        NoteEntity getByStudentAndTestId(long studentId, long testId) throws DaoOperationException;
        void deleteByStudentAndTestId(long studentId, long testId) throws DaoOperationException;
        List<NoteEntity> getNotesForStudentAndTestWithChildren(long studentId, long testId) throws DaoOperationException;
    }
}
