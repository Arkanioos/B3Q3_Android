package maxm.androidb3.androidb3.features.test.model.repository;

import java.util.List;

import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.test.contract.TestContract;
import maxm.androidb3.androidb3.features.test.data.dao.TestDao;
import maxm.androidb3.androidb3.features.test.data.entity.TestEntity;

public class TestRepository implements TestContract.Repository {
    private TestDao dao = null;

    public TestRepository(TestDao dao) { this.dao = dao; }

    public long insert(TestEntity test) throws DaoOperationException{
        try{
            return dao.insert(test);
        }catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }

    public List<TestEntity> getAllByClassId(long classId) throws DaoOperationException{
        try{
            return dao.getAllByClassId(classId);
        } catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }
}
