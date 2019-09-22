//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.hibernate.entities.mapping.many2one.unidirectional;

import common.utils.SessionUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-05
 */
public class ManyToOneTest {

    @Test
    public void testSaveManyToOneUnidirectionalRelationship() {

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
