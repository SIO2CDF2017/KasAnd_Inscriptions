package fr.cdf.TEST;

import fr.cdf.Equipe;
import fr.cdf.Inscriptions;
import fr.cdf.Personne;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestEquipe {
    
        Inscriptions i = Inscriptions.getInscriptions();
        Equipe e = i.createEquipe("SLAM");
        Personne p = i.createPersonne("yolo", "swagg", "yoloSwagg@bb.com");
	
        @Test
	public void testAdd() {
            assert(e.add(p));
	}
        
        @Test
        public void testDelete() {
            e.delete();
	}
        
        @Test
        public void testGetMembre() {
            assertNotNull(e.getMembres());
	}
        
        @Test
        public void testRemove() {
            e.remove(p);
	}
        
        @Test
        public void testToString() {
            e.toString();
	}
}
