package maxm.androidb3.androidb3.features.yearSelection.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import maxm.androidb3.androidb3.features.yearSelection.data.entity.YearEntity;

import java.util.List;

@Dao
public interface YearDao {
    @Insert
    long insert(YearEntity year); // stratégie de conflit par défaut = abort

    @Delete
    void delete(YearEntity year);

    @Query("SELECT * FROM year_table ORDER BY id ASC")
    List<YearEntity> getAll();
}
