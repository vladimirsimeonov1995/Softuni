package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PartsServiceTests {

    @Autowired
    private PartRepository partRepository;
    private ModelMapper modelMapper;
    private PartService partService;

    @Before
    public void init(){
        modelMapper = new ModelMapper();
        partService = new PartServiceImpl(partRepository, modelMapper);
    }

    @Test
    public void partService_savePartWithCorrectValues_ReturnsCorrect(){

        Part part = getPart();

        PartServiceModel actual = partService.savePart(modelMapper
                .map(part,PartServiceModel.class));
        PartServiceModel expected = this.modelMapper
                .map(partRepository.findAll().get(0), PartServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getSupplier(), actual.getSupplier());
    }

    @Test(expected = Exception.class)
    public void partService_savePartWithNullValues_TrowsException(){

        PartServiceModel toBeSaved = new PartServiceModel();
        toBeSaved.setName(null);
        toBeSaved.setPrice(null);
        toBeSaved.setSupplier(null);

        partService.savePart(toBeSaved);
    }

    @Test
    public void partService_editPartWithCorrectValues_ReturnsCorrect(){

        Part part = getPart();

        part = partRepository.saveAndFlush(part);

        PartServiceModel toBeEdited = new PartServiceModel();
        toBeEdited.setId(part.getId());
        toBeEdited.setName("Sedalka");
        toBeEdited.setPrice(new BigDecimal(500L));
        toBeEdited.setSupplier(null);

        PartServiceModel actual = partService.editPart(toBeEdited);
        PartServiceModel expected = this.modelMapper
                .map(partRepository.findAll().get(0), PartServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getSupplier(), actual.getSupplier());

    }



    @Test(expected = Exception.class)
    public void partService_editPartWithNullValues_ThrowsException(){

        Part part = getPart();

        part = partRepository.saveAndFlush(part);

        PartServiceModel toBeEdited = new PartServiceModel();
        toBeEdited.setId(part.getId());
        toBeEdited.setName(null);
        toBeEdited.setPrice(null);

        partService.editPart(toBeEdited);
    }

    @Test
    public void partService_deletePartWithCorrectValues_ReturnsCorrect(){

        Part part = getPart();

        part= partRepository.saveAndFlush(part);

        PartServiceModel expected = modelMapper
                .map(partRepository.findAll().get(0), PartServiceModel.class);

        PartServiceModel actual = partService.deletePart(part.getId());

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getSupplier(), actual.getSupplier());
        Assert.assertEquals(0, partRepository.findAll().size());


    }

    @Test(expected = Exception.class)
    public void partService_deletePartWithWrongId_TrowsException(){
        Part part = getPart();
        part.setId("wrongID");

        partService.deletePart(part.getId());
    }

    @Test
    public void partService_findPartByIdWithCorrectValue_ReturnsCorrect(){
        Part part = getPart();

        part = partRepository.saveAndFlush(part);

        PartServiceModel expected = modelMapper
                .map(partRepository.findAll().get(0), PartServiceModel.class);

        PartServiceModel actual = partService.findPartById(expected.getId());

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getSupplier(), actual.getSupplier());
    }

    @Test(expected = Exception.class)
    public void partService_findPartByIdWithNonExistenceId_ThrowsException(){
        partService.findPartById("wrongID");
    }

    private Part getPart() {
        Part part = new Part();
        part.setName("Far");
        part.setPrice(new BigDecimal(1000L));
        part.setSupplier(null);

        return part;
    }

}
