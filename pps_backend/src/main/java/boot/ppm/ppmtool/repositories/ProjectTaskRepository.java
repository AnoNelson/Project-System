package boot.ppm.ppmtool.repositories;

import boot.ppm.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nick on 1/6/2020.
 */
@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {
    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
    ProjectTask findByProjectSequence(String sequence);

}
