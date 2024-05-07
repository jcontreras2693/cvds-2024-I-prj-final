package co.edu.eci.cvds.service;

import co.edu.eci.cvds.exceptions.ServiceException;
import co.edu.eci.cvds.model.Category;
import co.edu.eci.cvds.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category getCategory(int id) throws ServiceException {
        Optional<Category> result = categoryRepository.findById(id);
        if(result.isEmpty()){
            throw new ServiceException(ServiceException.nonExistentCategory);
        }
        return result.get();
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category updateCategory(Category category) throws ServiceException {
        Category update = getCategory(category.getCategoryId());
        categoryRepository.delete(update);
        return categoryRepository.save(category);
    }

    public void deleteCategory(int id) throws ServiceException {
        Category category = getCategory(id);
        categoryRepository.delete(category);
    }

    public void deleteCategory(Category category){
        categoryRepository.delete(category);
    }

}
