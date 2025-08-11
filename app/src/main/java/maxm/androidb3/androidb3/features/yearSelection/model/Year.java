package maxm.androidb3.androidb3.features.yearSelection.model;

import maxm.androidb3.androidb3.features.common.model.ListItem;
import maxm.androidb3.androidb3.features.yearSelection.data.entity.YearEntity;

import java.io.Serializable;

public class Year implements Serializable, ListItem {
    private long id = -1;
    private String year = null;

    public Year(String year){
        this.year = year;
    }

    public Year(String year, int id){
        this.year = year;
        this.id = id;
    }

    public void setId(long id) { this.id = id; }
    public long getId() throws IllegalStateException{
        if(id < 0) throw new IllegalStateException("Id not defined.");
        return id;
    }

    public String getYear(){ return year; }

    public YearEntity toEntity() {
        return new YearEntity(year);
    }

    @Override
    public String toString(){ return year; }
}
