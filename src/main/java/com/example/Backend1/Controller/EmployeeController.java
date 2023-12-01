package com.example.Backend1.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend1.Mode.EmployeeEntity;
import com.example.Backend1.Repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins="*")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get all employees
	@GetMapping("/employees")
	public List<EmployeeEntity> getAllEmployees(){
		return employeeRepository.findAll();	
	}
	
	//create a employee
	@PostMapping("/employees")
	public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) {
		return employeeRepository.save(employee);
	}
	
	//Get a employee
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Long id) {
		EmployeeEntity employee = employeeRepository.findById(id)
			.orElseThrow();
	return ResponseEntity.ok(employee);
	}
	
	
	//Update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity employeeDetails){
		EmployeeEntity employee = employeeRepository.findById(id)
			.orElseThrow();
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		
		EmployeeEntity updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	//Delete employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		EmployeeEntity employee = employeeRepository.findById(id)
				.orElseThrow();
		employeeRepository.delete(employee);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Delete", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/one")
	public String getString(){
		return "Hello";	
	}
}
