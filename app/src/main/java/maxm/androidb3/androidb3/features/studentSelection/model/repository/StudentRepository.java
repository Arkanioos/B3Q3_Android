package maxm.androidb3.androidb3.features.studentSelection.model.repository;

import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.studentSelection.contract.StudentContract;
import maxm.androidb3.androidb3.features.studentSelection.data.dao.StudentDao;
import maxm.androidb3.androidb3.features.studentSelection.data.entity.StudentEntity;

import java.util.List;

public class StudentRepository implements StudentContract.Repository {
    private StudentDao dao = null;

    public StudentRepository(StudentDao dao){
        this.dao = dao;
    }

    public void insertAll(StudentEntity... students) throws DaoOperationException{
        try{
            dao.insertAll(students);
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
}
