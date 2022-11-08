package ex37;//package ex37;
//
//import ex36.Barcode;
//import ex36.Priceable;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//public class ex37Test {
//
//     Database database = new Database(
//            list.of(new City("Paris"),
//                    new City("Budapest"),
//                    new City("Skopje"),
//                    new City("Rotterdam"),
//                    new City("Valencia"),
//                    new City("Vancouver"),
//                    new City("Amsterdam"),
//                    new City("Vienna"),
//                    new City("Sydney"),
//                    new City("New York City"),
//                    new City("London"),
//                    new City("Bangkok"),
//                    new City("Hong Kong"),
//                    new City("Dubai"),
//                    new City("Rome"),
//                    new City("Paris")
//
//            ));
//
//    @BeforeAll
//    public static void prepare() {
//
//    }
//
//    @Test
//    @DisplayName("TwoChar")
//    public void twoChar() {
//        final var expected =  List.of(new City("Valencia"),new City("Vancouver"));
//        final var actual = List.of(database.City("Valencia", "Va"),
//                database.City("Vancouver", "Va"));
//        Assertions.assertEquals(expected, actual);
//
//    }
//
//    @Test
//    @DisplayName("noChar")
//    public void noChar() {
//
//        final var expected = List.of(new City("Valencia"),new City("Vancouver"));
//        final var actual = List.of(database.City("Valencia", ""),
//                database.City("Vancouver", ""));
//        Assertions.assertEquals(expected, actual);
//
//    }
//}
