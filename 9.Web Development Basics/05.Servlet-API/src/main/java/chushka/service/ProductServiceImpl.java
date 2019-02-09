package chushka.service;

import chushka.domain.entities.Product;
import chushka.domain.entities.enums.Type;
import chushka.domain.models.service.ProductServiceModel;
import chushka.repository.ProductRepository;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product =
                this.modelMapper.map(productServiceModel, Product.class);
        product.setType(Type.valueOf(productServiceModel.getType()));

        this.productRepository.save(product);
    }

    @Override
    public List<ProductServiceModel> getAll() {
        List<ProductServiceModel> models = new ArrayList<>();

        this.productRepository.findAll()
                .forEach(product ->{
                    ProductServiceModel productServiceModel =
                            this.modelMapper.map(product, ProductServiceModel.class);

                    productServiceModel.setType(product.getType().name());

                    models.add(productServiceModel);
                });

        return models;

    }

    @Override
    public ProductServiceModel getByName(String name) {
        Product product = this.productRepository.findByName(name);

        ProductServiceModel psm = this.modelMapper.map(product, ProductServiceModel.class);
        psm.setType(product.getType().name());

        return psm;
    }
}
