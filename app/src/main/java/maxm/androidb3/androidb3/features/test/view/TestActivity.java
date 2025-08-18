package maxm.androidb3.androidb3.features.test.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidb3.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import maxm.androidb3.androidb3.common.factory.PresenterFactory;
import maxm.androidb3.androidb3.common.keys.IntentExtraKeys;
import maxm.androidb3.androidb3.features.student.view.StudentActivity;
import maxm.androidb3.androidb3.features.test.contract.TestContract;
import maxm.androidb3.androidb3.features.test.model.Test;

public class TestActivity extends AppCompatActivity implements TestContract.View {

    private static double NUMBER_PICKER_STEP = 0.5;
    private static int NUMBER_PICKER_MAX_VALUE = 100;
    private FloatingActionButton addButton = null;
    private RecyclerView itemsList = null;

    private Dialog dialog = null;
    private EditText dialogInput = null;
    private CheckBox dialogCheckbox = null;
    private NumberPicker dialogNumberInput = null;
    private TextView dialogErrorDetails = null;
    private Button dialogCancelButton = null;
    private Button dialogConfirmButton = null;

    private Dialog parentDialog = null;
    private RecyclerView parentList = null;

    private TestContract.Presenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = PresenterFactory.providePresenter(this);

        initializeActivity();
        initializeView();

        presenter.onViewCreated(getIntent());
    }

    private void initializeActivity(){
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
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
        dialog.setContentView(R.layout.dialog_test_input);
        dialogInput = dialog.findViewById(R.id.textInput);
        dialogNumberInput = dialog.findViewById(R.id.numberInput);

        int count = (int) (NUMBER_PICKER_MAX_VALUE / NUMBER_PICKER_STEP) + 1;
        String[] values = new String[count];
        for(int i = 0; i < count; i++){
            values[i] = String.format("%.1f", i * NUMBER_PICKER_STEP);
        }
        dialogNumberInput.setMinValue(0);
        dialogNumberInput.setMaxValue(count - 1);
        dialogNumberInput.setDisplayedValues(values);
        dialogCheckbox = dialog.findViewById(R.id.testCheckbox);

        parentDialog = new Dialog(this);
        parentDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        parentDialog.setCancelable(true);
        parentDialog.setContentView(R.layout.dialog_test_parent);
        parentList = parentDialog.findViewById(R.id.testsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        parentList.setLayoutManager(layoutManager);
        presenter.onParentListCreated(parentList);

        dialogErrorDetails = dialog.findViewById(R.id.inputDialogErrorDetails);
        dialogCancelButton = dialog.findViewById(R.id.cancelButton);
        dialogConfirmButton = dialog.findViewById(R.id.confirmButton);

        dialogCancelButton.setOnClickListener(v -> {
            System.out.println("dialog cancelled");
            closeDialog();
        });

        dialogConfirmButton.setOnClickListener(v -> {
            String input = dialogInput.getText().toString();
            boolean isChecked = dialogCheckbox.isChecked();
            int numberInput = dialogNumberInput.getValue();
            double realValue = numberInput * NUMBER_PICKER_STEP;
            presenter.onAddDialogConfirm(input, realValue, isChecked);
        });
    }

    private void initializeList(){
        itemsList = findViewById(R.id.itemsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        itemsList.setLayoutManager(layoutManager);
        presenter.onListCreated(itemsList);
    }

    public void closeDialog(){
        dialog.dismiss();
    }

    public void closeParentDialog(){
        parentDialog.dismiss();
    }

    public void showDialogError(String details){
        dialogErrorDetails.setText("");
        dialogInput.setText("");
        dialogNumberInput.setValue(20);
        dialog.show();
    }

    private void showCustomDialog(){
        dialogErrorDetails.setText("");
        dialogInput.setText("");
        dialog.show();
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

    public void openParentDialog(){
        dialog.dismiss();
        parentDialog.show();
        presenter.onParentDialogShown();
    }

    public void loadStudentActivity(Test test){
        Intent intent = new Intent(this, StudentActivity.class);
        intent.putExtra(IntentExtraKeys.TEST_KEY, test);
        Log.d("TEST_TEST", "LOADING STUDENT ACTIVITY");
        startActivity(intent);
    }
}