package com.beadando.demo.Controller;

import com.beadando.demo.repository.RoleRepository;
import com.beadando.demo.repository.TantargyRepository;
import com.beadando.demo.repository.semesterRepository;
import com.beadando.demo.repository.userRepository;
import com.beadando.demo.semester.Semester;
import com.beadando.demo.tantargy.Tantargy;
import com.beadando.demo.users.Role;
import com.beadando.demo.users.User;
import com.beadando.demo.service.UserService;
import org.hibernate.id.uuid.StandardRandomStrategy;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Control {


    private UserService userService;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    userRepository userRepo;

    @Autowired
    TantargyRepository tantagyRepo;

    @Autowired
    semesterRepository semesterRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String USER_ROLE = "ROLE_USER";

    @RequestMapping(value = "/")
    public String main(@CurrentSecurityContext(expression="authentication?.name") String username,Model model){
        int ID = userRepo.finduserRole(userRepo.findID(username));
        if (roleRepo.findRole(ID).equals("ROLE_ADMIN")) return "tantargy";

        List<Tantargy> tantargy = tantagyRepo.findTantargyak("1");
        model.addAttribute("list", "alma");
        return "main";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/regist")
    public String regist(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @RequestMapping(value = "/tantargy")
    public String vbn(Model model){
        model.addAttribute("tantargy",new Tantargy());
        return "tantargy";
    }


    @RequestMapping(value={"/reg"}, method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute User user, Model model) {
        User usercheck = userRepo.findByUsername(user.getUsername());
        if (usercheck != null) {
            model.addAttribute("warningUsername","Van m??r ilyen felhaszn??l??.");
            return "register";
        }
        else {
            if (!user.getFaculty().equals("")) {
                System.out.println(user.getPassword());
                System.out.println(user.getConfirmpassword());
                if (user.getPassword().equals(user.getConfirmpassword())){
                    Role userRole = roleRepo.findByRole("ROLE_USER");

                    if (userRole != null){
                        user.getRoles().add(userRole);
                    }
                    else{
                        user.addRoles("ROLE_USER");
                    }
                    User u = userRepo.save(user);
                    return "index";
                }
                else model.addAttribute("warningPassword","Nem egyezik a jelsz??.");

            }
            else{
                logger.debug("Nem lehet ??res");
                model.addAttribute("warningFaculty","Nem lehet ??res a k??pz??si szak.");
                return "register";
            }
        }
        return "register";
    }

    @RequestMapping(value={"/upload"}, method = RequestMethod.POST,params = "save")
    public String save(@ModelAttribute Semester semester,
                       @CurrentSecurityContext(expression="authentication?.name") String username,
                       Model model) {

        if (semester.getSemester() != null && semester.getSubjects() != null){

            Semester s = semesterRepo.findBysubjects(semester.getSubjects());

            if (s == null){
                String sql = "INSERT INTO SEMESTERS " + "(SEMESTER,SUBJECTS,USERNAME) VALUES(?,?,?)";
                jdbcTemplate.update(new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setString(1,semester.getSemester());
                        ps.setString(2,semester.getSubjects());
                        ps.setString(3,username);
                        return ps;
                    }
                });
            }
            else{
                model.addAttribute("stream", "M??r felvetted ezt a t??rgyat!");
            }
        }
        else{
            model.addAttribute("stream1", "Mez??k kit??lt??se k??telez??");
        }

        return "main";
    }

    @RequestMapping(value={"/upload"}, method = RequestMethod.POST,params = "exit")
    public void exi(HttpServletResponse response) throws IOException {
        response.sendRedirect("/logout");
    }


    @RequestMapping(value={"/upload"}, method = RequestMethod.POST,params = "show")
    public String show(@ModelAttribute Semester semester,
                       @CurrentSecurityContext(expression="authentication?.name") String username,
                       Model model) {
        model.addAttribute("steam1",semesterRepo.findSubjects(username));
        return "main";

    }


    @RequestMapping(value={"/tan"}, method = RequestMethod.POST,params = "logout")
    public void logout(HttpServletResponse response) throws IOException {
        response.sendRedirect("/logout");
    }

    @RequestMapping(value={"/tan"}, method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Tantargy tantargy, Model model) {
        if (tantargy.getSem().equals("") || tantargy.getSubjectTime().equals("")){
            model.addAttribute("warningSemandTime","Minden mez?? kit??lt??se k??telez??");
        }else{
            tantagyRepo.save(tantargy);
        }
        ArrayList<Tantargy> tan = new ArrayList<>(tantagyRepo.findTantargyak("1"));
        model.addAttribute("list", tantagyRepo.findTantargyak("1"));
        return "tantargy";
    }


}


