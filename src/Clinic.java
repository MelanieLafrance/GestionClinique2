import enums.TriageType;
import enums.VisibleSymptom;

import java.util.*;

public class Clinic {
    private Deque<Patient> ListeMedecin = new LinkedList<Patient>();
    private Deque<Patient> ListeRadiologie = new LinkedList<Patient>();

    private final TriageType DoctorTriageType;
    private final TriageType RadiologieTriageType;

    public Clinic(TriageType doctorTriageType, TriageType radiologyTriageType) {
        DoctorTriageType = doctorTriageType;
        RadiologieTriageType = radiologyTriageType;
    }

    public boolean estVide(){
        return ListeMedecin.isEmpty();
    }

    public void ajoutPatient(Patient patient){
        ajoutPatient(patient.getName(), patient.getSymptom(), patient.getGravity());
    }

    public void ajoutPatient(String name, VisibleSymptom visibleSymptom, int gravity){
        Patient patient = new Patient(name, visibleSymptom, gravity);

        ajoutePatientListeMedecin(patient);
        if (visibleSymptom == VisibleSymptom.SPRAIN || visibleSymptom == VisibleSymptom.BROKEN_BONE) {
            ajoutePatientListeRadiology(patient);
        }
    }

    private void ajoutePatientListeRadiology(Patient patient){
        if (RadiologieTriageType == TriageType.GRAVITY && patient.getGravity() > 5) {
            ListeRadiologie.offerFirst(patient);
        } else {
            ListeRadiologie.offer(patient);
        }
    }

    private void ajoutePatientListeMedecin(Patient patient){
        if (DoctorTriageType==TriageType.GRAVITY && patient.getGravity() > 5){
            ListeMedecin.offerFirst(patient);
        } else {
            ListeMedecin.offer(patient);
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