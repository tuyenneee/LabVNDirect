package edu.java.spring.controller;

import edu.java.spring.dao.impl.StudentDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Controller
public class StudentController {
    @RequestMapping(value = "student/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("student.form", "command", new Student());
    }

    @Autowired
    private StudentDAOImpl studentDAO = new StudentDAOImpl();

    @RequestMapping(value = "student/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("command") Student student, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            mv = new ModelAndView("student.form", "command", student);
            mv.addObject("errors", result);
            mv.setViewName("student.form");
            return mv;
        }
        mv.setViewName("student.view");
        mv.addObject("student", student);
        if (student.getId() > 0) {
            studentDAO.update(student);
        } else {
            studentDAO.insert(student);
        }
        return new ModelAndView("redirect:/student/list");
    }

    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
    public ModelAndView listStudent(@RequestParam(value = "q", required = false) String query) {
        ModelAndView mv = new ModelAndView();
        if(query== null){
            mv.setViewName("student.list");
            mv.addObject("students", studentDAO.list());
            return mv;
        }
        else {
            mv.setViewName("student.list");
            mv.addObject("students", studentDAO.search(query));
            return mv;
        }
    }

    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id) {
        studentDAO.delete(Integer.parseInt(id));
        return "redirect:/student/list";
    }

    @RequestMapping(value = "/student/edit/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable String id) {
        Student student = studentDAO.get(Integer.parseInt(id));
        System.out.println(student.getName());
        return new ModelAndView("../student.form", "command", student);
    }

    @RequestMapping(value = "student/edit/save", method = RequestMethod.POST)
    public String saveEdit(){
        return "forward:/student/save";
    }

    @RequestMapping(value = "/student/json/{id}", method = RequestMethod.GET)
    public @ResponseBody Student viewJson(@PathVariable String id){
        return studentDAO.get(Integer.parseInt(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "redirect:/student/list";
    }

    @RequestMapping(value = "/student/avatar/save", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request, int id) throws IOException {
        if(file.isEmpty()) return "student.error";
        byte[] bytes = file.getBytes();
        System.out.println("Found -----> "+bytes.length);
        Path avatarFile = getImageFile(request, id);
        Files.write(avatarFile, file.getBytes(), StandardOpenOption.CREATE);
        System.out.println(avatarFile);
        return "redirect:/student/list";
    }

    private Path getImageFile(HttpServletRequest request, int id){
        ServletContext servletContext = request.getSession().getServletContext();
        String diskPath = servletContext.getRealPath("/");
        File folder = new File(diskPath + File.separator + "avatar" +File.separator);
        folder.mkdirs();
        return Paths.get(String.valueOf(new File(folder, String.valueOf(id)+".jpg")));
    }

    @RequestMapping(value = "student/avatar/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id, HttpServletRequest request) throws IOException {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        if(id != null){
            Path avatarPath = getImageFile(request, id);
            if(Files.exists(avatarPath)) byteOutput.write(Files.readAllBytes(avatarPath));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(byteOutput.toByteArray(), headers, HttpStatus.CREATED);
    }

}
