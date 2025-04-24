package java17.ex01;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java17.data.Data;
import java17.data.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercice 01 - Filter
 *
 * Cette classe de test montre l'utilisation d'un prédicat fonctionnel
 * pour filtrer une liste de Person selon différents critères.
 */
public class Lambda_01_Test {

    // tag::PersonPredicate[]
    /**
     * Interface fonctionnelle permettant de tester une condition sur un objet Person.
     */
    @FunctionalInterface
    interface PersonPredicate {
        /**
         * Teste si la personne p satisfait une condition.
         *
         * @param p la personne à tester
         * @return true si p satisfait la condition, false sinon
         */
        boolean test(Person p);
    }
    // end::PersonPredicate[]

    // tag::filter[]
    /**
     * Filtre une liste de Person en appliquant un prédicat.
     *
     * @param persons   liste à filtrer
     * @param predicate condition de filtrage
     * @return sous-liste de Person vérifiant predicate
     */
    private List<Person> filter(List<Person> persons, PersonPredicate predicate) {
        List<Person> filteredPersons = new ArrayList<>();
        for (Person p : persons) {
            if (predicate.test(p)) {
                filteredPersons.add(p);
            }
        }
        return filteredPersons;
    }
    // end::filter[]

    // tag::test_filter_by_age[]
    /**
     * Teste le filtrage des personnes majeures (age >= 18).
     *
     * @throws Exception si une erreur survient
     */
    @Test
    public void test_filter_by_age() throws Exception {
        List<Person> personList = Data.buildPersonList(100);

        // Filtrer les personnes adultes
        List<Person> result = filter(personList, p -> p.getAge() >= 18);

        // Doit y avoir 83 personnes de 18 ans et plus
        assert result.size() == 83;

        // Vérification individuelle de l'âge
        for (Person person : result) {
            assert person.getAge() >= 18;
        }
    }
    // end::test_filter_by_age[]

    // tag::test_filter_by_firstname[]
    /**
     * Teste le filtrage des personnes dont le prénom est "first_10".
     *
     * @throws Exception si une erreur survient
     */
    @Test
    public void test_filter_by_firstname() throws Exception {
        List<Person> personList = Data.buildPersonList(100);

        // Filtrer les personnes ayant pour prénom "first_10"
        List<Person> result = filter(personList,
                p -> "first_10".equals(p.getFirstname()));

        // Doit y avoir exactement une personne avec ce prénom
        assert result.size() == 1;
        assert "first_10".equals(result.get(0).getFirstname());
    }
    // end::test_filter_by_firstname[]

    // tag::test_filter_by_password[]
    /**
     * Teste le filtrage des personnes âgées de plus de 49 ans
     * dont le hash SHA-512 du mot de passe correspond à une valeur donnée.
     *
     * @throws Exception si une erreur survient
     */
    @Test
    public void test_filter_by_password() throws Exception {
        List<Person> personList = Data.buildPersonList(100);

        // Hash SHA-512 attendu pour le mot de passe
        String passwordSha512Hex =
                "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27" +
                        "ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff";

        // Filtrer les personnes de plus de 49 ans dont le hash du mot de passe
        // correspond à celui attendu
        List<Person> result = filter(personList,
                p -> p.getAge() > 49
                        && DigestUtils.sha512Hex(p.getPassword())
                        .equals(passwordSha512Hex));

        // Doit y avoir 6 personnes correspondantes
        assert result.size() == 6;

        // S'assurer que leur mot de passe en clair est "test"
        for (Person person : result) {
            assert "test".equals(person.getPassword());
        }
    }
    // end::test_filter_by_password[]
}
