package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zakirskikh.dao.ReportDao;
import zakirskikh.form.ReportForm;
import zakirskikh.model.Report;
import zakirskikh.model.SystemUser;

/**
 * Created by Anvar on 17/11/2016.
 */
@Controller
public class ReportController {

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String getReportPage(Model model) {
        model.addAttribute("is_report_category", true);

        if (!SystemUser.getCurrent().getRole().isAdmin()) {
            model.addAttribute("is_user", true);
        }

        model.addAttribute("reportForm", new ReportForm());

        return "report";
    }

    @RequestMapping(value = "/client/report", method = RequestMethod.GET)
    public String getClientReportPage(Model model) {
        model.addAttribute("is_report_category", true);

        if (!SystemUser.getCurrent().getRole().isAdmin()) {
            model.addAttribute("is_user", true);
        }

        model.addAttribute("reportForm", new ReportForm());

        return "report";
    }

    @RequestMapping(value = "/report/add", method = RequestMethod.POST)
    public String addReport(@ModelAttribute("reportForm") ReportForm reportForm, Model model) {

        ReportDao.save(new Report(
                reportForm.getText(),
                SystemUser.getCurrent().getPersonId()
        ));

        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/report/{report_id}/delete", method = RequestMethod.GET)
    public String deleteReport(@PathVariable("report_id") int reportId, Model model) {

        ReportDao.delete(reportId);

        return "redirect:/dashboard";
    }

}
