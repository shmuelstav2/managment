package project.com.managment.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.com.managment.domain.*;
import project.com.managment.services.PurchaseService;
import project.com.managment.services.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
        import project.com.managment.domain.Role;
        import project.com.managment.domain.User;
        import project.com.managment.services.UserService;

import java.util.List;

@Api(description = "manage all the users data")
@Controller
@RequestMapping("/api/purchases/")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @ApiOperation(value = "This will get a list of purchases.", notes = "These are some notes about the API.")
    @GetMapping
    public ResponseEntity<Set<Purchase>> getallPurchase(){
        Set<Purchase> purchases = purchaseService.getAllPurchases();
        return new ResponseEntity<Set<Purchase>>(purchases, HttpStatus.OK);
    }

   /*
    @ApiOperation(value = "get project by id number", notes = "These are some notes about the API.")
    @GetMapping({"/{id}"})
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable Long id){
        return new ResponseEntity<Purchase>(purchaseService.getPurchaseById(id), HttpStatus.OK);
    }




    @ApiOperation(value = "update the project by the id OF the project", notes = "Please be carefull provide only the new data")
    @PutMapping({"/{id}"})
    public ResponseEntity <Purchase> updateProject(@PathVariable Long id, @RequestBody Purchase project) {
        return new ResponseEntity<Purchase>(projectService.updatePurchase(id, project),
                HttpStatus.OK);
    }
  */
    Path path = FileSystems.getDefault().getPath(".");
    private String UPLOADED_FOLDER ="src\\main\\resources\\static\\";
    @ApiOperation(value = "image", notes = "Please be carefull provide only the new data")
    @PostMapping({"/purchase/uploadimage/{id}"})

    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadfile, @PathVariable Long id) {


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

            if (file.isEmpty()) {
                continue; //next pls
            }

            String fileName ="invoice"+ id.toString()+file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf('.'));
            purchaseService.updatePurchaseImage(id ,fileName);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER+fileName+"png");
            Files.write(path, bytes);
        }

    }

    @ApiOperation(value = "get images with the image name")
    @RequestMapping(value = "/image/{imageName}",method = RequestMethod.GET)
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
        File serverFile = new File( UPLOADED_FOLDER + imageName+".png");
        return Files.readAllBytes(serverFile.toPath());
    }
}


