package com.beadando.demo.Controller;

import com.beadando.demo.repository.semesterRepository;
import com.beadando.demo.repository.userRepository;
import com.beadando.demo.semester.Semester;
import com.beadando.demo.users.User;
import com.beadando.demo.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.slf4j.LoggerFactory;

@Controller
public class Control {


    private UserService userService;

    @Autowired
    userRepository userRepo;

    @Autowired
    semesterRepository semesterRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/")
    public String valami(User user){
        System.out.println(user.getId());
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

    //@PostMapping("/reg")
    @RequestMapping(value={"/reg"}, method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute User user) {
        logger.info("hello");
        logger.debug(user.getFirstname());
        logger.debug(user.getLastname());
        logger.debug(user.getUsername());
        logger.debug(user.getPassword());
        logger.debug(user.getConfirmpassword());
        logger.debug(user.getFaculty());
        /***********************Valamiért nem tudtam elérni interfacen keresztül az userRepot  **************/
        User usercheck = userRepo.findByUsername(user.getUsername());
        if (usercheck != null) return "register";
        else {
            if (user.getFaculty() != "") {
                logger.info("Adatbázisba mentem.");
                User u = userRepo.save(user);
            }
            else{
                logger.debug("Nem lehet üres");
            }
        }
        return "index";
    }

    @RequestMapping(value={"/upload"}, method = RequestMethod.POST)
    public String grettingSemester(@ModelAttribute Semester semester, @CurrentSecurityContext(expression="authentication?.name") String username){
        logger.info("Semester");
        semester.setUsername(username);

        System.out.println("ID : " + semester.getId());
        Semester s = semesterRepo.save(semester);
        //Semester c = semesterRepo.saveAndFlush(semester);

        logger.info(semester.getSemester());
        logger.info(semester.getSubjects());
        logger.info(semester.getUsername());

        return "main";
    }


/*
    @Transactional
    public void insertWithQuery(Semester semester) {
        entityManager.createNativeQuery("INSERT INTO person (id, first_name, last_name) VALUES (?,?,?)")
                .setParameter(1, semester.getId())
                .setParameter(2, semester.getSemester())
                .setParameter(3, semester.getSubjects())
                .executeUpdate();
    }
*/
}


