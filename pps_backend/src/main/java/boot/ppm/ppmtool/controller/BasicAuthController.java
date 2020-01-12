package boot.ppm.ppmtool.controller;

import boot.ppm.ppmtool.domain.AuthBean;
import boot.ppm.ppmtool.domain.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nick on 1/4/2020.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/basicauth")
public class BasicAuthController {
    @GetMapping(value = "")
    public ResponseEntity<?> getByIdentifier(){

        return new ResponseEntity<AuthBean>( new AuthBean("you passed"), HttpStatus.OK);
    }
}
