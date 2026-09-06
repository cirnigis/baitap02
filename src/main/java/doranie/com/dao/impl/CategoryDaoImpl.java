package doranie.com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import doranie.com.dao.CategoryDao;
import doranie.com.models.Category;

public class CategoryDaoImpl extends DBConnectionProduct implements CategoryDao {

    @Override
    public void insert(Category category) {

        String sql = "INSERT INTO Category(cate_name, icons) VALUES (?, ?)";

        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, category.getCateName());
            ps.setString(2, category.getIcons());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Category category) {

        String sql = "UPDATE Category SET cate_name = ?, icons = ? WHERE cate_id = ?";

        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, category.getCateName());
            ps.setString(2, category.getIcons());
            ps.setInt(3, category.getCateId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM Category WHERE cate_id = ?";

        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category get(int id) {

        String sql = "SELECT * FROM Category WHERE cate_id = ?";

        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Category category = new Category();

                category.setCateId(rs.getInt("cate_id"));
                category.setCateName(rs.getString("cate_name"));
                category.setIcons(rs.getString("icons"));

                return category;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Category get(String name) {

        String sql = "SELECT * FROM Category WHERE cate_name = ?";

        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Category category = new Category();

                category.setCateId(rs.getInt("cate_id"));
                category.setCateName(rs.getString("cate_name"));
                category.setIcons(rs.getString("icons"));

                return category;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Category> getAll() {

        List<Category> categories = new ArrayList<Category>();

        String sql = "SELECT * FROM Category";

        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Category category = new Category();

                category.setCateId(rs.getInt("cate_id"));
                category.setCateName(rs.getString("cate_name"));
                category.setIcons(rs.getString("icons"));

                categories.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public List<Category> search(String keyword) {

        List<Category> categories = new ArrayList<Category>();

        String sql = "SELECT * FROM Category WHERE cate_name LIKE ?";

        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Category category = new Category();

                category.setCateId(rs.getInt("cate_id"));
                category.setCateName(rs.getString("cate_name"));
                category.setIcons(rs.getString("icons"));

                categories.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }
}