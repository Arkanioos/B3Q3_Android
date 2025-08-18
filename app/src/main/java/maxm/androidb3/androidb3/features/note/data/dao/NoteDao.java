package maxm.androidb3.androidb3.features.note.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import maxm.androidb3.androidb3.features.note.data.entity.NoteEntity;

@Dao
public interface NoteDao {
    @Insert
    long insert(NoteEntity note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long replace(NoteEntity note);

    @Query("SELECT * FROM note_table WHERE studentId = :studentId AND testId = :testId")
    NoteEntity getByStudentAndTestId(long studentId, long testId);

    @Query("DELETE FROM note_table WHERE studentId = :studentId AND testId = :testId")
    void deleteByStudentAndTestId(long studentId, long testId);

    @Query("SELECT * FROM note_table " +
            "WHERE studentId = :studentId " +
            "AND testId IN ( " +
            "    SELECT id FROM test_table " +
            "    WHERE id = :testId " +
            "    OR parentId = :testId " +
            "    OR id = (SELECT parentId FROM test_table WHERE id = :testId) " +
            "    OR parentId = (SELECT parentId FROM test_table WHERE id = :testId) " +
            ")")
    List<NoteEntity> getNotesForStudentAndTestWithChildren(long studentId, long testId);


    @Query("SELECT * FROM note_table")
    List<NoteEntity> getAll();
}
