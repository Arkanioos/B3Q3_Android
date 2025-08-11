package com.example.androidb3.common.factory;

import android.content.Intent;

import com.example.androidb3.features.classSelection.contract.ClassContract;
import com.example.androidb3.features.classSelection.data.dao.ClassDao;
import com.example.androidb3.features.classSelection.model.repository.ClassRepository;
import com.example.androidb3.features.classSelection.presenter.ClassPresenter;
import com.example.androidb3.features.database.data.AppDatabase;
import com.example.androidb3.features.studentSelection.contract.StudentContract;
import com.example.androidb3.features.studentSelection.data.dao.StudentDao;
import com.example.androidb3.features.studentSelection.model.repository.StudentRepository;
import com.example.androidb3.features.studentSelection.presenter.StudentPresenter;
import com.example.androidb3.features.yearSelection.contract.YearContract;
import com.example.androidb3.features.yearSelection.data.dao.YearDao;
import com.example.androidb3.features.yearSelection.model.repository.YearRepository;
import com.example.androidb3.features.yearSelection.presenter.YearPresenter;
import com.example.androidb3.features.startup.contract.StartupContract;
import com.example.androidb3.features.startup.presenter.StartupPresenter;

public class PresenterFactory {
    public static YearContract.Presenter providePresenter(YearContract.View view){
        //YearContract.Model model = new Model();
        YearDao dao = AppDatabase.getInstance().yearDao();
        YearContract.Repository repository = new YearRepository(dao);
        return new YearPresenter(view, repository);
        //return new YearPresenter(view, model);
    }

    public static StartupContract.Presenter providePresenter(StartupContract.View view){
        return new StartupPresenter(view);
    }

    public static ClassContract.Presenter providePresenter(ClassContract.View view){
        ClassDao dao = AppDatabase.getInstance().classDao();
        ClassContract.Repository repository = new ClassRepository(dao);
        return new ClassPresenter(view, repository);
    }

    public static StudentContract.Presenter providePresenter(StudentContract.View view){
        StudentDao dao = AppDatabase.getInstance().studentDao();
        StudentContract.Repository repository = new StudentRepository(dao);
        return new StudentPresenter(view, repository);
    }
}
