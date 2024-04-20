package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private EmployeeInfo employeeInfo;
    private List<String> childNames;
    private List<String> childIdNumbers;

	private static final int GRADE_1_SALARY = 3000000;
    private static final int GRADE_2_SALARY = 5000000;
    private static final int GRADE_3_SALARY = 7000000;

	private static final int NON_TAXABLE_INCOME_LIMIT = 54000000;
    private static final int PER_CHILD_DEDUCTION = 4500000;
    private static final int SPOUSE_DEDUCTION = 1500000;
	
    public Employee(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
        this.childNames = new LinkedList<>();
        this.childIdNumbers = new LinkedList<>();
    }
	
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			employeeInfo.setMonthlySalary(GRADE_1_SALARY);
			if (employeeInfo.isForeigner()) {
				int salary = (int) (3000000 * 1.5);
				employeeInfo.setMonthlySalary(salary);
			}
		}else if (grade == 2) {
			employeeInfo.setMonthlySalary(GRADE_2_SALARY);
			if (employeeInfo.isForeigner()) {
				int salary = (int) (3000000 * 1.5);
				employeeInfo.setMonthlySalary(salary);
			}
		}else if (grade == 3) {
			employeeInfo.setMonthlySalary(GRADE_3_SALARY);
			if (employeeInfo.isForeigner()) {
				int salary = (int) (3000000 * 1.5);
				employeeInfo.setMonthlySalary(salary);
			}
		}
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		employeeInfo.setSpouseName(spouseName);
		employeeInfo.setSpouseIdNumber(spouseIdNumber);
	}

	private int calculateTax() {
        int totalIncome = employeeInfo.getMonthlySalary() * employeeInfo.getMonthWorkingInYear() + employeeInfo.getOtherMonthlyIncome() * employeeInfo.getMonthWorkingInYear();
        int totalDeductible = employeeInfo.getAnnualDeductible();
        if (employeeInfo.getSpouseIdNumber().equals("")) {
            totalDeductible += SPOUSE_DEDUCTION;
        }
        totalDeductible += childIdNumbers.size() * PER_CHILD_DEDUCTION;
        int taxableIncome = Math.max(0, totalIncome - totalDeductible);
        return (int) (taxableIncome * 0.05);
    }

    public int getAnnualIncomeTax() {
        LocalDate date = LocalDate.now();
        if (date.getYear() == employeeInfo.getJoinDate().getYear()) {
            int month = date.getMonthValue() - employeeInfo.getJoinDate().getMonthValue();
			employeeInfo.setMonthWorkingInYear(month);
        } else {
            employeeInfo.setMonthWorkingInYear(12);;
        }
        return calculateTax();
    }
}