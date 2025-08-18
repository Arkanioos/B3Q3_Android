package maxm.androidb3.androidb3.features.note.model;

import android.util.Log;

import java.util.List;

public class AllNotes {
    private List<Note> notes = null;

    public AllNotes(List<Note> notes){
        this.notes = notes;
    }

    public double getAverageNote(){
        if (notes == null || notes.isEmpty()) return 0.0;

        double totalPoints = 0.0;
        double totalMaxPoints = 0.0;

        for (Note note : notes) {
            Log.d("TEST_NOTES", "adding to points : " + note.getPoints());
            Log.d("TEST_NOTES", "adding to max points : " + note.getMaxPoints());
            totalPoints += note.getPoints();
            totalMaxPoints += note.getMaxPoints();
        }

        Log.d("TEST_NOTES", "total points are : " + totalPoints);
        Log.d("TEST_NOTES", "total max points are : " + totalMaxPoints);


        if (totalMaxPoints == 0) return 0.0;

        double averageOn20 = (totalPoints / totalMaxPoints) * 20.0;
        return Math.round(averageOn20 * 2) / 2.0;
    }
}
