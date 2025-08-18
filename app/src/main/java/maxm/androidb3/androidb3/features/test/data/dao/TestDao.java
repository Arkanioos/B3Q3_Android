package maxm.androidb3.androidb3.features.test.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import maxm.androidb3.androidb3.features.test.data.entity.TestEntity;

@Dao
public interface TestDao {
    @Insert
    long insert(TestEntity note);

    @Query("SELECT * FROM test_table WHERE classId = :classId")
    List<TestEntity> getAllByClassId(long classId);

    @Query("SELECT * FROM test_table WHERE classId = :courseId AND parentId IS NULL")
    List<TestEntity> getRootEvaluations(long courseId);

    @Query("SELECT * FROM test_table WHERE parentId = :parentId")
    List<TestEntity> getSubEvaluations(long parentId);

    @Query("SELECT * FROM test_table")
    List<TestEntity> getAll();
}
