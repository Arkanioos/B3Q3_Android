package maxm.androidb3.androidb3.features.startup.contract;

import android.content.Context;

public interface StartupContract {
    interface View {
        void showError(String details);
        void loadNextActivity();
    }

    interface Model {

    }

    interface Presenter {
        void onViewCreated(Context context);
        void onRetryClicked(Context context);
    }
}
