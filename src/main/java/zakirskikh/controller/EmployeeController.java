package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zakirskikh.dao.EmployeeDao;
import zakirskikh.dao.HotelDao;
import zakirskikh.dao.PostDao;
import zakirskikh.form.EmployeeForm;
import zakirskikh.form.PersonProfileForm;
import zakirskikh.model.Employee;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Anvar on 16/11/2016.
 */
@Controller
public class EmployeeController {

    @RequestMapping(value = "/employees" , method = RequestMethod.GET)
    public String getEmployees(Model model){
        model.addAttribute("employees", EmployeeDao.getAll());

        return "employees";
    }

    @RequestMapping(value = "/employees/add" , method = RequestMethod.GET)
    public String getAddEmployee(Model model){
        model.addAttribute("employeeForm", new EmployeeForm());
        model.addAttribute("posts", PostDao.getAll());
        model.addAttribute("hotels", HotelDao.getAll());

        return "employee-add";
    }

    @RequestMapping(value = "/employees/add" , method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employeeForm") EmployeeForm employeeForm, Model model){
        System.out.println(employeeForm);

        EmployeeDao.save(employeeForm);

        return "redirect:/employees";
    }

    @RequestMapping(value = "/employees/{employee_id}" , method = RequestMethod.GET)
    public String getEmployee(@PathVariable(value = "employee_id") Integer employee_id){
        Employee employee = EmployeeDao.get(employee_id);
        return "employee-single";
    }

    @RequestMapping(value = "/employees/{employee_id}/edit" , method = RequestMethod.GET)
    public String getEditEmployee(@PathVariable(value = "employee_id") Integer employee_id){
        Employee employee = EmployeeDao.get(employee_id);
        return "employee-add";
    }

}
