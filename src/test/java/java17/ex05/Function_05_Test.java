package java17.ex05;

import java.util.List;
import java.util.function.Consumer;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java17.data.Data;
import java17.data.Person;

/**
 * Exercice 5 - java.util.function.Consumer
 */
public class Function_05_Test {

    //tag::functions[]
    /**
     * Consumer modifiant le mot de passe d'une personne en "secret"
     */
    private static final Consumer<Person> CHANGE_PASSWORD_TO_SECRET = p -> p.setPassword("secret");

    /**
     * Consumer vérifiant via assertion JUnit que l'âge est supérieur à 4
     */
    private static final Consumer<Person> VERIFY_AGE = p -> assertTrue(
            "L'âge doit être > 4 pour " + p.getFirstname(),
            p.getAge() > 4
    );

    /**
     * Consumer vérifiant via assertion JUnit que le mot de passe est "secret"
     */
    private static final Consumer<Person> VERIFY_PASSWORD = p -> assertEquals(
            "Le mot de passe doit être 'secret' pour " + p.getFirstname(),
            "secret",
            p.getPassword()
    );
    //end::functions[]

    @Test
    public void testConsumer() throws Exception {
        final List<Person> personList = Data.buildPersonList();

        // Modifier le mot de passe de chaque personne en "secret"
        personList.forEach(CHANGE_PASSWORD_TO_SECRET);

        // Vérifier l'âge et le mot de passe de chaque personne
        personList.forEach(VERIFY_AGE.andThen(VERIFY_PASSWORD));
    }
}
