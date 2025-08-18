package maxm.androidb3.androidb3.features.year.model.repository;

import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.year.contract.YearContract;
import maxm.androidb3.androidb3.features.year.data.dao.YearDao;
import maxm.androidb3.androidb3.features.year.data.entity.YearEntity;

import java.util.List;

public class YearRepository implements YearContract.Repository {
    private YearDao dao = null;

    public YearRepository(YearDao dao){
        this.dao = dao;
    }

    public long insert(YearEntity year) throws DaoOperationException{
        try{
            return dao.insert(year);
        } catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }

    public List<YearEntity> getAll() throws DaoOperationException{
        try{
            return dao.getAll();
        } catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }
}
