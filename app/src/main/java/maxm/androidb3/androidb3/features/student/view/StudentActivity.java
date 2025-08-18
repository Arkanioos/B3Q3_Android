package maxm.androidb3.androidb3.features.student.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import maxm.androidb3.androidb3.features.note.view.NoteActivity;
import maxm.androidb3.androidb3.features.test.model.Test;
import maxm.androidb3.androidb3.features.student.contract.StudentContract;
import maxm.androidb3.androidb3.features.student.model.Student;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentActivity extends AppCompatActivity implements StudentContract.View {

    private FloatingActionButton addButton = null;
    private RecyclerView itemsList = null;

    private Dialog dialog = null;
    private EditText firstNameInput = null;
    private EditText lastNameInput = null;
    private EditText registrationInput = null;
    private TextView dialogErrorDetails = null;
    private Button dialogCancelButton = null;
    private Button dialogConfirmButton = null;

    private StudentContract.Presenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TEST_STUDENT", "IN STUDENT ACTIVITY");

        super.onCreate(savedInstanceState);

        presenter = PresenterFactory.providePresenter(this);

        initializeActivity();
        initializeView();

        presenter.onViewCreated(getIntent());
    }

    private void initializeActivity(){
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student);
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
        dialog.setContentView(R.layout.dialog_student_input);
        firstNameInput = dialog.findViewById(R.id.firstnameInput);
        lastNameInput = dialog.findViewById(R.id.lastnameInput);
        registrationInput = dialog.findViewById(R.id.registrationInput);
        dialogErrorDetails = dialog.findViewById(R.id.inputDialogErrorDetails);
        dialogCancelButton = dialog.findViewById(R.id.cancelButton);
        dialogConfirmButton = dialog.findViewById(R.id.confirmButton);

        dialogCancelButton.setOnClickListener(v -> {
            System.out.println("dialog cancelled");
            closeDialog();
        });

        dialogConfirmButton.setOnClickListener(v -> {
            String firstname = firstNameInput.getText().toString();
            String lastname = lastNameInput.getText().toString();
            String registration = registrationInput.getText().toString();
            presenter.onAddDialogConfirm(firstname, lastname, registration);
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

    public void showDialogError(String details){
        dialogErrorDetails.setText("");
        firstNameInput.setText("");
        dialog.show();
    }

    private void showCustomDialog(){
        dialogErrorDetails.setText("");
        firstNameInput.setText("");
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

    public void loadNotesActivity(Student student, Test test){
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra(IntentExtraKeys.STUDENT_KEY, student);
        intent.putExtra(IntentExtraKeys.TEST_KEY, test);
        startActivity(intent);
    }
}