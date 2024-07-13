package telran.employees;

import org.json.JSONObject;

public class WageEmployee extends Employee {
    private static final long serialVersionUID = 1L;
    private int wage;
    private int hours;

    public WageEmployee() {}

    public WageEmployee(long id, int basicSalary, String department, int wage, int hours) {
        super(id, basicSalary, department);
        this.wage = wage;
        this.hours = hours;
    }

    @Override
    public int computeSalary() {
        return getBasicSalary() + wage * hours;
    }

    @Override
    protected void fillJSONObject(JSONObject jsonObject) {
        super.fillJSONObject(jsonObject);
        jsonObject.put("wage", wage);
        jsonObject.put("hours", hours);
    }

    @Override
    protected void fillEmployee(JSONObject jsonObject) {
        super.fillEmployee(jsonObject);
        wage = jsonObject.getInt("wage");
        hours = jsonObject.getInt("hours");
    }
}
