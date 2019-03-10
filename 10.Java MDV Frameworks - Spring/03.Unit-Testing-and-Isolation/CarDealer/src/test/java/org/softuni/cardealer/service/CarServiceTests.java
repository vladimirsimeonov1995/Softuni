package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarServiceTests {

    @Autowired
    private CarRepository carRepository;
    private ModelMapper modelMapper;
    private CarService carService;

    @Before
    public void init(){
        modelMapper = new ModelMapper();
        carService = new CarServiceImpl(carRepository, modelMapper);
    }

    @Test
    public void carService_saveCarWithCorrectValues_ReturnsCorrect(){

        CarServiceModel toBeSaved = new CarServiceModel();
        toBeSaved.setMake("VW");
        toBeSaved.setModel("Golf 4");
        toBeSaved.setTravelledDistance(200000L);
        toBeSaved.setParts(null);

        CarServiceModel actual = carService.saveCar(toBeSaved);
        CarServiceModel expected = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getMake(), actual.getMake());
        Assert.assertEquals(expected.getModel(), actual.getModel());
        Assert.assertEquals(expected.getTravelledDistance(), actual.getTravelledDistance());
        Assert.assertEquals(expected.getParts(), actual.getParts());
    }

    @Test(expected = Exception.class)
    public void carService_saveCarWithNullValues_TrowsException(){

        CarServiceModel toBeSaved = new CarServiceModel();
        toBeSaved.setMake(null);
        toBeSaved.setModel(null);
        toBeSaved.setTravelledDistance(null);
        toBeSaved.setParts(null);

        carService.saveCar(toBeSaved);
    }

    @Test
    public void carService_editCarWithCorrectValues_ReturnsCorrect(){

        Car car = getCar();

        car = carRepository.saveAndFlush(car);

        CarServiceModel toBeEdited = new CarServiceModel();
        toBeEdited.setId(car.getId());
        toBeEdited.setMake("VW");
        toBeEdited.setModel("Golf mk2");
        toBeEdited.setTravelledDistance(500000L);
        toBeEdited.setParts(new ArrayList<>());

        CarServiceModel actual = carService.editCar(toBeEdited);
        CarServiceModel expected = this.modelMapper
                .map(carRepository.findAll().get(0), CarServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getMake(), actual.getMake());
        Assert.assertEquals(expected.getModel(), actual.getModel());
        Assert.assertEquals(expected.getTravelledDistance(), actual.getTravelledDistance());
        Assert.assertEquals(expected.getParts(), actual.getParts());

    }



    @Test(expected = Exception.class)
    public void carService_editCarWithNullValues_ThrowsException(){

        Car car = getCar();

        car = carRepository.saveAndFlush(car);

        CarServiceModel toBeEdited = new CarServiceModel();
        toBeEdited.setId(car.getId());
        toBeEdited.setMake(null);
        toBeEdited.setModel(null);
        toBeEdited.setTravelledDistance(null);
        toBeEdited.setParts(null);

        carService.editCar(toBeEdited);
    }

    @Test
    public void carService_deleteCarWithCorrectValues_ReturnsCorrect(){

        Car car = getCar();

        car = carRepository.saveAndFlush(car);

        CarServiceModel expected = modelMapper
                .map(carRepository.findAll().get(0), CarServiceModel.class);

        CarServiceModel actual = carService.deleteCar(car.getId());

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getMake(), actual.getMake());
        Assert.assertEquals(expected.getModel(), actual.getModel());
        Assert.assertEquals(expected.getTravelledDistance(), actual.getTravelledDistance());
        Assert.assertEquals(expected.getParts(), actual.getParts());
        Assert.assertEquals(0, carRepository.findAll().size());


    }

    @Test(expected = Exception.class)
    public void carService_deleteCarWithWrongId_TrowsException(){
        Car car = getCar();
        car.setId("wrongID");

        carService.deleteCar(car.getId());
    }

    @Test
    public void carService_findCarByIdWithCorrectValue_ReturnsCorrect(){
        Car car = getCar();

        car = carRepository.saveAndFlush(car);

        CarServiceModel expected = modelMapper
                .map(carRepository.findAll().get(0), CarServiceModel.class);

        CarServiceModel actual = carService.findCarById(expected.getId());

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getMake(), actual.getMake());
        Assert.assertEquals(expected.getModel(), actual.getModel());
        Assert.assertEquals(expected.getTravelledDistance(), actual.getTravelledDistance());
        Assert.assertEquals(expected.getParts(), actual.getParts());
    }

    @Test(expected = Exception.class)
    public void carService_findCarByIdWithNonExistenceId_ThrowsException(){
        carService.findCarById("wrongID");
    }


    private Car getCar() {
        Car car = new Car();
        car.setMake("VW");
        car.setModel("Golf 4");
        car.setTravelledDistance(200000L);
        car.setParts(null);
        return car;
    }

}
