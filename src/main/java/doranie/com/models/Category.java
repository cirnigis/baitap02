package doranie.com.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id")
    private int cateId;

    @Column(name = "cate_name")
    private String cateName;

    @Column(name = "icons")
    private String icons;

    public Category() {

    }

    public Category(int cateId, String cateName, String icons) {

        this.cateId = cateId;

        this.cateName = cateName;

        this.icons = icons;

    }

    public int getCateId() {

        return cateId;

    }

    public void setCateId(int cateId) {

        this.cateId = cateId;

    }

    public String getCateName() {

        return cateName;

    }

    public void setCateName(String cateName) {

        this.cateName = cateName;

    }

    public String getIcons() {

        return icons;

    }

    public void setIcons(String icons) {

        this.icons = icons;

    }

}