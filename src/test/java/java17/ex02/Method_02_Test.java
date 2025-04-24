package java17.ex02;

import java.util.List;

import org.junit.Test;

import java17.data.Data;
import java17.data.Person;

/**
 * Exercice 02 - Redéfinition
 */
public class Method_02_Test {

    // tag::IDao[]
    interface IDao {
        List<Person> findAll();

        // méthode par défaut format() qui renvoie "[<nb_personnes> persons]"
        default String format() {
            int count = findAll().size();
            return "[" + count + " persons]";
        }
    }
    // end::IDao[]

    // tag::DaoA[]
    class DaoA implements IDao {

        List<Person> people = Data.buildPersonList(20);

        @Override
        public List<Person> findAll() {
            return people;
        }

        // redéfinition de format() pour préfixer le nom de la classe
        @Override
        public String format() {
            // appelle la version par défaut de l'interface
            return "DaoA" + IDao.super.format();
        }
    }
    // end::DaoA[]

    @Test
    public void test_daoA_format() throws Exception {
        DaoA daoA = new DaoA();
        // on invoque format() pour que le test passe
        String result = daoA.format();
        assert "DaoA[20 persons]".equals(result);
    }
}
