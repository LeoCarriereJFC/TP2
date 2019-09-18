package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        // S3 : on n’imprime pas le ticket si le montant inséré est insuffisant
        public void notEnoughtMoney() {
            
            assertEquals("Le montant inséré est insuffisant", false, machine.printTicket() );            
	}
        
        @Test
        // S4 : on imprime le ticket si le montant inséré est suffisant
        public void enoughtMoney() {
            machine.insertMoney(50);
            assertEquals("Le montant inséré est insuffisant", true, machine.printTicket() );          
	}
        
        @Test
        // S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
        public void balanceStateAfterPrint() {
            machine.insertMoney(PRICE);
            assertEquals("La balance n'as pas été réinitialisée", 0, machine.getBalance() );              
	}
           
        @Test
        // S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
        public void totalUpdatedAfterPrintTicket() {
            assertEquals("le total est incorrect", 0, machine.getTotal() );             
            machine.insertMoney(PRICE);
            machine.printTicket();
            assertEquals("le total est incorrect" , PRICE , machine.getTotal() );              
	}
        
        @Test
        // S7 : refund() rend correctement la monnaie
        public void refundIsWokingCorrectly() {
            machine.insertMoney(50);
            assertEquals("le refund est incorrect" , 50 , machine.refund() );              
	}
        
        @Test
        // S8 : refund() remet la balance à zéro
        public void refundIsResetBalance() {
            machine.insertMoney(50);
            machine.refund();
            
            assertEquals("le refund est incorrect" , 0 , machine.getBalance() );              
	}
        
        @Test
        // S9 : on ne peut pas insérer un montant négatif
        public void insertionOfNegativeAmountOfMoney() {
            machine.insertMoney(-50);
            assertEquals("le montant ne peut pas etre negatif" , 0 , machine.getBalance() );              
	}
                
        @Test
        // S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
        public void creationOfMachineWithNegativeTicketSPrice() {
            //TODO verifier que l'exeption à bien été lancé à l'appel d'un contructeur de machine avec un prix negatif
	}

}
