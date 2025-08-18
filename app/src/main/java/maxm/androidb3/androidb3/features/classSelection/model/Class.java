package maxm.androidb3.androidb3.features.classSelection.model;

import maxm.androidb3.androidb3.features.classSelection.data.entity.ClassEntity;
import maxm.androidb3.androidb3.features.common.model.ListItem;

import java.io.Serializable;

public class Class implements Serializable, ListItem {
    private long id = -1;
    private String name = null;
    public long yearId = -1;

    public Class(String name, long yearId){
        this.name = name;
        this.yearId = yearId;
    }

    public Class(String name, long id, long yearId){
        this.name = name;
        this.id = id;
        this.yearId = yearId;
    }

    public void setId(long id) { this.id = id; }
    public long getId() throws IllegalStateException{
        if(id < 0) throw new IllegalStateException("Id not defined.");
        return id;
    }

    public String getName(){ return name; }

    public ClassEntity toEntity() {
        return new ClassEntity(name, yearId);
    }

    @Override
    public String toString(){ return name; }
}
