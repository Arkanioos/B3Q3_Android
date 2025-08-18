package maxm.androidb3.androidb3.features.note.view;

import static android.view.View.VISIBLE;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
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
import maxm.androidb3.androidb3.features.note.contract.NoteContract;

public class NoteActivity extends AppCompatActivity implements NoteContract.View {
    private static double NUMBER_PICKER_STEP = 0.5;
    private FloatingActionButton addButton = null;
    private FloatingActionButton deleteButton = null;
    private TextView noteText = null;
    private TextView averageText = null;

    private Dialog dialog = null;
    private NumberPicker dialogInput = null;
    private TextView dialogErrorDetails = null;
    private Button dialogCancelButton = null;
    private Button dialogConfirmButton = null;

    private Dialog replaceDialog = null;
    private Button replaceDialogCancelButton = null;
    private Button replaceDialogConfirmButton = null;

    private Dialog deleteDialog = null;
    private Button deleteDialogCancelButton = null;
    private Button deleteDialogConfirmButton = null;

    private NoteContract.Presenter presenter = null;

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
        setContentView(R.layout.activity_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeView(){
        initializeDialog();
        initializeReplaceDialog();
        initializeDeleteDialog();

        noteText = findViewById(R.id.noteText);
        averageText = findViewById(R.id.averageText);

        addButton = findViewById(R.id.addItem);
        addButton.setOnClickListener(view -> {
            showCustomDialog();
        });

        deleteButton = findViewById(R.id.deleteItem);
        deleteButton.setOnClickListener(view -> {
            showDeleteDialog();
        });
    }

    public void setNoteText(String text) { noteText.setText(text); }

    private void initializeDialog(){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_note_input);
        dialogInput = dialog.findViewById(R.id.numberInput);
        dialogErrorDetails = dialog.findViewById(R.id.inputDialogErrorDetails);
        dialogCancelButton = dialog.findViewById(R.id.cancelButton);
        dialogConfirmButton = dialog.findViewById(R.id.confirmButton);
        dialogInput.setWrapSelectorWheel(true);

        dialogCancelButton.setOnClickListener(v -> {
            System.out.println("dialog cancelled");
            closeDialog();
        });

        dialogConfirmButton.setOnClickListener(v -> {
            int numberInput = dialogInput.getValue();
            double realValue = numberInput * NUMBER_PICKER_STEP;
            presenter.onAddDialogConfirm(realValue);
        });
    }

    private void initializeReplaceDialog(){
        replaceDialog = new Dialog(this);
        replaceDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        replaceDialog.setCancelable(true);
        replaceDialog.setContentView(R.layout.dialog_note_replace);
        replaceDialogCancelButton = replaceDialog.findViewById(R.id.cancelButton);
        replaceDialogConfirmButton = replaceDialog.findViewById(R.id.confirmButton);

        replaceDialogCancelButton.setOnClickListener(v -> {
            System.out.println("dialog cancelled");
            closeReplaceDialog();
        });
    }

    private void initializeDeleteDialog(){
        deleteDialog = new Dialog(this);
        deleteDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        deleteDialog.setCancelable(true);
        deleteDialog.setContentView(R.layout.dialog_note_delete);
        deleteDialogCancelButton = deleteDialog.findViewById(R.id.cancelButton);
        deleteDialogConfirmButton = deleteDialog.findViewById(R.id.confirmButton);

        deleteDialogCancelButton.setOnClickListener(v -> {
            System.out.println("dialog cancelled");
            closeDeleteDialog();
        });

        deleteDialogConfirmButton.setOnClickListener(v -> {
            presenter.onDeleteConfirm();
        });
    }

    public void closeDialog(){
        dialog.dismiss();
    }

    public void closeReplaceDialog() { replaceDialog.dismiss(); }

    public void closeDeleteDialog() { deleteDialog.dismiss(); }

    public void showDialogError(String details){
        dialogErrorDetails.setText("");
        dialogInput.setValue(20);
        dialog.show();
    }

    private void showCustomDialog(){
        presenter.onDialogShown();
        dialogErrorDetails.setText("");
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

    public void setupDialogValues(int minPoints, double maxPoints){
        int count = (int) (maxPoints / NUMBER_PICKER_STEP) + 1;
        String[] values = new String[count];
        for(int i = 0; i < count; i++){
            values[i] = String.format("%.1f", i * NUMBER_PICKER_STEP);
        }
        dialogInput.setMinValue(0);
        dialogInput.setMaxValue(count - 1);
        dialogInput.setDisplayedValues(values);
    }

    public void showReplaceDialog(double initialInput){
        closeDialog();

        replaceDialogConfirmButton.setOnClickListener(v -> {
            presenter.onReplaceConfirm(initialInput);
        });

        replaceDialog.show();
    }

    public void showDeleteDialog(){
        deleteDialog.show();
    }

    public void setAverageText(String text){
        averageText.setText(text);
    }

    public void setDeleteButtonVisibility(int visibilityIndex) { deleteButton.setVisibility(visibilityIndex); }
}