package alekseybykov.portfolio.hibernate.mapping.many2one.unidirectional;

import alekseybykov.portfolio.hibernate.TestContextHook;
import common.utils.SessionUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Any method on Session object fire off some event.
 * For example, session.save() will be translated to SaveOrUpdateEvent.
 * All the event is queued in ActionQueue.
 * At the end of the unit of work this actions are performed
 * in database (as DML).
 *
 * @author Aleksey Bykov
 * @since 09.06.2019
 */
@ExtendWith({TestContextHook.class})
class ManyToOneTest {

    @Test
    void testSaveManyToOneUnidirectionalRelationship() {

        Department department = new Department();
        department.setDeptName("Database development department");

        Employee firstEmployee = new Employee();
        firstEmployee.setFirstName("First Student");
        firstEmployee.setSalary(10d);
        firstEmployee.setDepartment(department);

        Employee secondEmployee = new Employee();
        secondEmployee.setFirstName("Second Student");
        secondEmployee.setSalary(15d);
        secondEmployee.setDepartment(department);

        Employee thirdEmployee = new Employee();
        thirdEmployee.setFirstName("Third Student");
        thirdEmployee.setSalary(20d);
        thirdEmployee.setDepartment(department);

        try(Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            session.save(department);
            session.save(firstEmployee);
            session.save(secondEmployee);
            session.save(thirdEmployee);

            tx.commit();
        }

        try(Session session = SessionUtil.getSession()) {
            Query<Employee> queryForEmployees = session.createQuery("from Employee", Employee.class);
            List<Employee> listOfEmployees = queryForEmployees.list();

            assertTrue(listOfEmployees.size() == 3);

            Set<Department> departments = listOfEmployees.stream()
                    .map(Employee::getDepartment)
                    .collect(Collectors.toSet());

            assertTrue(departments.size() == NumberUtils.INTEGER_ONE);
        }
    }
}
