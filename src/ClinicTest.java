
import enums.VisibleSymptom;
import org.junit.Test;

import static enums.VisibleSymptom.*;
import static org.junit.Assert.*;

public class ClinicTest {
    public static final VisibleSymptom SYMPTOME_PAR_DEFAUT= MIGRAINE;

    @Test
    public void CliniqueInitialementVide() {
        Clinic clinique = new Clinic();

        assertTrue(clinique.estVide());
    }

    @Test
    public void SiNouveauPatient_AlorsCliniqueNonVide() {
        Clinic clinique = new Clinic();

        clinique.ajoutPatient(SYMPTOME_PAR_DEFAUT);

        assertFalse(clinique.estVide());
    }

    @Test
    public void SiPatientRetire_AlorsCliniqueEstVide(){
        Clinic clinique = new Clinic();

        clinique.ajoutPatient(SYMPTOME_PAR_DEFAUT);
        clinique.retirePatient();

        assertTrue(clinique.estVide());
    }

    @Test
    public void SiPatient_AlorsPremierFileMedecin(){
        Clinic clinique = new Clinic();

        clinique.ajoutPatient(SYMPTOME_PAR_DEFAUT);
        VisibleSymptom premierPatient = clinique.premierPatientMedecin();

        assertEquals(SYMPTOME_PAR_DEFAUT, premierPatient);
    }

    @Test
    public void SiPatientMigraine_AlorsPasDansRadiologie(){
        Clinic clinique = new Clinic();

        clinique.ajoutPatient(MIGRAINE);
        VisibleSymptom premierPatient = clinique.premierPatientRadiologie();

        assertNotEquals(MIGRAINE, premierPatient);
    }

    @Test
    public void SiSecondPatient_AlorsIlEstSecond(){
        Clinic clinique = new Clinic();
        clinique.ajoutPatient(SYMPTOME_PAR_DEFAUT);

        clinique.ajoutPatient(FLU);
        clinique.retirePatient();
        VisibleSymptom SecondPatient = clinique.premierPatientMedecin();

        assertEquals(FLU, SecondPatient);
    }

    @Test
    public void SiSecondPatientFLU_AlorsPasDansRadiologie(){
        Clinic clinique = new Clinic();
        clinique.ajoutPatient(SYMPTOME_PAR_DEFAUT);

        clinique.ajoutPatient(FLU);
        VisibleSymptom FluPatient = clinique.premierPatientRadiologie();

        assertNotEquals(FLU, FluPatient);
    }

    @Test
    public void SiSprain_AlorsPremierRadiologie(){
        Clinic clinique = new Clinic();

        clinique.ajoutPatient(SPRAIN);
        VisibleSymptom premierPatientRadiologie = clinique.premierPatientRadiologie();

        assertEquals(SPRAIN, premierPatientRadiologie);
    }
}
