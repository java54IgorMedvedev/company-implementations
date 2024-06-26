package telran.employees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;

public interface Company extends Iterable<Employee>{
	public void addEmployee(Employee empl) ;
	public Employee getEmployee(long id) ;
	public Employee removeEmployee(long id);
	public int getDepartmentBudget(String department);
	public String[] getDepartments();
	public Manager[] getManagersWithMostFactor();
}