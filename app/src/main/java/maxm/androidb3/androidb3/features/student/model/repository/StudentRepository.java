package maxm.androidb3.androidb3.features.student.model.repository;

import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.student.contract.StudentContract;
import maxm.androidb3.androidb3.features.student.data.dao.StudentDao;
import maxm.androidb3.androidb3.features.student.data.entity.StudentEntity;

import java.util.List;

public class StudentRepository implements StudentContract.Repository {
    private StudentDao dao = null;

    public StudentRepository(StudentDao dao){
        this.dao = dao;
    }

    public long insert(StudentEntity student) throws DaoOperationException{
        try{
            return dao.insert(student);
        } catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }

    public List<StudentEntity> getAllByYearId(long yearId) throws DaoOperationException{
        try{
            return dao.getAllByYearId(yearId);
        } catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }

    public long getYearIdForTest(long testId) throws DaoOperationException{
        try{
            return dao.getYearIdForTest(testId);
        } catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }
}
