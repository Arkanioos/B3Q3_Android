package maxm.androidb3.androidb3.features.year.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import maxm.androidb3.androidb3.features.year.model.Year;

@Entity(tableName = "year_table")
public class YearEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String year;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public YearEntity(){}

    public YearEntity(String year){
        this.year = year;
    }

    public boolean isValid(){
        return year != null && !year.trim().isEmpty();
    }

    public Year toModel(){
        return new Year(year, id);
    }
}
