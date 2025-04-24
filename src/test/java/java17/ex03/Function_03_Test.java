package java17.ex03;

import java.util.function.BinaryOperator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java17.data.Person;

/**
 * Exercice 03 - java.util.function.BinaryOperator
 */
public class Function_03_Test {

    /**
     * Fonction de création d'un enfant à partir du père et de la mère.
     * @param father le parent père
     * @param mother le parent mère
     * @return une nouvelle Person représentant l'enfant
     */
    private static final BinaryOperator<Person> MAKE_A_CHILD = (father, mother) -> {
        final Person child = new Person();
        child.setFirstname(father.getFirstname() + " " + mother.getFirstname());
        child.setLastname(father.getLastname());
        child.setAge(0);
        child.setPassword(null);
        return child;
    };

    @Test
    public void testMakeAChild() {
        final Person father = new Person("John", "France", 25, "johndoe");
        final Person mother = new Person("Aline", "Lebreton", 22, "alino");

        // Invoquer la fonction pour créer l'enfant
        final Person child = MAKE_A_CHILD.apply(father, mother);

        assertEquals("John Aline", child.getFirstname());
        assertEquals("France", child.getLastname());
        assertEquals(Integer.valueOf(0), child.getAge());
        assertNull(child.getPassword());
    }
}
