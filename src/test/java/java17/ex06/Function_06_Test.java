package java17.ex06;

import java.util.function.Supplier;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java17.data.Person;

/**
 * Exercice 06 - java.util.function.Supplier
 */
public class Function_06_Test {

    // tag::formatAge[]
    /**
     * Formatte l'âge de la Person fournie par le Supplier
     * @param supplier le Supplier fournissant l'instance de Person
     * @return une chaîne de la forme "[age=<AGE>]"
     */
    String formatAge(Supplier<Person> supplier) {
        final Person person = supplier.get();
        return "[age=" + person.getAge() + "]";
    }
    // end::formatAge[]

    @Test
    public void testFormatAge() {
        // Supplier créant une Person d'âge 35
        final Supplier<Person> supplier = () -> new Person("Test", "Test", 35, "pass");
        final String result = formatAge(supplier);

        assertEquals("[age=35]", result);
    }
}
