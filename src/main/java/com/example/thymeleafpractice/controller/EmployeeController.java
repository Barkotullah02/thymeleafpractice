package com.example.thymeleafpractice.controller;

import com.example.thymeleafpractice.dao.EmployeeRepository;
import com.example.thymeleafpractice.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


	private EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}


	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> employees = employeeRepository.findAllByOrderByFirstNameAsc();
		// add to the spring model
		theModel.addAttribute("employees", employees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Employee employee = new Employee();
		theModel.addAttribute("employee", employee);

		return "employees/form-employee";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id, Model theModel) {
		Optional<Employee> employee = employeeRepository.findById(id);

		theModel.addAttribute("employee", employee);
		return "employees/form-employee";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {

		employeeRepository.deleteById(id);

		return "redirect:/employees/list";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		employeeRepository.save(theEmployee);
		return "redirect:/employees/list";
	}
}










