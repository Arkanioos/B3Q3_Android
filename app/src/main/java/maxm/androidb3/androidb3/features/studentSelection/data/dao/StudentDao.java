package maxm.androidb3.androidb3.features.studentSelection.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import maxm.androidb3.androidb3.features.studentSelection.data.entity.StudentEntity;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertAll(StudentEntity... students);

    @Query("SELECT * FROM student_table WHERE yearId = :yearId")
    List<StudentEntity> getAllByYearId(long yearId);
}
