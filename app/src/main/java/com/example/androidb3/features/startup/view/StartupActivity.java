package com.example.androidb3.features.startup.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidb3.R;
import com.example.androidb3.common.factory.PresenterFactory;
import com.example.androidb3.features.yearSelection.view.YearActivity;
import com.example.androidb3.features.startup.contract.StartupContract;

public class StartupActivity extends AppCompatActivity implements StartupContract.View {

    private Button retryButton = null;
    private StartupContract.Presenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeActivity();
        initializeView();

        presenter = PresenterFactory.providePresenter(this);
        presenter.onViewCreated(getApplicationContext());
    }

    private void initializeActivity(){
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_startup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeView(){
        retryButton = findViewById(R.id.startupRetryButton);

        retryButton.setOnClickListener(v -> {
            presenter.onRetryClicked(getApplicationContext());
        });
    }

    public void showError(String details){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_error);

        TextView errorDetails = dialog.findViewById(R.id.errorDetails);
        errorDetails.setText(details);

        dialog.show();
    }

    public void loadNextActivity(){
        Intent intent = new Intent(this, YearActivity.class);
        startActivity(intent);
    }
}