package UC15.Service;

import UC15.Model.QuantityDTO;

public interface IQuantityMeasurementService {
    String compare(QuantityDTO q1, QuantityDTO q2);

    QuantityDTO convert(QuantityDTO q, String targetUnit);

    QuantityDTO add(QuantityDTO q1, QuantityDTO q2);

}
