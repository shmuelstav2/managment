package project.com.managment.controllers;


import com.cloudinary.Cloudinary;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.util.Map;
import java.io.File;
import java.util.Map;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.com.managment.domain.*;
import project.com.managment.services.ProjectService;
import project.com.managment.services.UserService;
import static java.lang.System.out;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import static java.lang.System.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import  java.io.*;
import java.lang.Object.*;
import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import org.json.JSONObject;
        import org.springframework.data.jpa.domain.Specification;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
        import project.com.managment.domain.User;
        import project.com.managment.services.UserService;

import javax.imageio.ImageIO;
import java.util.List;

@Api(description = "manage all the projects data")
@Controller
@RequestMapping("/api/projects/")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @ApiOperation(value = "get project by id partner", notes = "These are some notes about the API.")
    @GetMapping({"/partner/{partner}"})
    public ResponseEntity<Set<Project>> getProjectById(@PathVariable int partner){
        return new ResponseEntity<Set<Project>>(projectService.getProjectByPartner(partner), HttpStatus.OK);
    }
    @ApiOperation(value = "get project by active or not", notes = "These are some notes about the API.")
    @GetMapping({"/active/{active}"})
    public ResponseEntity<Set<Project>> getProjectById(@PathVariable boolean active){
        return new ResponseEntity<Set<Project>>(projectService.getProjectByActive(active), HttpStatus.OK);
    }

    @ApiOperation(value = "create new project", notes = "These are some notes about the API.")
    @PostMapping
    public ResponseEntity<Project> createNewProject(@RequestBody Project project){
        return new ResponseEntity<Project>(projectService.createNewProject(project),
                HttpStatus.CREATED);
    }


    @ApiOperation(value = "get project by id number", notes = "These are some notes about the API.")
    @GetMapping({"/{id}"})
    public ResponseEntity<Project> getProjectById(@PathVariable Long id){
        return new ResponseEntity<Project>(projectService.getProjectById(id), HttpStatus.OK);
    }




    @ApiOperation(value = "update the project by the id OF the project", notes = "Please be carefull provide only the new data")
    @PutMapping({"/{id}"})
    public ResponseEntity <Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
        return new ResponseEntity<Project>(projectService.updateProject(id, project),
                HttpStatus.OK);
    }


    @ApiOperation(value = "create new purchase", notes = "These are some notes about the API.")
    @PostMapping("/purchase/{id}")
    public ResponseEntity<Purchase> createNewPurchase(@PathVariable Long id,@RequestBody Purchase purchase){
        return new ResponseEntity<Purchase>(projectService.createNewPurchase(id,purchase),
                HttpStatus.CREATED);
    }

    Path path = FileSystems.getDefault().getPath(".");
    private String UPLOADED_FOLDER ="src\\main\\resources\\static\\";
    @ApiOperation(value = "image", notes = "Please be carefull provide only the new data")
    @PostMapping({"/uploadimage/{id}"})

    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadfile,@PathVariable Long id) {


        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(Arrays.asList(uploadfile),id);



        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }
    private void saveUploadedFiles(List<MultipartFile> files,Long id) throws IOException {

        for (MultipartFile file : files) {
            File convFile = new File(file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            //return convFile;

            if (file.isEmpty()) {
                continue; //next pls
            }
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "daezoaeee",
                    "api_key", "752169828272875",
                    "api_secret", "F0asv7iKUGV4LiSOrMuCeuMqd_U"));
            Map uploadResult = cloudinary.uploader().upload(convFile, ObjectUtils.emptyMap());
           String fileName = uploadResult.get("secure_url").toString();
           projectService.updateProjectImage(id ,fileName);
        }

    }
}


