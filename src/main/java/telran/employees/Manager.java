package telran.employees;

import java.util.Objects;

public class Manager extends Employee {
    private float factor;

    public Manager(long id, int basicSalary, String department, float factor) {
        super(id, basicSalary, department);
        this.factor = factor;
    }

    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }

    @Override
    public int computeSalary() {
        return Math.round(super.computeSalary() * factor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), factor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Manager)) return false;
        Manager manager = (Manager) obj;
        return Float.compare(manager.factor, factor) == 0;
    }
}
