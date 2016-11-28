package fr.cdf.TEST;

import fr.cdf.Competition;
import fr.cdf.Equipe;
import fr.cdf.Inscriptions;
import fr.cdf.Personne;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestCompetition {
        Inscriptions i = Inscriptions.getInscriptions();
        Equipe e = i.createEquipe("Team 1");
        Personne p = i.createPersonne("kasp", "vic", "kaspvic@cdf.fr");
        Competition comp = i.createCompetition("1st comp",null, true);
        Competition comp2 = i.createCompetition("2nd comp",null, false);
	
        @Test
	public void testGetNom() {
		comp.getNom();
	}

	@Test
	public void testSetNom() {
		comp.setNom("1ère comp");
	}

	@Test
	public void testInscriptionsOuvertes() {
		comp.inscriptionsOuvertes();
	}

	@Test
	public void testGetDateCloture() {
		comp.getDateCloture();
	}

	@Test
	public void testEstEnEquipe() {
            comp.estEnEquipe();
	}

	@Test
	public void testSetDateCloture() {
            comp.setDateCloture(LocalDate.of(2017,Month.DECEMBER,25));
	}

	@Test
	public void testGetCandidats() {
            assertNotNull(comp.getCandidats());
	}

	@Test
	public void testAddPersonne() {
            if(comp.estEnEquipe())
            {
                assert(comp2.add(p));
            }
	}

	@Test
	public void testAddEquipe() {
            assert(comp.add(e));
	}

	@Test
	public void testRemove() {
            comp.remove(e);
	}

	@Test
	public void testDelete() {
            comp.delete();
	}

	@Test
	public void testCompareTo() {
            comp.compareTo(comp2);
	}

	@Test
	public void testToString() {
            comp.toString();
	}

}
