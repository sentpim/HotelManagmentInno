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
import zakirskikh.model.SystemUser;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Anvar on 16/11/2016.
 */
@Controller
public class EmployeeController {

    @RequestMapping(value = "/employees" , method = RequestMethod.GET)
    public String getEmployees(Model model){
        model.addAttribute("is_employees_category", true);

        if (!SystemUser.getCurrent().getRole().isAdmin()) {
            return "redirect:/client";
        }

        model.addAttribute("employees", EmployeeDao.getAll());

        return "employees";
    }

    @RequestMapping(value = "/employees/add" , method = RequestMethod.GET)
    public String getAddEmployee(Model model){
        model.addAttribute("is_employees_category", true);

        if (!SystemUser.getCurrent().getRole().isAdmin()) {
            return "redirect:/client";
        }

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

//    @RequestMapping(value = "/employees/{employee_id}" , method = RequestMethod.GET)
//    public String getEmployee(@PathVariable(value = "employee_id") Integer employee_id){
//        Employee employee = EmployeeDao.get(employee_id);
//        return "employee-single";
//    }

    @RequestMapping(value = "/employees/{employee_id}/delete" , method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable(value = "employee_id") Integer employee_id){
        Employee employee = EmployeeDao.get(employee_id);

        EmployeeDao.delete(employee_id);

        return "redirect:/employees";
    }

    @RequestMapping(value = "/employees/{employee_id}/edit" , method = RequestMethod.GET)
    public String getEditEmployee(@PathVariable(value = "employee_id") Integer employee_id, Model model){
        Employee employee = EmployeeDao.get(employee_id);

        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setId(employee.getId());
        employeeForm.setAddress(employee.getPerson().getAddress().getAddress());
        employeeForm.setCountry(employee.getPerson().getAddress().getCountry());
        employeeForm.setCity(employee.getPerson().getAddress().getCity());
        employeeForm.setPostcode(employee.getPerson().getAddress().getPostcode());
        employeeForm.setPassportId(employee.getPerson().getPassportId());
        employeeForm.setEmail(employee.getPerson().getEmail());
        employeeForm.setFirstName(employee.getPerson().getFirstName());
        employeeForm.setLastName(employee.getPerson().getLastName());
        employeeForm.setHotelId(employee.getHotelId());
        employeeForm.setSalary(employee.getSalary());
        employeeForm.setGenderId(employee.getPerson().getGender().getGenderId());
        employeeForm.setPhoneNumber(employee.getPerson().getPhoneNumber());
        employeeForm.setPostId(employee.getPostId());
        model.addAttribute("employeeForm", employeeForm);
        model.addAttribute("posts", PostDao.getAll());
        model.addAttribute("hotels", HotelDao.getAll());

        model.addAttribute("is_edit", true);

        return "employee-add";
    }

//    @RequestMapping(value = "/employees/{employee_id}/edit" , method = RequestMethod.POST)
//    public String editEmployee(@PathVariable(value = "employee_id") Integer employee_id, Model model) {
//        Employee employee = EmployeeDao.get(employee_id);
//
//        model.addAttribute("is_edit", true);
//
//        return "redirect:/employees";
//    }

}
