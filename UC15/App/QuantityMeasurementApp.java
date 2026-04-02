package UC15.App;

import UC15.Controller.QuantityMeasurementController;
import UC15.Repository.QuantityMeasurementCacheRepository;
import UC15.Service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        var repo = QuantityMeasurementCacheRepository.getInstance();
        var service = new QuantityMeasurementServiceImpl(repo);
        var controller = new QuantityMeasurementController(service);

        controller.performOperations();
    }
}
