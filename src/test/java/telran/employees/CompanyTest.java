package telran.employees;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class CompanyTest {
    private static final long ID1 = 123;
    private static final int SALARY1 = 1000;
    private static final String DEPARTMENT1 = "QA";
    private static final long ID2 = 120;
    private static final int SALARY2 = 2000;
    private static final long ID3 = 125;
    private static final int SALARY3 = 3000;
    private static final String DEPARTMENT2 = "Development";
    protected Employee empl1 = new WageEmployee(ID1, SALARY1, DEPARTMENT1, 100, 10);
    protected Employee empl2 = new Manager(ID2, SALARY2, DEPARTMENT1, 2);
    protected Employee empl3 = new SalesPerson(ID3, SALARY3, DEPARTMENT2, 100, 10, 1.0f, 10000);

    protected Company company;

    abstract void setCompany();

    @Test
    void testAddEmployee() {
        Employee empl = new Employee(200, SALARY1, DEPARTMENT1);
        company.addEmployee(empl);
        assertThrows(IllegalStateException.class, () -> company.addEmployee(empl));
        assertThrows(IllegalStateException.class, () -> company.addEmployee(empl1));
    }

    @Test
    void testGetEmployee() {
        assertEquals(empl1, company.getEmployee(ID1));
        assertNull(company.getEmployee(999)); 
    }

    @Test
    void testRemoveEmployee() {
        assertEquals(empl1, company.removeEmployee(ID1));
        assertThrows(NoSuchElementException.class, () -> company.removeEmployee(ID1));
    }

    @Test
    void testGetDepartmentBudget() {
        assertEquals(SALARY1 + 100 * 10 + SALARY2 * 2, company.getDepartmentBudget(DEPARTMENT1));
        assertEquals(SALARY3 + 100 * 10 + 10000 * 1.0f / 100, company.getDepartmentBudget(DEPARTMENT2));
        assertEquals(0, company.getDepartmentBudget("NonexistentDepartment"));
    }

    @Test
    void testIterator() {
        Employee[] expected = {empl2, empl1, empl3};
        Iterator<Employee> it = company.iterator();
        int index = 0;
        while (it.hasNext()) {
            assertEquals(expected[index++], it.next());
        }
        assertEquals(expected.length, index);
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void testGetDepartments() {
        String[] expected = {DEPARTMENT1, DEPARTMENT2};
        Arrays.sort(expected);
        assertArrayEquals(expected, company.getDepartments());
    }

    @Test
    void testGetManagersWithMostFactor() {
        company.addEmployee(new Manager(300, SALARY1, DEPARTMENT1, 2.5f));
        Manager[] managersExpected = {
                new Manager(300, SALARY1, DEPARTMENT1, 2.5f),
                new Manager(125, SALARY3, DEPARTMENT2, 3.0f)
        };
        assertArrayEquals(managersExpected, company.getManagersWithMostFactor());
    }
}

