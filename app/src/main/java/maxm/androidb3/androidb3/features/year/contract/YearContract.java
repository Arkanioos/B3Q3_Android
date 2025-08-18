package maxm.androidb3.androidb3.features.year.contract;

import androidx.recyclerview.widget.RecyclerView;

import maxm.androidb3.androidb3.features.database.model.exception.DaoOperationException;
import maxm.androidb3.androidb3.features.year.data.entity.YearEntity;
import maxm.androidb3.androidb3.features.year.model.Year;

import java.util.List;

public interface YearContract {
    interface View {
        void showDialogError(String details);
        void closeDialog();
        void showError(String details);
        void loadClassActivity(Year clickedItem);
    }

    interface Presenter {
        void onAddDialogConfirm(String input);
        void onListCreated(RecyclerView list);
        void onViewCreated();
    }

    interface Repository {
        long insert(YearEntity year) throws DaoOperationException;
        List<YearEntity> getAll() throws DaoOperationException;
    }
}
