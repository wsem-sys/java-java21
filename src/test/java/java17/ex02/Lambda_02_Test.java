package java17.ex02;

import org.junit.Test;

import java17.data.Account;
import java17.data.Data;
import java17.data.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercice 02 - Map
 *
 * Cette classe de test illustre la transformation d'une liste de Person
 * en une liste de Account via un mapper fonctionnel.
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
    /**
     * Interface fonctionnelle permettant de convertir un objet Person
     * en un objet Account.
     */
    @FunctionalInterface
    interface PersonToAccountMapper {
        /**
         * Mappe une personne vers un compte.
         *
         * @param p la personne à convertir
         * @return le compte associé à la personne
         */
        Account map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    /**
     * Transforme une liste de Person en une liste de Account
     * en appliquant un mapper fonctionnel.
     *
     * @param personList liste de Person à transformer
     * @param mapper     fonction de mapping Person -> Account
     * @return liste de Account résultante
     */
    private List<Account> map(List<Person> personList, PersonToAccountMapper mapper) {
        List<Account> accounts = new ArrayList<>();
        for (Person p : personList) {
            accounts.add(mapper.map(p));
        }
        return accounts;
    }
    // end::map[]

    // tag::test_map_person_to_account[]
    /**
     * Teste la création d'un compte pour chaque personne
     * avec un solde initial de 100.
     *
     * @throws Exception si une erreur survient
     */
    @Test
    public void test_map_person_to_account() throws Exception {
        List<Person> personList = Data.buildPersonList(100);

        // Transformation : chaque Person devient un Account avec solde 100
        List<Account> result = map(
                personList,
                p -> new Account(p, 100)
        );

        // Vérifie la taille identique à la liste d'origine
        assert result.size() == personList.size();

        // Chaque compte doit avoir un solde de 100 et un propriétaire non null
        for (Account account : result) {
            assert account.getBalance().equals(100);
            assert account.getOwner() != null;
        }
    }
    // end::test_map_person_to_account[]
}
