package boot.ppm.ppmtool.repositories;

import boot.ppm.ppmtool.domain.BackLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Nick on 1/6/2020.
 */
@Repository
public interface BackLogRepository extends CrudRepository<BackLog,Long> {
   Optional<BackLog> findByProjectIdentifier(String identifier);
}
