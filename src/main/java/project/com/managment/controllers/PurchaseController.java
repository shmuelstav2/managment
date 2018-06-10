package project.com.managment.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import java.io.FileOutputStream;
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

    @ApiOperation(value = "create new purchase", notes = "These are some notes about the API.")
    @PostMapping("/{projectid}")
    public ResponseEntity<Purchase> createNewPurchase(@PathVariable("projectid") Long projectid,@RequestBody Purchase purchase){
        return new ResponseEntity<Purchase>(purchaseService.createNewPurchase(projectid,purchase),
                HttpStatus.CREATED);
    }
    @ApiOperation(value = "update purchase", notes = "These are some notes about the API.")
    @PostMapping("/update/{purchaseid}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable("purchaseid") Long purchaseid,@RequestBody Purchase purchase){
        return new ResponseEntity<Purchase>(purchaseService.updatePurchase(purchaseid,purchase),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "get purchase", notes = "These are some notes about the API.")
    @GetMapping("/get/{purchaseid}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable("purchaseid") Long purchaseid){
        return new ResponseEntity<Purchase>(purchaseService.getPurchase(purchaseid),
                HttpStatus.CREATED);
    }


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
            purchaseService.updatePurchaseImage(id ,fileName);
        }

    }
}


