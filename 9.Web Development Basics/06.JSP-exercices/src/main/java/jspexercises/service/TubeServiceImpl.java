package chushka.service;

import chushka.domain.entities.Tube;
import chushka.domain.models.service.TubeSurviceModel;
import chushka.repository.TubeRepository;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(TubeSurviceModel tubeSurviceModel) {
        this.tubeRepository.save(modelMapper.map(tubeSurviceModel, Tube.class));
    }

    @Override
    public List<TubeSurviceModel> getAll() {
        List<TubeSurviceModel> models = new ArrayList<>();

        this.tubeRepository.findAll()
                .forEach(tube ->{
                    TubeSurviceModel tubeModel = modelMapper.map(tube, TubeSurviceModel.class);
                    models.add(tubeModel);
                });

        return models;

    }

    @Override
    public TubeSurviceModel getByName(String name) {
        TubeSurviceModel tubeSurviceModel = modelMapper.map(this.tubeRepository.findByName(name), TubeSurviceModel.class);

        return tubeSurviceModel;
    }
}
