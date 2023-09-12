import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClinicTest {
    @Test
    public void cliniqueEstInitialementVide() {
        Clinic clinic = new Clinic();
        assertTrue(clinic.estVide());
    }

    @Test
    public void triagePatient_devraitAjouterFileDocteur(){
        Clinic clinic = new Clinic();
        clinic.triagePatient("name", 4, VisibleSymptom.SPRAIN);

    }
}