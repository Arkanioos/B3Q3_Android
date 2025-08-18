package maxm.androidb3.androidb3.features.classSelection.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import maxm.androidb3.androidb3.features.classSelection.model.Class;
import maxm.androidb3.androidb3.features.year.data.entity.YearEntity;

@Entity(
        tableName = "class_table",
        foreignKeys = @ForeignKey(
                entity = YearEntity.class,
                parentColumns = "id",      // colonne dans year_table
                childColumns = "yearId",   // colonne dans class_table
                onDelete = ForeignKey.CASCADE // supprime les classes si l'année est supprimée
        )
)
public class ClassEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public long yearId;

    public long getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getYearId() { return yearId; }
    public void setYearId(int yearId) { this.yearId = yearId; }

    public ClassEntity(){}

    public ClassEntity(String name, long yearId){
        this.name = name;
        this.yearId = yearId;
    }

    public boolean isValid(){
        return name != null && !name.trim().isEmpty();
    }

    public Class toModel(){
        return new Class(name, id, yearId);
    }
}
