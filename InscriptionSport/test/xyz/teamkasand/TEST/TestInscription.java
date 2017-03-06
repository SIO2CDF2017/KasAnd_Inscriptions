package xyz.teamkasand.TEST;

import static org.junit.Assert.*;
import xyz.teamkasand.Inscriptions;
import java.io.IOException;

import org.junit.Test;

public class TestInscription {
	
	private final 
		Inscriptions insc = Inscriptions.getInscriptions();
	
	@Test
	public void testGetCompetitions() {
		insc.getCompetitions();
	}

	@Test
	public void testGetCandidats() {
		insc.getCandidats();
	}

	@Test
	public void testGetPersonnes() {
		insc.getPersonnes();
	}

	@Test
	public void testGetEquipes() {
		insc.getEquipes();
	}

	@Test
	public void testCreateCompetition() {
		insc.createCompetition("1st comp",null, true);
	}

	@Test
	public void testCreatePersonne() {
		insc.createPersonne("kasperski", "victor", "kasperskivictor@gmail.com");
	}

	@Test
	public void testCreateEquipe() {
		insc.createEquipe("yolo");
        }
        
	@Test
	public void testGetInscriptions() {
		Inscriptions.getInscriptions();
	}

	@Test
	public void testReinitialiser() {
		insc.reinitialiser();
	}

	@Test
	public void testRecharger() {
		insc.recharger();
	}

	@Test
	public void testSauvegarder() throws IOException{
		insc.sauvegarder();
	}

        
	@Test
	public void testToString() {
		insc.toString();
	}


}
