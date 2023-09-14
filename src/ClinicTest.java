
import enums.VisibleSymptom;
import org.junit.Test;

import static enums.TriageType.*;
import static enums.VisibleSymptom.*;
import static org.junit.Assert.*;

public class ClinicTest {
    public static final VisibleSymptom SYMPTOME_PAR_DEFAUT= MIGRAINE;
    public static final int GRAVITE_PAR_DEFAUT = 0;
    public static final String NOM_PAR_DEFAUT = "John";
    public static final Patient PATIENT_PAR_DEFAUT= new Patient(NOM_PAR_DEFAUT, SYMPTOME_PAR_DEFAUT, GRAVITE_PAR_DEFAUT);

    public static final Patient PATIENT_RADIOLOGIE = new Patient(NOM_PAR_DEFAUT, SPRAIN, GRAVITE_PAR_DEFAUT);

    @Test
    public void CliniqueInitialementVide() {
        Clinic clinique = new Clinic(FIFO);

        assertTrue(clinique.estVide());
    }

    @Test
    public void SiNouveauPatient_AlorsCliniqueNonVide() {
        Clinic clinique = new Clinic(FIFO);

        clinique.ajoutPatient(PATIENT_PAR_DEFAUT);

        assertFalse(clinique.estVide());
    }

    @Test
    public void SiPatientRetire_AlorsCliniqueEstVide(){
        Clinic clinique = new Clinic(FIFO);

        clinique.ajoutPatient(PATIENT_PAR_DEFAUT);
        clinique.retirePatientMedecin();

        assertTrue(clinique.estVide());
    }

    @Test
    public void SiPatient_AlorsPremierFileMedecin(){
        Clinic clinique = new Clinic(FIFO);

        clinique.ajoutPatient(PATIENT_PAR_DEFAUT);
        Patient premierPatient = clinique.premierPatientMedecin();

        assertEquals(PATIENT_PAR_DEFAUT, premierPatient);
    }

    @Test
    public void SiPatientMigraine_AlorsPasDansRadiologie(){
        Clinic clinique = new Clinic(FIFO);
        Patient patientMigraine = new Patient(NOM_PAR_DEFAUT, MIGRAINE, GRAVITE_PAR_DEFAUT);

        clinique.ajoutPatient(patientMigraine);
        Patient premierPatient = clinique.premierPatientRadiologie();

        assertNotEquals(patientMigraine, premierPatient);
    }

    @Test
    public void SiSecondPatient_AlorsIlEstSecond(){
        Clinic clinique = new Clinic(FIFO);
        clinique.ajoutPatient(PATIENT_PAR_DEFAUT);
        Patient patientFlu = new Patient(NOM_PAR_DEFAUT, FLU, GRAVITE_PAR_DEFAUT);

        clinique.ajoutPatient(patientFlu);
        clinique.retirePatientMedecin();
        Patient SecondPatient = clinique.premierPatientMedecin();

        assertEquals(patientFlu, SecondPatient);
    }

    @Test
    public void SiSecondPatientFLU_AlorsPasDansRadiologie(){
        Clinic clinique = new Clinic(FIFO);
        clinique.ajoutPatient(PATIENT_PAR_DEFAUT);
        Patient patientFlu = new Patient(NOM_PAR_DEFAUT, FLU, GRAVITE_PAR_DEFAUT);

        clinique.ajoutPatient(patientFlu);
        Patient premierPatient = clinique.premierPatientRadiologie();

        assertNotEquals(patientFlu, premierPatient);
    }

    @Test
    public void SiSprain_AlorsPremierRadiologie(){
        Clinic clinique = new Clinic(FIFO);
        Patient patientSprain = new Patient(NOM_PAR_DEFAUT, SPRAIN, GRAVITE_PAR_DEFAUT);

        clinique.ajoutPatient(patientSprain);
        Patient premierPatientRadiologie = clinique.premierPatientRadiologie();

        assertEquals(patientSprain, premierPatientRadiologie);
    }

    @Test
    public void GivenGravitySortingDoctorList_whenSecondPatientWithPriority_ThenFirstInRadiology(){
        Clinic clinique = new Clinic(GRAVITY);
        clinique.ajoutPatient(PATIENT_PAR_DEFAUT);
        Patient patientFlu7 = new Patient(NOM_PAR_DEFAUT, FLU, 7);

        clinique.ajoutPatient(patientFlu7);
        Patient premierPatient= clinique.premierPatientMedecin();

        assertEquals(premierPatient, patientFlu7);
    }

    @Test
    public void GivenGravitySortingDoctorList_whenSecondPatientForRadiologyWithPriority_ThenIsSecondInRadiology(){
        Clinic clinique = new Clinic(GRAVITY);
        clinique.ajoutPatient(PATIENT_RADIOLOGIE);
        Patient patientBrokenBone7 = new Patient(NOM_PAR_DEFAUT, BROKEN_BONE, 7);

        clinique.ajoutPatient(patientBrokenBone7);
        clinique.retirePatientRadiologie();
        Patient premierPatient= clinique.premierPatientRadiologie();

        assertEquals(premierPatient, patientBrokenBone7);
    }
}
