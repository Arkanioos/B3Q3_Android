package maxm.androidb3.androidb3.features.note.model.repository;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import java.util.List;

import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.note.contract.NoteContract;
import maxm.androidb3.androidb3.features.note.data.dao.NoteDao;
import maxm.androidb3.androidb3.features.note.data.entity.NoteEntity;
import maxm.androidb3.androidb3.features.note.model.exception.AlreadyExistingException;

public class NoteRepository implements NoteContract.Repository {
    private NoteDao dao = null;

    public NoteRepository(NoteDao dao) { this.dao = dao; }

    public long insert(NoteEntity note) throws DaoOperationException{
        try{
            return dao.insert(note);
        }catch (SQLiteConstraintException e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new AlreadyExistingException(details, e);
        }
        catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }

    public long replace(NoteEntity note) throws DaoOperationException{
        try{
            return dao.replace(note);
        }catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }

    public NoteEntity getByStudentAndTestId(long studentId, long testId) throws DaoOperationException{
        try{
            return dao.getByStudentAndTestId(studentId, testId);
        } catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }

    public void deleteByStudentAndTestId(long studentId, long testId) throws DaoOperationException{
        try{
            dao.deleteByStudentAndTestId(studentId, testId);
        }catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }

    public List<NoteEntity> getNotesForStudentAndTestWithChildren(long studentId, long testId) throws DaoOperationException{
        try{
            return dao.getNotesForStudentAndTestWithChildren(studentId, testId);
        }catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }
}
