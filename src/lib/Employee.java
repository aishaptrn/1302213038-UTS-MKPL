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
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {
        int salary;
        switch (grade) {
            case 1:
                salary = GRADE_1_SALARY;
                break;
            case 2:
                salary = GRADE_2_SALARY;
                break;
            case 3:
                salary = GRADE_3_SALARY;
                break;
            default:
                salary = 0;
        }
        if (employeeInfo.isForeigner()) {
            salary = (int) (salary * 1.5);
			employeeInfo.setMonthlySalary(salary);
        }
    }
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		employeeInfo.setSpouseName(spouseName);
		employeeInfo.setSpouseIdNumber(spouseIdNumber);
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
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
