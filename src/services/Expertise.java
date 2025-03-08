package services;

import java.util.Objects;

public class Expertise {
    private String expertiseName;
    private String[] treatmentList;

    public Expertise(String expertiseName, String[] treatmentList) {
        this.expertiseName = expertiseName;
        this.treatmentList = treatmentList;
    }


    public String getExpertiseName() {
        return expertiseName;
    }

    public String[] getTreatmentList() {
        return treatmentList.clone(); // Ensures data integrity - external code can't modify the data
    }

    public String displayTreatments() {
        return String.join(", ", treatmentList); // Converts array to a readable string
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expertise expertise = (Expertise) o;
        return Objects.equals(expertiseName, expertise.expertiseName);  // Compare expertise names
    }

    @Override
    public int hashCode() {
        return Objects.hash(expertiseName);  // Hash code based on expertise name
    }

}
