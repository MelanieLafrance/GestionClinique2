import enums.VisibleSymptom;

public class Patient {
    private final String name;
    private final VisibleSymptom symptom;
    private final int gravity;

    public Patient(String name, VisibleSymptom symptom, int gravity) {
        this.name = name;
        this.symptom = symptom;
        this.gravity = gravity;
    }

    public String getName() {
        return name;
    }

    public VisibleSymptom getSymptom() {
        return symptom;
    }

    public int getGravity() {
        return gravity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Patient otherPatient = (Patient) obj;
        return this.name.equals(otherPatient.name) &&
                this.symptom.equals(otherPatient.symptom) &&
                this.gravity == otherPatient.gravity;
    }
}