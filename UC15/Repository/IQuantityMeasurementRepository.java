package UC15.Repository;

import UC15.Model.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementRepository {
    void save(QuantityMeasurementEntity entity);
    List<QuantityMeasurementEntity> findAll();
}