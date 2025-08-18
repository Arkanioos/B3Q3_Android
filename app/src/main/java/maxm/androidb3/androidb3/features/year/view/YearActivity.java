package maxm.androidb3.androidb3.features.year.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidb3.R;
import maxm.androidb3.androidb3.common.factory.PresenterFactory;
import maxm.androidb3.androidb3.common.keys.IntentExtraKeys;
import maxm.androidb3.androidb3.features.classSelection.view.ClassActivity;
import maxm.androidb3.androidb3.features.year.contract.YearContract;
import maxm.androidb3.androidb3.features.year.model.Year;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class YearActivity extends AppCompatActivity implements YearContract.View {

    private FloatingActionButton addButton = null;
    private RecyclerView itemsList = null;

    private Dialog dialog = null;
    private EditText dialogInput = null;
    private TextView dialogErrorDetails = null;
    private Button dialogCancelButton = null;
    private Button dialogConfirmButton = null;

    private YearContract.Presenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = PresenterFactory.providePresenter(this);

        initializeActivity();
        initializeView();

        presenter.onViewCreated();
    }

    private void initializeActivity(){
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_year);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeView(){
        initializeDialog();
        initializeList();

        addButton = findViewById(R.id.addItem);
        addButton.setOnClickListener(view -> {
            showCustomDialog();
        });
    }

    private void initializeDialog(){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_generic_input);
        dialogInput = dialog.findViewById(R.id.textInput);
        dialogErrorDetails = dialog.findViewById(R.id.inputDialogErrorDetails);
        dialogCancelButton = dialog.findViewById(R.id.cancelButton);
        dialogConfirmButton = dialog.findViewById(R.id.confirmButton);

        dialogCancelButton.setOnClickListener(v -> {
            System.out.println("dialog cancelled");
            closeDialog();
        });

        dialogConfirmButton.setOnClickListener(v -> {
            String input = dialogInput.getText().toString();
            System.out.println("dialog input : " + input);
            presenter.onAddDialogConfirm(input);
        });
    }

    private void initializeList(){
        itemsList = findViewById(R.id.itemsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        itemsList.setLayoutManager(layoutManager);
        presenter.onListCreated(itemsList);
    }

    private void showCustomDialog(){
        dialogErrorDetails.setText("");
        dialogInput.setText("");
        dialog.show();
    }

    public void showDialogError(String details){
        dialogErrorDetails.setText(details);
    }

    public void showError(String details) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_error);

        TextView errorDetails = dialog.findViewById(R.id.errorDetails);
        errorDetails.setText(details);

        dialog.show();
    }

    public void closeDialog(){
        dialog.dismiss();
    }

    public void loadClassActivity(Year clickedItem){
        Intent intent = new Intent(this, ClassActivity.class);
        intent.putExtra(IntentExtraKeys.YEAR_KEY, clickedItem);
        startActivity(intent);
    }
}