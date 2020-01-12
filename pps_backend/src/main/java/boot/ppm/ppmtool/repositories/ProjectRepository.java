package boot.ppm.ppmtool.repositories;

import boot.ppm.ppmtool.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Nick on 11/18/2019.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
     Optional<Project> findByProjectIdentifier(String ident);
}
