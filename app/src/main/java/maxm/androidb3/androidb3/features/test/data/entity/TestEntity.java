package maxm.androidb3.androidb3.features.test.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import maxm.androidb3.androidb3.features.test.model.Test;

@Entity(tableName = "test_table")
public class TestEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public double maxPoints;
    public long classId;
    public long parentId;
    public String parentName;

    public TestEntity(String name, double maxPoints, long classId, long parentId, String parentName) {
        this.classId = classId;
        this.name = name;
        this.maxPoints = maxPoints;
        this.parentId = parentId;
        this.parentName = parentName;
    }

    public boolean isValid() {
        return name != null && !name.trim().isEmpty() && maxPoints > 0;
    }

    public long getId() { return id; }
    public long getParentId() { return parentId; }

    public Test toModel(){ return new Test(id, name, maxPoints, classId, parentId, parentName); }
}
