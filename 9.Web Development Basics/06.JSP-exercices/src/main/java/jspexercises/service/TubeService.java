package chushka.service;

import chushka.domain.entities.Tube;
import chushka.domain.models.service.TubeSurviceModel;

import java.util.List;

public interface TubeService {

    void saveProduct(TubeSurviceModel tubeSurviceModel);

    List<TubeSurviceModel> getAll();

    TubeSurviceModel getByName(String name);

}
