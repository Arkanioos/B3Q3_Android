package maxm.androidb3.androidb3.features.student.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import maxm.androidb3.androidb3.features.student.data.entity.StudentEntity;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    long insert(StudentEntity student);

    @Query("SELECT * FROM student_table WHERE yearId = :yearId")
    List<StudentEntity> getAllByYearId(long yearId);

    @Query("SELECT y.id " +
            "FROM test_table AS t " +
            "JOIN class_table AS c ON t.classId = c.id " +
            "JOIN year_table AS y ON c.yearId = y.id " +
            "WHERE t.id = :testId")
    long getYearIdForTest(long testId);

}
