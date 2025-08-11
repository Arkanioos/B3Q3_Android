package maxm.androidb3.androidb3.features.classSelection.model;

import maxm.androidb3.androidb3.features.classSelection.data.entity.ClassEntity;
import maxm.androidb3.androidb3.features.common.model.ListItem;

import java.io.Serializable;

public class Class implements Serializable, ListItem {
    private String name = null;
    private long yearId = -1;

    public Class(String name, long yearId){
        this.name = name;
        this.yearId = yearId;
    }

    public String getName(){ return name; }

    public ClassEntity toEntity() {
        return new ClassEntity(name, yearId);
    }

    @Override
    public String toString(){ return name; }
}
