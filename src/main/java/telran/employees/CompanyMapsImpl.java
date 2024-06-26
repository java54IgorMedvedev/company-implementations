package telran.employees;

import java.util.*;

public class CompanyMapsImpl implements Company {
    private TreeMap<Long, Employee> employees = new TreeMap<>();
    private HashMap<String, List<Employee>> employeesDepartment = new HashMap<>();
    private TreeMap<Float, List<Manager>> factorManagers = new TreeMap<>();

    @Override
    public Iterator<Employee> iterator() {
        return employees.values().iterator();
    }

    @Override
    public void addEmployee(Employee empl) {
        long id = empl.getId();
        if (employees.containsKey(id)) {
            throw new IllegalStateException("Employee with ID " + id + " already exists");
        }
        employees.put(id, empl);
        employeesDepartment.computeIfAbsent(empl.getDepartment(), k -> new ArrayList<>()).add(empl);
        if (empl instanceof Manager) {
            Manager manager = (Manager) empl;
            factorManagers.computeIfAbsent(manager.getFactor(), k -> new ArrayList<>()).add(manager);
        }
    }

    @Override
    public Employee getEmployee(long id) {
        return employees.get(id);
    }

    @Override
    public Employee removeEmployee(long id) {
        Employee removed = employees.remove(id);
        if (removed != null) {
            List<Employee> departmentList = employeesDepartment.get(removed.getDepartment());
            if (departmentList != null) {
                departmentList.remove(removed);
            }
            if (removed instanceof Manager) {
                Manager manager = (Manager) removed;
                List<Manager> factorList = factorManagers.get(manager.getFactor());
                if (factorList != null) {
                    factorList.remove(manager);
                    if (factorList.isEmpty()) {
                        factorManagers.remove(manager.getFactor());
                    }
                }
            }
        }
        return removed;
    }

    @Override
    public int getDepartmentBudget(String department) {
        List<Employee> employeesInDepartment = employeesDepartment.getOrDefault(department, Collections.emptyList());
        return employeesInDepartment.stream().mapToInt(Employee::computeSalary).sum();
    }

    @Override
    public String[] getDepartments() {
        return employeesDepartment.keySet().toArray(new String[0]);
    }

    @Override
    public Manager[] getManagersWithMostFactor() {
        if (factorManagers.isEmpty()) {
            return new Manager[0];
        }
        List<Manager> managers = factorManagers.lastEntry().getValue();
        return managers.toArray(new Manager[managers.size()]);
    }
}
