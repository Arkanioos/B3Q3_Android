package maxm.androidb3.androidb3.features.classSelection.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import maxm.androidb3.androidb3.features.classSelection.data.entity.ClassEntity;

import java.util.List;

@Dao
public interface ClassDao {
    @Insert
    void insertAll(ClassEntity... classes); // stratégie de conflit par défaut = abort

    @Delete
    void delete(ClassEntity className);

    @Query("SELECT * FROM class_table WHERE yearId = :yearId")
    List<ClassEntity> getAllByYearId(long yearId);
}
