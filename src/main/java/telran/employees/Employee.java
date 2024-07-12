package telran.employees;

import java.util.Objects;
import java.io.Serializable;
import org.json.JSONObject;

import telran.io.JSONable;

public class Employee implements Serializable, JSONable {
    private static final long serialVersionUID = 1L;
    private long id;
    private int basicSalary;
    private String department;

    public Employee() {
    }

    public Employee(long id, int basicSalary, String department) {
        this.id = id;
        this.basicSalary = basicSalary;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public String getDepartment() {
        return department;
    }

    public int computeSalary() {
        return basicSalary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj != null && getClass() == obj.getClass()) {
            Employee other = (Employee) obj;
            result = id == other.id;
        }
        return result;
    }


    @Override
    public String getJSON() {
        JSONObject jsonObject = new JSONObject();
        fillJSONObject(jsonObject);
        return jsonObject.toString();
    }

    protected void fillJSONObject(JSONObject jsonObject) {
        fillClassName(jsonObject);
        jsonObject.put("id", id);
        jsonObject.put("department", department);
        jsonObject.put("basicSalary", basicSalary);
    }

    protected void fillClassName(JSONObject jsonObject) {
        if (!jsonObject.has("className")) {
            jsonObject.put("className", getClass().getName());
        }
    }

    @Override
    public void setObject(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String className = jsonObject.getString("className");
        try {
            Employee empl = (Employee) Class.forName(className)
                    .getConstructor().newInstance();
            empl.fillEmployee(jsonObject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void fillEmployee(JSONObject jsonObject) {
        id = jsonObject.getLong("id");
        department = jsonObject.getString("department");
        basicSalary = jsonObject.getInt("basicSalary");
    }
}
