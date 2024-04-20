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
	
    public Employee(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
        this.childNames = new LinkedList<>();
        this.childIdNumbers = new LinkedList<>();
    }
	
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			employeeInfo.setMonthlySalary(3000000);
			if (employeeInfo.isForeigner()) {
				int salary = (int) (3000000 * 1.5);
				employeeInfo.setMonthlySalary(salary);
			}
		}else if (grade == 2) {
			employeeInfo.setMonthlySalary(5000000);
			if (employeeInfo.isForeigner()) {
				int salary = (int) (3000000 * 1.5);
				employeeInfo.setMonthlySalary(salary);
			}
		}else if (grade == 3) {
			employeeInfo.setMonthlySalary(7000000);
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

	public int getAnnualIncomeTax() {
		LocalDate date = LocalDate.now();

		if (date.getYear() == employeeInfo.getJoinDate().getYear()) {
			int month = date.getMonthValue() - employeeInfo.getJoinDate().getMonthValue();
			employeeInfo.setMonthWorkingInYear(month);
		}else {
			employeeInfo.setMonthWorkingInYear(12);
		}

		return TaxFunction.calculateTax(employeeInfo.getMonthlySalary(), employeeInfo.getOtherMonthlyIncome(), employeeInfo.getMonthWorkingInYear(), employeeInfo.getAnnualDeductible(), employeeInfo.getSpouseIdNumber().equals(""), childIdNumbers.size());
	}
}