package java17.ex07;

import java.util.function.IntBinaryOperator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Exercice 07 - java.util.function.IntBinaryOperator
 */
public class Function_07_Test {

    // tag::format[]
    /**
     * Formatte le résultat d'une opération binaire sur deux ints
     * @param nb1    le premier opérande
     * @param nb2    le second opérande
     * @param symbol le symbole représentant l'opération
     * @param operator l'opérateur réalisant le calcul
     * @return une chaîne de la forme "(<nb1><symbol><nb2>)=<résultat>"
     */
    String format(int nb1, int nb2, String symbol, IntBinaryOperator operator) {
        final int result = operator.applyAsInt(nb1, nb2);
        return "(" + nb1 + symbol + nb2 + ")=" + result;
    }
    // end::format[]

    /**
     * Opérateur de somme de deux entiers
     */
    private static final IntBinaryOperator SUM = (a, b) -> a + b;

    @Test
    public void testFormatSum() {
        // Invoquer la méthode format avec l'opérateur SUM
        final String result = format(12, 13, "+", SUM);
        assertEquals("(12+13)=25", result);
    }

    /**
     * Opérateur de soustraction de deux entiers
     */
    private static final IntBinaryOperator SUBTRACT = (a, b) -> a - b;

    @Test
    public void testFormatSubtract() {
        // Invoquer la méthode format avec l'opérateur SUBTRACT
        final String result = format(2, 3, "-", SUBTRACT);
        assertEquals("(2-3)=-1", result);
    }
}
