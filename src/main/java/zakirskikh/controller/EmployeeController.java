package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zakirskikh.dao.EmployeeDao;
import zakirskikh.model.Employee;

/**
 * Created by Anvar on 16/11/2016.
 */
@Controller
public class EmployeeController {

    @RequestMapping(value = "/employees" , method = RequestMethod.GET)
    public String getEmployees(){
        return "hotels";
    }

    @RequestMapping(value = "/employees/add" , method = RequestMethod.GET)
    public String getAddEmployee(){
        return "employee-add";
    }

    @RequestMapping(value = "/employees/add" , method = RequestMethod.POST)
    public String addEmployee(){
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
