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

}
