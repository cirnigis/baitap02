package doranie.com.service.impl;

import java.io.File;
import java.util.List;

import doranie.com.dao.CategoryDao;
import doranie.com.dao.impl.CategoryDaoJPA;
import doranie.com.models.Category;
import doranie.com.service.CategoryService;
import doranie.com.utils.Constant;

public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = new CategoryDaoJPA();

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void edit(Category newCategory) {
        Category oldCategory = categoryDao.get(newCategory.getCateId());

        oldCategory.setCateName(newCategory.getCateName());

        if (newCategory.getIcons() != null) {

            String fileName = oldCategory.getIcons();

            File file = new File(Constant.DIR + "/" + fileName);

            if (file.exists()) {
                file.delete();
            }

            oldCategory.setIcons(newCategory.getIcons());
        }

        categoryDao.edit(oldCategory);
    }

    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }

    @Override
    public Category get(int id) {
        return categoryDao.get(id);
    }

    @Override
    public Category get(String name) {
        return categoryDao.get(name);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public List<Category> search(String keyword) {
        return categoryDao.search(keyword);
    }
}