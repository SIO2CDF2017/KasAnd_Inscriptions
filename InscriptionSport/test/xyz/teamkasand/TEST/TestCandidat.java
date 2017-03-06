package xyz.teamkasand.TEST;

import xyz.teamkasand.Candidat;
import xyz.teamkasand.Competition;
import xyz.teamkasand.Personne;
import xyz.teamkasand.Inscriptions;
import java.time.LocalDate;
import java.util.SortedSet;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestCandidat {
	
	Inscriptions i = Inscriptions.getInscriptions();
	SortedSet<Candidat> can = i.getCandidats();
	Personne p = i.createPersonne("Tony", "Jean", "jean.riviere@mail.com");
	Personne p2 = i.createPersonne("Dupont", "Michel", "michel.dupont@mail.com");
	Competition c = i.createCompetition("Competition 1", LocalDate.of(2020, 1, 1), false);

	@Test
	public void testGetNom() {
		c.add(p);
		assertEquals(can.last().getNom(), "Tony");
	}

	@Test
	public void testSetNom() {
		c.add(p);
		can.last().setNom("Robert");
		assertEquals(can.last().getNom(), "Robert");
	}

	@Test
	public void testGetCompetitions() {
		assertNotNull(i.getCompetitions());
	}

	@Test
	public void testDelete() {
		c.add(p);
		c.add(p2);
		can.last().delete();
		assertEquals(i.getCandidats().toString(), "[\n" + "Boris le Hachoir, \n" + "Dupont Michel, \n" + "Tony Dent de plomb]");
	}

	@Test
	public void testCompareTo() {
		c.add(p);
		c.add(p2);
		assertEquals(can.first().compareTo(can.first()), can.last().compareTo(can.last()));
	}

	@Test
	public void testToString() {
		assertEquals(can.last().toString(), "Equipe \n" + "yolo");
	}

}