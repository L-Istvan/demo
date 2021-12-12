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
    public String valami(@CurrentSecurityContext(expression="authentication?.name") String username,Model model){
        int ID = userRepo.finduserRole(userRepo.findID(username));
        if (roleRepo.findRole(ID).equals("ROLE_ADMIN")) return "tantargy";

        List<Tantargy> tantargy = tantagyRepo.findTantargyak("1");
        model.addAttribute("list", "alma");
        return "main";
    }

    @RequestMapping(value = "/index")
    public String m(){
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
            model.addAttribute("warningUsername","Van már ilyen felhasználó.");
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
                else model.addAttribute("warningPassword","Nem egyezik a jelszó.");

            }
            else{
                logger.debug("Nem lehet üres");
                model.addAttribute("warningFaculty","Nem lehet üres a képzési szak.");
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
                model.addAttribute("stream", "Már felvetted ezt a tárgyat!");
            }
        }
        else{
            model.addAttribute("stream1", "Mezők kitöltése kötelező");
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

    /*
    @RequestMapping(value={"/tan"}, method = RequestMethod.POST,params = "logout")
    public void logout(HttpServletResponse response) throws IOException {
        response.sendRedirect("/logout");
    }
     */


    @RequestMapping(value={"/tan"}, method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Tantargy tantargy, Model model) {
        tantagyRepo.save(tantargy);
        ArrayList<Tantargy> tan = new ArrayList<>(tantagyRepo.findTantargyak("1"));
        for (int i=0;i<tan.size();i++){
            System.out.println(tan.get(i).getSubjectName());
        }
        model.addAttribute("list", tantagyRepo.findTantargyak("1"));
        return "tantargy";
    }


}


