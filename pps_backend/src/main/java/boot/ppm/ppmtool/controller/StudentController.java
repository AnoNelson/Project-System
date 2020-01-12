package boot.ppm.ppmtool.controller;

import boot.ppm.ppmtool.domain.Project;
import boot.ppm.ppmtool.service.CustomerErrorGenerator;
import boot.ppm.ppmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Nick on 11/18/2019.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/project")
public class StudentController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private CustomerErrorGenerator customerErrorGenerator;

    @PostMapping(value = "")
    public ResponseEntity<?> returnall(@Valid @RequestBody Project project,BindingResult bindResult) {
        if (customerErrorGenerator.getErrors(bindResult) != null) {
            return new CustomerErrorGenerator().getErrors(bindResult);
        } else {

            return new ResponseEntity<Project>(projectService.saveOrUpdate(project), HttpStatus.CREATED);
        }

    }

    @GetMapping(value = "/{ident}")
    public ResponseEntity<?> getByIdentifier(@PathVariable String ident){

        return new ResponseEntity<Project>( projectService.findByIdentifier(ident),HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<List<Project>>(projectService.getAll(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{ident}")
    public ResponseEntity<?> deleteByIdentifier(@PathVariable String ident){
        return new ResponseEntity<>(projectService.deleteByIdentifier(ident),HttpStatus.OK);
    }
}
