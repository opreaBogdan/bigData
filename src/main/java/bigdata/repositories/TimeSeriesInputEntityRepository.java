package bigdata.repositories;

import bigdata.entities.TimeSeriesInputEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by radu on 9/19/2016.
 */
@Repository
public interface TimeSeriesInputEntityRepository extends JpaRepository<TimeSeriesInputEntity, Long> {
}
