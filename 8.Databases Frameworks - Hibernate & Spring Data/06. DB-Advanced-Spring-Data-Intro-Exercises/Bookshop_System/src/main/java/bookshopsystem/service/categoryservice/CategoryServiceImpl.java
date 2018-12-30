package bookshopsystem.service.categoryservice;

import bookshopsystem.domain.entities.Category;
import bookshopsystem.repository.CategoryRepository;
import bookshopsystem.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORIES_FILE_PATH =
            "C:\\Users\\Vlad\\Desktop\\AdvancedQuerying\\src\\main\\resources\\files\\categories.txt";

    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {

        if(categoryRepository.count() != 0){
            return;
        }

        String[] categoryFileContent =
                fileUtil.getFileContent(CATEGORIES_FILE_PATH);

        for (String currentCategoryDate : categoryFileContent) {
            Category category = new Category();
            category.setName(currentCategoryDate);
            categoryRepository.saveAndFlush(category);
        }



    }
}
