import enums.TriageType;
import enums.VisibleSymptom;

import java.util.*;

public class Clinic {
    private Deque<Patient> ListeMedecin = new LinkedList<Patient>();
    private Deque<Patient> ListeRadiologie = new LinkedList<Patient>();

    private final TriageType DoctorTriageType;

    public Clinic(TriageType doctorTriageType /*radiologyTriageType*/) {
        DoctorTriageType = doctorTriageType;
    }

    public boolean estVide(){
        return ListeMedecin.isEmpty();
    }

    public void ajoutPatient(Patient patient){
        ajoutPatient(patient.getName(), patient.getSymptom(), patient.getGravity());
    }

    public void ajoutPatient(String name, VisibleSymptom visibleSymptom, int gravity){
        Patient patient = new Patient(name, visibleSymptom, gravity);

        if (DoctorTriageType==TriageType.GRAVITY && gravity > 5){
            ListeMedecin.offerFirst(patient);
        } else {
            ListeMedecin.offer(patient);
        }

        if (visibleSymptom == VisibleSymptom.SPRAIN || visibleSymptom == VisibleSymptom.BROKEN_BONE){
            ListeRadiologie.offer(patient);
        }
    }

    public Patient retirePatientMedecin() {
        Patient premierPatient = premierPatientMedecin();
        ListeMedecin.poll();
        return premierPatient;
    }

    public Patient retirePatientRadiologie() {
        Patient premierPatient = premierPatientRadiologie();
        ListeRadiologie.poll();
        return premierPatient;
    }

    public Patient premierPatientMedecin() {
        return ListeMedecin.peek();
    }

    public Patient premierPatientRadiologie() {
        return ListeRadiologie.peek();
    }
}