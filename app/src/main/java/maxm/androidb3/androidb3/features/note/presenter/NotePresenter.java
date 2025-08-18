package maxm.androidb3.androidb3.features.note.presenter;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import maxm.androidb3.androidb3.common.keys.IntentExtraKeys;
import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.note.contract.NoteContract;
import maxm.androidb3.androidb3.features.note.data.entity.NoteEntity;
import maxm.androidb3.androidb3.features.note.model.AllNotes;
import maxm.androidb3.androidb3.features.note.model.Note;
import maxm.androidb3.androidb3.features.note.model.exception.AlreadyExistingException;
import maxm.androidb3.androidb3.features.student.model.Student;
import maxm.androidb3.androidb3.features.test.model.Test;
import maxm.androidb3.androidb3.features.year.model.exception.EmptyDialogInputException;

public class NotePresenter implements NoteContract.Presenter { //#TODO fix la moyenne (on la voit bien sur les évaluations parents mais pas les enfants

    private NoteContract.View view = null;
    private NoteContract.Repository repository = null;
    private Student currentStudent = null;
    private Test currentTest = null;

    public NotePresenter(NoteContract.View view, NoteContract.Repository repository){
        this.view = view;
        this.repository = repository;
    }

    public void onAddDialogConfirm(double input){
        try{
            if(input < 0) throw new EmptyDialogInputException("Le champs est incorrect");
            Note note = new Note(input, currentTest.getMaxPoints(), currentTest.getId(), currentStudent.getId());
            long insertedId = repository.insert(note.toEntity());
            note.setId(insertedId);
            view.setNoteText(note.toString());
            view.setDeleteButtonVisibility(VISIBLE);
            view.closeDialog();
        } catch (EmptyDialogInputException | DaoOperationException e) {
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("ERROR_STUDENT", details);
            view.showDialogError(details);
        } catch (AlreadyExistingException e){
            view.showReplaceDialog(input);
        }
    }

    public void onViewCreated(Intent intent){
        try{
            currentTest = (Test)intent.getSerializableExtra(IntentExtraKeys.TEST_KEY);
            currentStudent = (Student)intent.getSerializableExtra(IntentExtraKeys.STUDENT_KEY);
            NoteEntity entity = repository.getByStudentAndTestId(currentStudent.getId(), currentTest.getId());
            if(entity == null || !entity.isValid()){
                view.setDeleteButtonVisibility(INVISIBLE);
                return;
            }
            Note note = entity.toModel();
            view.setNoteText(note.toString());
            view.setDeleteButtonVisibility(VISIBLE);
            List<NoteEntity> notesEntities = repository.getNotesForStudentAndTestWithChildren(currentStudent.getId(), currentTest.getId());
            List<Note> notesList = new ArrayList<Note>();
            for(NoteEntity noteEntity : notesEntities){
                Log.d("TEST_LIST", "RETRIEVED FOLLOWING NOTE : " + noteEntity.toModel().toString() + " - " + noteEntity.toModel().getPoints() + " - " + noteEntity.toModel().getMaxPoints());
                notesList.add(noteEntity.toModel());
            }
            AllNotes allNotes = new AllNotes(notesList);
            view.setAverageText("Moyenne (comprenant les sous-évaluations), pondérée sur 20 : " + allNotes.getAverageNote() + " / 20");
        } catch (DaoOperationException e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("ERROR_STUDENT", details);
            view.showError(details);
        }
    }

    public void onReplaceConfirm(double input){
        try{
            Note note = new Note(input, currentTest.getMaxPoints(), currentTest.getId(), currentStudent.getId());
            long insertedId = repository.replace(note.toEntity());
            note.setId(insertedId);
            view.setNoteText(note.toString());
            view.setDeleteButtonVisibility(VISIBLE);
            view.closeReplaceDialog();
        }catch (DaoOperationException e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("ERROR_STUDENT", details);
            view.showError(details);
        }
    }

    public void onDialogShown(){
        view.setupDialogValues(0, currentTest.getMaxPoints());
    }

    public void onDeleteConfirm(){
        try{
            repository.deleteByStudentAndTestId(currentStudent.getId(), currentTest.getId());
            view.setNoteText("Note supprimée");
            view.setDeleteButtonVisibility(INVISIBLE);
            view.closeDeleteDialog();
        }catch (DaoOperationException e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("ERROR_STUDENT", details);
            view.showError(details);
        }
    }
}
