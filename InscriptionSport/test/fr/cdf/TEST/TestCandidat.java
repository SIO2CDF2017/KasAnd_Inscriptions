package fr.cdf.TEST;

import fr.cdf.Candidat;
import fr.cdf.Inscriptions;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestCandidat {
        
	@Test
	public void testGetNom() {
            cand.getNom();
	}

	@Test
	public void testSetNom() {
            String name = "toto";
            cand.setNom(name);
	}

	@Test
	public void testGetCompetitions() {
            cand.getCompetitions();
	}

	@Test
	public void testDelete() {
            cand.delete();
	}

	@Test
	public void testCompareTo() {
            cand.compareTo(cand2);
	}

	@Test
	public void testToString() {
            cand.toString();
	}

}