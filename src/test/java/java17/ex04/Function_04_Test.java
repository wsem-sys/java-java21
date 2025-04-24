package java17.ex04;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java17.data.Data;
import java17.data.Person;

/**
 * Exercice 4 - java.util.function.Predicate
 */
public class Function_04_Test {

    // tag::filterMethod[]
    /**
     * Filtre une liste en fonction d'un prédicat
     * @param list la liste à filtrer
     * @param predicate le prédicat de filtrage
     * @param <T> le type des éléments de la liste
     * @return une nouvelle liste contenant les éléments satisfaisant le prédicat
     */
    <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T el : list) {
            if (predicate.test(el)) {
                result.add(el);
            }
        }
        return result;
    }
    // end::filterMethod[]

    /** Prédicat vérifiant qu'une personne est majeure (âge >= 18) */
    private static final Predicate<Person> ADULT = p -> p.getAge() >= 18;

    @Test
    public void testFilterAdults() {
        final List<Person> personList = Data.buildPersonList();
        // Appliquer le prédicat ADULT
        final List<Person> result = filter(personList, ADULT);

        assertEquals(4, result.size());
    }

    /** Prédicat vérifiant que le nom de famille est "France" */
    private static final Predicate<Person> LASTNAME_IS_FRANCE = p -> "France".equals(p.getLastname());

    /** Prédicat vérifiant que le prénom est "Armor" */
    private static final Predicate<Person> FIRSTNAME_IS_ARMOR = p -> "Armor".equals(p.getFirstname());

    @Test
    public void testFilterAdultFranceArmor() {
        final List<Person> personList = Data.buildPersonList();
        // Chaîner les prédicats pour filtrer adultes dont le nom est France et le prénom Armor
        final List<Person> result = filter(personList, ADULT.and(LASTNAME_IS_FRANCE).and(FIRSTNAME_IS_ARMOR));

        assertEquals(1, result.size());
        final Person p = result.get(0);
        assertEquals("Armor", p.getFirstname());
        assertEquals("France", p.getLastname());
        assertEquals(Integer.valueOf(25), p.getAge());
    }
}
