package boot.ppm.ppmtool.service;

import boot.ppm.ppmtool.domain.BackLog;
import boot.ppm.ppmtool.domain.ProjectTask;
import boot.ppm.ppmtool.exceptions.GlobalException;
import boot.ppm.ppmtool.repositories.BackLogRepository;
import boot.ppm.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Nick on 1/7/2020.
 */
@Service
public class ProjectTaskService {
    @Autowired
    private BackLogRepository backLogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask) {


        Optional<BackLog> pro = backLogRepository.findByProjectIdentifier(projectIdentifier);
        if (pro.isPresent()) {
            BackLog backLog = pro.get();
            projectTask.setBackLog(backLog);
            int backLogSequence = backLog.getpTSequence();
            backLogSequence++;
            backLog.setpTSequence(backLogSequence);
            projectTask.setProjectSequence(backLog.getProjectIdentifier() + "-" + backLogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            if (projectTask.getStatus() == " " || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }

            if (projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }
            return projectTaskRepository.save(projectTask);

        } else {
            System.out.println("exception here");
            throw new GlobalException("can not find the project with " + projectIdentifier);
        }

    }

    public List<ProjectTask> findBackLogById(String backLog_id){
        Optional<BackLog> pro = backLogRepository.findByProjectIdentifier(backLog_id);
        if(pro.isPresent()){
            return projectTaskRepository.findByProjectIdentifierOrderByPriority(backLog_id);
        }else {
            throw new GlobalException("can not find the project  with "+backLog_id);
        }

    }
    public ProjectTask findBySequence(String backLog_id,String sequence){
        Optional<BackLog> back = backLogRepository.findByProjectIdentifier(backLog_id);
        if(back.isPresent()){
            BackLog backLog = back.get();
        }else {
            throw new GlobalException("can not find the project  with "+backLog_id);
        }
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(sequence);
        if(projectTask==null){
            throw new GlobalException("Project Task "+sequence+" not found");
        }
        if(!projectTask.getProjectIdentifier().equals(backLog_id)){
            throw new GlobalException("we can find matching project task to this "+backLog_id);
        }
        return projectTaskRepository.findByProjectSequence(sequence);
    }
    public ProjectTask updateProjectTask(ProjectTask projectTask,String backLog_id,String sequence){
        if(findBySequence(backLog_id,sequence)!=null){
            return projectTaskRepository.save(projectTask);
        }else {
            throw new GlobalException("the request can be made");
        }

    }
    public String deleteProjectTask(String backLog_id,String sequence){
        ProjectTask projectTask = findBySequence(backLog_id,sequence);
        if(projectTask!=null){
            projectTaskRepository.delete(projectTask);
            return "projectTask with "+sequence +" sequence has been deleted";
        }else {
            throw new GlobalException("the request can be made");
        }
    }
}
