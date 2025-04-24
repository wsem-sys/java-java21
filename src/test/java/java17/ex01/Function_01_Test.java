package java17.ex01;

import java.util.function.Function;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java17.data.Account;
import java17.data.Person;

/**
 * Exercice 01 - java.util.function.Function
 */
public class Function_01_Test {

    /******** PART 1 - Integer -> Person *******/

    /**
     * Fonction de conversion d'un entier en objet Person
     * @param i l'entier utilisé pour construire les attributs de Person
     * @return une instance de Person dont les champs sont dérivés de l'entier
     */
    private static final Function<Integer, Person> INT_TO_PERSON = i -> new Person(
            "first_" + i,
            "last_" + i,
            i,
            "pass_" + i
    );

    @Test
    public void testIntToPerson() {
        // Invoquer la fonction avec l'entier 10
        final Person result = INT_TO_PERSON.apply(10);

        // Vérifier que les attributs correspondent au format attendu
        assertEquals("first_10", result.getFirstname());
        assertEquals("last_10", result.getLastname());
        assertEquals(Integer.valueOf(10), result.getAge());
        assertEquals("pass_10", result.getPassword());
    }

    /******** PART 2 - Person -> Account *******/

    /**
     * Fonction de conversion d'une Person en Account avec solde fixe de 1000
     * @param p la personne à associer au compte
     * @return un nouvel Account dont l'owner est p et balance est 1000
     */
    private static final Function<Person, Account> PERSON_TO_ACCOUNT = p -> {
        final Account account = new Account();
        account.setOwner(p);
        account.setBalance(1000);
        return account;
    };

    @Test
    public void testPersonToAccount() {
        final Person person = new Person("Jules", "France", 10, "pass");

        // Invoquer la fonction pour créer un compte à partir de la personne
        final Account result = PERSON_TO_ACCOUNT.apply(person);

        // Vérifier que l'owner et le solde sont correctement définis
        assertEquals(person, result.getOwner());
        assertEquals(Integer.valueOf(1000), result.getBalance());
    }
}
