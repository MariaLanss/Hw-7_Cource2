package pro.sky.hw7;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    private Map<String, Employee> employees;

    public EmployeeService() {
        employees = new HashMap<>();
        Employee e1 = new Employee("Татьяна ", "Адаменко");
        employees.put(generateKey(e1), e1);
        Employee e2 = new Employee("Рената ", "Андреева");
        employees.put(generateKey(e2), e2);
        Employee e3 = new Employee("Наталья ", "Баринова");
        employees.put(generateKey(e3), e3);
        Employee e4 = new Employee("Василий ", "Шабашов");
        employees.put(generateKey(e4), e4);
    }

    public Map<String, Employee> index(){
        return employees;
    }

    private String generateKey(Employee employee){
        return employee.getFirstName() + employee.getLastName();
    }

    public Employee add (String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(generateKey(employee))){
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(generateKey(employee), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        if (!employees.remove(generateKey(employee), employee)){
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Employee find(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        Employee employeeFound = employees.get(generateKey(employee));
        if (employeeFound == null || (!employeeFound.getFirstName().equals(firstName) || !employeeFound.getLastName().equals(lastName))) {
            throw new EmployeeNotFoundException();
        }
        return employeeFound;
    }
}