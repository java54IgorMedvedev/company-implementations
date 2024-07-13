package telran.employees;

import org.json.JSONObject;

public class Manager extends Employee {
    private static final long serialVersionUID = 1L;
    protected float factor; 

    public Manager() {}

    public Manager(long id, int basicSalary, String department, float factor) {
        super(id, basicSalary, department);
        this.factor = factor;
    }

    @Override
    public int computeSalary() {
        return Math.round(getBasicSalary() * factor);
    }

    @Override
    protected void fillJSONObject(JSONObject jsonObject) {
        super.fillJSONObject(jsonObject);
        jsonObject.put("factor", factor);
    }

    @Override
    protected void fillEmployee(JSONObject jsonObject) {
        super.fillEmployee(jsonObject);
        factor = (float) jsonObject.getDouble("factor");
    }
}
