package by.itacademy;

import by.itacademy.processing.CriteriaQueryLanguage;

public class Main {

    public static void main(String[] args) {
        final CriteriaQueryLanguage criteriaQueryLanguage = new CriteriaQueryLanguage();

        criteriaQueryLanguage.startTransaction();

        /*
          1. Выбрать студентов по названию специализации
         */
        criteriaQueryLanguage.getStudentsBySpecialization("New spec").forEach(System.out::println);

        /*
          2. Обновить название специализации
         */
        System.out.println(criteriaQueryLanguage.updateSpecializationName(2L, "New spec"));

        /*
          3. Выбрать название и количество дисциплин на специализации
         */
        criteriaQueryLanguage.getNamesAndCountsFromSpecializations().forEach(s -> System.out.println(s[0] + ", " + s[1]));

        criteriaQueryLanguage.closeEntityManager();
    }
}
