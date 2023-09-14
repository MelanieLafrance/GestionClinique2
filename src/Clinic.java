import enums.VisibleSymptom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Clinic {
    private Queue<VisibleSymptom> ListeMedecin = new LinkedList<VisibleSymptom>();
    private Queue<VisibleSymptom> ListeRadiologie = new LinkedList<VisibleSymptom>();

    public Clinic(/*doctorTriageType, radiologyTriageType*/) {
    }

    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom) {
        // TODO
    }

    public boolean estVide(){
        return ListeMedecin.isEmpty();
    }

    public void ajoutPatient(VisibleSymptom visibleSymptom) {
        ListeMedecin.offer(visibleSymptom);
        if (visibleSymptom == VisibleSymptom.SPRAIN || visibleSymptom == VisibleSymptom.BROKEN_BONE){
            ListeRadiologie.offer(visibleSymptom);
        }
    }

    public void retirePatient() {
        ListeMedecin.poll();
    }

    public VisibleSymptom premierPatientMedecin() {
        return ListeMedecin.peek();
    }

    public VisibleSymptom premierPatientRadiologie() {
        return ListeRadiologie.peek();
    }
}