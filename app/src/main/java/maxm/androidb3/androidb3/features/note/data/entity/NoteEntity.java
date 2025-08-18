package maxm.androidb3.androidb3.features.note.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import maxm.androidb3.androidb3.features.note.model.Note;
import maxm.androidb3.androidb3.features.student.data.entity.StudentEntity;
import maxm.androidb3.androidb3.features.test.data.entity.TestEntity;

@Entity(
        tableName = "note_table",
        foreignKeys = {
                @ForeignKey(
                        entity = TestEntity.class,
                        parentColumns = "id",       // colonne primaire dans test_table
                        childColumns = "testId",    // colonne dans note_table
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = StudentEntity.class,
                        parentColumns = "id",       // colonne primaire dans student_table
                        childColumns = "studentId", // colonne dans note_table
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {
                @Index(value = {"studentId", "testId"}, unique = true)
        }
)
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public double points;
    public double maxPoints;
    public long testId;
    public long studentId;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getTestId() { return testId; }
    public void setTestId(long testId) { this.testId = testId; }

    public NoteEntity() {}

    public NoteEntity(double points, double maxPoints, long testId, long studentId){
        this.points = points;
        this.maxPoints = maxPoints;
        this.testId = testId;
        this.studentId = studentId;
    }

    public boolean isValid(){
        return points >= 0 && maxPoints > 0;
    }

    public Note toModel() { return new Note(points, maxPoints, testId, studentId); }
}
