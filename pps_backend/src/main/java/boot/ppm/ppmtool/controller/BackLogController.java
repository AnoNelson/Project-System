package boot.ppm.ppmtool.controller;



import boot.ppm.ppmtool.domain.Project;
import boot.ppm.ppmtool.domain.ProjectTask;
import boot.ppm.ppmtool.service.CustomerErrorGenerator;
import boot.ppm.ppmtool.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Nick on 1/8/2020.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/backLog")
public class BackLogController {

    @Autowired
    private ProjectTaskService projectTaskService;
    @Autowired
    private CustomerErrorGenerator customerErrorGenerator;


    @PostMapping("/{backLog_id}")
    public ResponseEntity<?> createBackLog(@Valid @RequestBody ProjectTask projectTask,BindingResult bindResult,
                                             @PathVariable String backLog_id){

        if (customerErrorGenerator.getErrors(bindResult) != null) {
            return new CustomerErrorGenerator().getErrors(bindResult);
        } else {

            return new ResponseEntity<ProjectTask>(projectTaskService.addProjectTask(backLog_id,projectTask), HttpStatus.CREATED);
        }

    }

    @GetMapping("/{backLog_id}")
    public ResponseEntity<List<ProjectTask>> getProjectTask( @PathVariable String backLog_id){

      return new ResponseEntity<List<ProjectTask>>(projectTaskService.findBackLogById(backLog_id),HttpStatus.OK);
    }
    @GetMapping("/{backLog_id}/{pt_id}")
    public ResponseEntity<ProjectTask> findProjectTask( @PathVariable String backLog_id,@PathVariable String pt_id){

        return new ResponseEntity<ProjectTask>(projectTaskService.findBySequence(backLog_id, pt_id),HttpStatus.OK);
    }
    @PutMapping("/{backLog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask( @Valid @RequestBody ProjectTask projectTask,
                                                           BindingResult bindResult,@PathVariable String backLog_id,@PathVariable String pt_id){


        if (customerErrorGenerator.getErrors(bindResult) != null) {
            return new CustomerErrorGenerator().getErrors(bindResult);
        } else {

            return new ResponseEntity<ProjectTask>(projectTaskService.updateProjectTask(projectTask, backLog_id, pt_id),HttpStatus.CREATED);
        }

    }
    @DeleteMapping("/{backLog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask( @PathVariable String backLog_id,@PathVariable String pt_id){
      return new ResponseEntity<Object>(projectTaskService.deleteProjectTask(backLog_id,pt_id),HttpStatus.CREATED);
    }
}
