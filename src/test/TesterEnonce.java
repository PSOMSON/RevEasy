package test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Model.Enonce;
import Model.Enonce.Type;  

/**
 * Classe de test de la classe Model.Enonce.java
 */
public class TesterEnonce {
    
    Enonce e1, e2;

    @Before
    public void setUp() {
        e1 = new Enonce(Type.THEOREME, "Pythagore", "a2+b2=c2");
        e2 = new Enonce(Type.DEFINITION, "Vide", "");   
    }

    private void assertEquals(String s1, String s2) {
        assertTrue(s1.equals(s2));
    }

    @Test
    public void testerGetTitre() {
        assertEquals("Pythagore", e1.getTitre());
        assertEquals("Vide", e2.getTitre());
    }

    @Test
    public void testerGetCorps() {
        assertEquals("a2+b2=c2", e1.getCorps());
        assertEquals("", e2.getCorps());
    }

    @Test
    public void testerGetType() {
        assertTrue(e1.getType() == Type.THEOREME);
        assertTrue(e2.getType() == Type.DEFINITION);
    }
    
}
