package maxm.androidb3.androidb3.common.factory;

import maxm.androidb3.androidb3.features.classSelection.contract.ClassContract;
import maxm.androidb3.androidb3.features.classSelection.data.dao.ClassDao;
import maxm.androidb3.androidb3.features.classSelection.model.repository.ClassRepository;
import maxm.androidb3.androidb3.features.classSelection.presenter.ClassPresenter;
import maxm.androidb3.androidb3.features.database.data.AppDatabase;
import maxm.androidb3.androidb3.features.studentSelection.contract.StudentContract;
import maxm.androidb3.androidb3.features.studentSelection.data.dao.StudentDao;
import maxm.androidb3.androidb3.features.studentSelection.model.repository.StudentRepository;
import maxm.androidb3.androidb3.features.studentSelection.presenter.StudentPresenter;
import maxm.androidb3.androidb3.features.yearSelection.contract.YearContract;
import maxm.androidb3.androidb3.features.yearSelection.data.dao.YearDao;
import maxm.androidb3.androidb3.features.yearSelection.model.repository.YearRepository;
import maxm.androidb3.androidb3.features.yearSelection.presenter.YearPresenter;
import maxm.androidb3.androidb3.features.startup.contract.StartupContract;
import maxm.androidb3.androidb3.features.startup.presenter.StartupPresenter;

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
