package java17.ex02;

import java.util.function.BiFunction;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java17.data.Account;
import java17.data.Person;

/**
 * Exercice 02 - java.util.function.BiFunction
 */
public class Function_02_Test {

    /**
     * Fonction de construction d'un Account à partir d'une Person et d'un solde
     * @param p la personne propriétaire du compte
     * @param balance le montant initial du compte
     * @return un nouvel Account dont l'owner est p et le balance fixé à balance
     */
    private static final BiFunction<Person, Integer, Account> BUILD_ACCOUNT = (p, balance) -> {
        final Account account = new Account();
        account.setOwner(p);
        account.setBalance(balance);
        return account;
    };

    @Test
    public void testBuildAccount() {
        // Création d'une personne de test
        final Person person = new Person("John", "France", 80, "pass");
        // Invoquer la fonction avec la personne et le solde 500
        final Account account = BUILD_ACCOUNT.apply(person, 500);

        // Vérifier que le solde et le propriétaire sont correctement définis
        assertEquals(Integer.valueOf(500), account.getBalance());
        assertEquals("John", account.getOwner().getFirstname());
        assertEquals("France", account.getOwner().getLastname());
        assertEquals(Integer.valueOf(80), account.getOwner().getAge());
        assertEquals("pass", account.getOwner().getPassword());
    }
}
