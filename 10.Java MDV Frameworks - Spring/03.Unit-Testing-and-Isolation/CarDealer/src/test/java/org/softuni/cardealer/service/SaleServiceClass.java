package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.entities.CarSale;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.PartSale;
import org.softuni.cardealer.domain.models.service.CarSaleServiceModel;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.domain.models.service.PartSaleServiceModel;
import org.softuni.cardealer.repository.CarRepository;
import org.softuni.cardealer.repository.CarSaleRepository;
import org.softuni.cardealer.repository.PartRepository;
import org.softuni.cardealer.repository.PartSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SaleServiceClass {

    @Autowired
    private CarSaleRepository carSaleRepository;
    @Autowired
    private PartSaleRepository partSaleRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PartRepository partRepository;

    private ModelMapper modelMapper;
    private SaleService saleService;

    @Before
    public void init(){
        modelMapper = new ModelMapper();
        saleService = new SaleServiceImpl(carSaleRepository, partSaleRepository, modelMapper);
    }

    @Test
    public void saleCarWithCorrectValues_ReturnsCorrect(){

        Car car = new Car();
        car.setMake("VW");
        car.setModel("Golf");
        car.setTravelledDistance(20000L);
        car.setParts(new ArrayList<>());

        carRepository.saveAndFlush(car);



        CarSale carSale = new CarSale();
        carSale.setCar(car);
        carSale.setCustomer(null);
        carSale.setDiscount(100.0);

        CarSaleServiceModel actual = saleService
                .saleCar(modelMapper
                    .map(carSale, CarSaleServiceModel.class));

        CarSaleServiceModel expected = modelMapper
                .map(carSaleRepository.findAll().get(0), CarSaleServiceModel.class);

        Assert.assertEquals(actual.getCar().getId(), expected.getCar().getId());
        Assert.assertEquals(actual.getCar().getMake(), expected.getCar().getMake());
        Assert.assertEquals(actual.getCar().getModel(), expected.getCar().getModel());
        Assert.assertEquals(actual.getCar().getTravelledDistance(), expected.getCar().getTravelledDistance());
        Assert.assertEquals(actual.getCar().getParts(), expected.getCar().getParts());

        Assert.assertEquals(actual.getCustomer(), expected.getCustomer());
        Assert.assertEquals(actual.getDiscount(), expected.getDiscount(), 2);
        Assert.assertEquals(actual.getId(), expected.getId());
    }

    @Test(expected = Exception.class)
    public void saleCarWithWrongValues_ThrowsException(){

        CarSaleServiceModel carSaleServiceModel = new CarSaleServiceModel();
        carSaleServiceModel.setCar(null);
        carSaleServiceModel.setCustomer(null);
        carSaleServiceModel.setDiscount(null);

        saleService.saleCar(null);
    }

    @Test
    public void salePartWithCorrectValues_ReturnsCorrect(){

        Part part = new Part();
        part.setName("door");
        part.setPrice(new BigDecimal(100L));
        part.setSupplier(null);

        partRepository.saveAndFlush(part);



        PartSale partSale = new PartSale();
        partSale.setPart(part);
        partSale.setQuantity(100);
        partSale.setCustomer(null);
        partSale.setDiscount(20.0);

        PartSaleServiceModel actual = saleService
                .salePart(modelMapper
                        .map(partSale, PartSaleServiceModel.class));

        PartSaleServiceModel expected = modelMapper
                .map(partSaleRepository.findAll().get(0), PartSaleServiceModel.class);

        Assert.assertEquals(actual.getPart().getId(), expected.getPart().getId());
        Assert.assertEquals(actual.getPart().getPrice(), expected.getPart().getPrice());
        Assert.assertEquals(actual.getPart().getName(), expected.getPart().getName());
        Assert.assertEquals(actual.getPart().getSupplier(), expected.getPart().getSupplier());

        Assert.assertEquals(actual.getQuantity(), expected.getQuantity());
        Assert.assertEquals(actual.getCustomer(), expected.getCustomer());
        Assert.assertEquals(actual.getDiscount(), expected.getDiscount(), 2);
        Assert.assertEquals(actual.getId(), expected.getId());
    }

    @Test(expected = Exception.class)
    public void salePartWithWrongValues_ThrowsException(){
        PartSaleServiceModel toBeSaved = new PartSaleServiceModel();
        toBeSaved.setPart(null);
        toBeSaved.setQuantity(null);
        toBeSaved.setCustomer(null);
        toBeSaved.setDiscount(null);

        saleService.salePart(toBeSaved);
    }

}
