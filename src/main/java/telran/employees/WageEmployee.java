package telran.employees;

import org.json.JSONObject;

public class WageEmployee extends Employee {
    private static final long serialVersionUID = 1L;
    int hours;
    int wage;

    public WageEmployee(long id, int basicSalary, String department, int hours, int wage) {
        super(id, basicSalary, department);
        this.hours = hours;
        this.wage = wage;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    @Override
    public int computeSalary() {
        return getBasicSalary() + hours * wage;
    }

    @Override
    protected void fillJSONObject(JSONObject jsonObject) {
        fillClassName(jsonObject);
        super.fillJSONObject(jsonObject);
        jsonObject.put("hours", hours);
        jsonObject.put("wage", wage);
    }

    @Override
    protected void fillEmployee(JSONObject jsonObject) {
        super.fillEmployee(jsonObject);
        hours = jsonObject.getInt("hours");
        wage = jsonObject.getInt("wage");
    }
}
