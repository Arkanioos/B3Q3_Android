package maxm.androidb3.androidb3.features.note.model;

import maxm.androidb3.androidb3.features.note.data.entity.NoteEntity;

public class Note {
    private long id = -1;
    private double points;
    private double maxPoints;
    private long testId = -1;
    private long studentId = -1;

    public Note(double points, double maxPoints, long testId, long studentId){
        this.points = points;
        this.maxPoints = maxPoints;
        this.testId = testId;
        this.studentId = studentId;
    }

    public NoteEntity toEntity(){
        return new NoteEntity(points, maxPoints, testId, studentId);
    }

    public void setId(long id) { this.id = id; }
    public long getId() throws IllegalStateException {
        if (id < 0) throw new IllegalStateException("Id not defined");
        return id;
    }

    public double getPoints() { return points; }
    public double getMaxPoints() { return maxPoints; }
    public long getTestId() { return testId; }

    @Override
    public String toString() { return points + " / " + maxPoints; }
}
