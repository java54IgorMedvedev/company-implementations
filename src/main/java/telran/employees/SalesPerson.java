package telran.employees;

import org.json.JSONObject;

public class SalesPerson extends Employee {
    private static final long serialVersionUID = 1L;
    private int wage;
    private int hours;
    private float percent;
    private long sales;

    public SalesPerson() {}

    public SalesPerson(long id, int basicSalary, String department, int wage, int hours, float percent, long sales) {
        super(id, basicSalary, department);
        this.wage = wage;
        this.hours = hours;
        this.percent = percent;
        this.sales = sales;
    }

    @Override
    public int computeSalary() {
        return getBasicSalary() + wage * hours + Math.round(percent * sales / 100);
    }

    @Override
    protected void fillJSONObject(JSONObject jsonObject) {
        super.fillJSONObject(jsonObject);
        jsonObject.put("wage", wage);
        jsonObject.put("hours", hours);
        jsonObject.put("percent", percent);
        jsonObject.put("sales", sales);
    }

    @Override
    protected void fillEmployee(JSONObject jsonObject) {
        super.fillEmployee(jsonObject);
        wage = jsonObject.getInt("wage");
        hours = jsonObject.getInt("hours");
        percent = (float) jsonObject.getDouble("percent");
        sales = jsonObject.getLong("sales");
    }
}
