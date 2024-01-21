package by.itacademy.processing;

import by.itacademy.entities.Specialization;
import by.itacademy.entities.Student;
import by.itacademy.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

import static by.itacademy.utils.Constants.*;

public class CriteriaQueryLanguage {

    private final EntityManager entityManager = JPAUtil.getEntityManager();
    private final EntityTransaction transaction = entityManager.getTransaction();
    private final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    public void startTransaction() {
        transaction.begin();
    }

    public List<Student> getStudentsBySpecialization(String specializationName) {
        final CriteriaQuery<Student> studentCriteriaQuery = criteriaBuilder.createQuery(Student.class);
        final Root<Specialization> specializationRoot = studentCriteriaQuery.from(Specialization.class);
        studentCriteriaQuery.select(specializationRoot.get(STUDENTS_LIST_FIELD))
                .where(criteriaBuilder.equal(specializationRoot.get(NAME_FIELD), specializationName));
        return entityManager.createQuery(studentCriteriaQuery).getResultList();
    }

    public boolean updateSpecializationName(Long idForUpdate, String newSpecialisationName) {
        final CriteriaUpdate<Specialization> specializationCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Specialization.class);
        final Root<Specialization> specializationRoot = specializationCriteriaUpdate.from(Specialization.class);
        specializationCriteriaUpdate.set(NAME_FIELD, newSpecialisationName).where(criteriaBuilder.equal(specializationRoot.get(ID_FIELD), idForUpdate));
        final int executeUpdate = entityManager.createQuery(specializationCriteriaUpdate).executeUpdate();

        return executeUpdate > 0;
    }

    public List<Object[]> getNamesAndCountsFromSpecializations() {
        final CriteriaQuery<Object[]> criteriaBuilderQuery = criteriaBuilder.createQuery(Object[].class);
        final Root<Specialization> specializationRoot = criteriaBuilderQuery.from(Specialization.class);
        criteriaBuilderQuery.multiselect(specializationRoot.get(NAME_FIELD), specializationRoot.get(DISCIPLINE_COUNT_FIELD));
        return entityManager.createQuery(criteriaBuilderQuery).getResultList();
    }

    public void closeEntityManager() {
        transaction.commit();
        entityManager.close();
    }
}
