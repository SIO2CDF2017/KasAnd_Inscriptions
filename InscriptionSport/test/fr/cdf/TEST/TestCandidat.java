package fr.cdf.TEST;

import fr.cdf.Candidat;
import fr.cdf.Personne;
import fr.cdf.Inscriptions;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestCandidat {
        Inscriptions i = Inscriptions.getInscriptions();
        Personne cand = i.createPersonne("kasperski", "Victor", "victor.kasperski@laposte.net");
        Personne cand2 = i.createPersonne("ka", "Vi", "vi.ka@gmail.com");
	
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