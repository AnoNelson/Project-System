package boot.ppm.ppmtool.service;

import boot.ppm.ppmtool.domain.BackLog;
import boot.ppm.ppmtool.domain.Project;
import boot.ppm.ppmtool.exceptions.GlobalException;
import boot.ppm.ppmtool.repositories.BackLogRepository;
import boot.ppm.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Nick on 11/18/2019.
 */
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BackLogRepository backLogRepository;

    public Project saveOrUpdate(Project project){

        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
           if(project.getId()==0){
               BackLog backLog = new BackLog();
               backLog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
               project.setBackLog(backLog);
               backLog.setProject(project);
            }
            if(project.getId()!=0){
                Optional<BackLog> pro = backLogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase());
                if(pro.isPresent()){
                    project.setBackLog(pro.get());
                }else{
                    throw new GlobalException("can not find matching project backlog for "+project.getProjectIdentifier());
                }

            }
            return projectRepository.save(project);
        }catch (Exception ex){
            throw new GlobalException("project Identifier "+ex.getMessage()+" already existed");
        }

    }

    public Project findByIdentifier(String ident){
        Optional<Project> pro = projectRepository.findByProjectIdentifier(ident.toUpperCase());
         if(pro.isPresent()){
             return pro.get();
         }else{
            throw new GlobalException("project identifier "+ident+" doesnot exist");
        }
    }
    public List<Project> getAll(){
         List<Project> li = projectRepository.findAll();
        if(li.size()!=0){
            return li;
        }else {
           throw new GlobalException("there is nothing in the database");
        }
    }

    public Project deleteByIdentifier(String ident){
        Optional<Project> pro = projectRepository.findByProjectIdentifier(ident);
        if(pro.isPresent()){
            projectRepository.delete(pro.get());
            return pro.get();
        }else{
            throw new GlobalException("project identifier "+ident+" doesnot exist");
        }
    }

}
