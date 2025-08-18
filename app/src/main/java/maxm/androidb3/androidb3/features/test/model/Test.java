package maxm.androidb3.androidb3.features.test.model;

import java.io.Serializable;

import maxm.androidb3.androidb3.features.common.model.ListItem;
import maxm.androidb3.androidb3.features.test.data.entity.TestEntity;

public class Test implements Serializable, ListItem {
    private long id = -1;
    private String name = null;
    private double maxPoints = 20;
    private long classId = -1;
    private long parentId = -1;
    private String parentName = null;

    public Test(long id, String name, double maxPoints, long classId, long parentId, String parentName){
        this.id = id;
        this.name = name;
        this.maxPoints = maxPoints;
        this.classId = classId;
        this.parentId = parentId;
        this.parentName = parentName;
    }

    public Test(String name, double maxPoints, long classId, long parentId, String parentName){
        this.name = name;
        this.maxPoints = maxPoints;
        this.classId = classId;
        this.parentId = parentId;
        this.parentName = parentName;
    }

    public TestEntity toEntity(){
        return new TestEntity(name, maxPoints, classId, parentId, parentName);
    }

    public void setId(long id) { this.id = id; }
    public long getId() throws IllegalStateException{
        if(id < 0) throw new IllegalStateException("Id not defined");
        return id;
    }

    public double getMaxPoints() throws IllegalStateException{
        if (maxPoints < 0) throw new IllegalStateException("Max points not defined");
        return maxPoints;
    }

    public void setParentId(long parentId) { this.parentId = parentId; }
    public long getParentId() { return parentId; }

    public String getName() { return name; }

    public void setParentName(String parentName) { this.parentName = parentName; }

    @Override
    public String toString() {
        return name + "(/" + maxPoints + ")" +
                (parentName != null && !parentName.isEmpty() ? " (issue de " + parentName + ")" : "");
    }

}
