package maxm.androidb3.androidb3.common.factory;

import maxm.androidb3.androidb3.features.classSelection.contract.ClassContract;
import maxm.androidb3.androidb3.features.classSelection.data.dao.ClassDao;
import maxm.androidb3.androidb3.features.classSelection.model.repository.ClassRepository;
import maxm.androidb3.androidb3.features.classSelection.presenter.ClassPresenter;
import maxm.androidb3.androidb3.features.database.data.AppDatabase;
import maxm.androidb3.androidb3.features.note.contract.NoteContract;
import maxm.androidb3.androidb3.features.note.data.dao.NoteDao;
import maxm.androidb3.androidb3.features.note.model.repository.NoteRepository;
import maxm.androidb3.androidb3.features.note.presenter.NotePresenter;
import maxm.androidb3.androidb3.features.test.contract.TestContract;
import maxm.androidb3.androidb3.features.student.contract.StudentContract;
import maxm.androidb3.androidb3.features.student.data.dao.StudentDao;
import maxm.androidb3.androidb3.features.student.model.repository.StudentRepository;
import maxm.androidb3.androidb3.features.student.presenter.StudentPresenter;
import maxm.androidb3.androidb3.features.test.data.dao.TestDao;
import maxm.androidb3.androidb3.features.test.model.repository.TestRepository;
import maxm.androidb3.androidb3.features.test.presenter.TestPresenter;
import maxm.androidb3.androidb3.features.year.contract.YearContract;
import maxm.androidb3.androidb3.features.year.data.dao.YearDao;
import maxm.androidb3.androidb3.features.year.model.repository.YearRepository;
import maxm.androidb3.androidb3.features.year.presenter.YearPresenter;
import maxm.androidb3.androidb3.features.startup.contract.StartupContract;
import maxm.androidb3.androidb3.features.startup.presenter.StartupPresenter;

public class PresenterFactory {
    public static YearContract.Presenter providePresenter(YearContract.View view){
        YearDao dao = AppDatabase.getInstance().yearDao();
        YearContract.Repository repository = new YearRepository(dao);
        return new YearPresenter(view, repository);
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

    public static TestContract.Presenter providePresenter(TestContract.View view){
        TestDao dao = AppDatabase.getInstance().testDao();
        TestContract.Repository repository = new TestRepository(dao);
        return new TestPresenter(view, repository);
    }

    public static NoteContract.Presenter providePresenter(NoteContract.View view){
        NoteDao dao = AppDatabase.getInstance().noteDao();
        NoteContract.Repository repository = new NoteRepository(dao);
        return new NotePresenter(view, repository);
    }
}
