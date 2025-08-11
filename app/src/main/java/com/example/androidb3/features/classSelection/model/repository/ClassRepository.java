package com.example.androidb3.features.classSelection.model.repository;

import com.example.androidb3.features.classSelection.contract.ClassContract;
import com.example.androidb3.features.classSelection.data.dao.ClassDao;
import com.example.androidb3.features.classSelection.data.entity.ClassEntity;
import com.example.androidb3.features.database.model.exception.DaoOperationException;

import java.util.List;

public class ClassRepository implements ClassContract.Repository {
    private ClassDao dao = null;

    public ClassRepository(ClassDao dao){
        this.dao = dao;
    }

    public void insertAll(ClassEntity... classes) throws DaoOperationException {
        try{
            dao.insertAll(classes);
        } catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }

    public List<ClassEntity> getAllById(long yearId) throws DaoOperationException{
        try{
            return dao.getAllByYearId(yearId);
        } catch (Exception e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            throw new DaoOperationException(details, e);
        }
    }

}
