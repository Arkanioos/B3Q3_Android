package maxm.androidb3.androidb3.features.startup.presenter;

import android.content.Context;
import android.util.Log;

import maxm.androidb3.androidb3.features.database.data.AppDatabase;
import maxm.androidb3.androidb3.features.database.model.exception.DatabaseCreationException;
import maxm.androidb3.androidb3.features.startup.contract.StartupContract;

public class StartupPresenter implements StartupContract.Presenter {
    private StartupContract.View view = null;

    public StartupPresenter(StartupContract.View view){
        this.view = view;
    }

    public void onViewCreated(Context context){
        try {
            AppDatabase.createDatabase(context);
            view.loadNextActivity();
        } catch (DatabaseCreationException e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("Error", details);
            view.showError(details);
        }
    }

    public void onRetryClicked(Context context){
        try{
            if(AppDatabase.getInstance() == null) AppDatabase.createDatabase(context);
            view.loadNextActivity();
        }catch (IllegalStateException | DatabaseCreationException e){
            String details = e.getMessage();
            if(details == null || details.isEmpty()) details = "No details";
            Log.e("Error", details);
            view.showError(details);
        }
    }
}
