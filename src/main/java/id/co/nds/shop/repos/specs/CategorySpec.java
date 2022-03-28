package id.co.nds.shop.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import id.co.nds.shop.globals.GlobalConstant;
import id.co.nds.shop.entities.CategoryEntity;
import id.co.nds.shop.models.CategoryModel;

import org.springframework.data.jpa.domain.Specification;

public class CategorySpec implements Specification<CategoryEntity> {
    private CategoryModel categoryModel;

    public CategorySpec(CategoryModel categoryModel) {
        super();
        this.categoryModel = categoryModel;
    }

    @Override
    public Predicate toPredicate(Root<CategoryEntity> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {

        Predicate p = cb.disjunction();

        if (categoryModel.getId() != null && categoryModel.getId() != "") {
            p.getExpressions().add(cb.like(cb.lower(root.get("id")),
                    "%" + categoryModel.getId().toLowerCase() + "%"));
        }

        if (categoryModel.getName() != null && categoryModel.getName().length() > 3) {
            p.getExpressions().add(cb.like(cb.lower(root.get("name")),
                    "%" + categoryModel.getName().toLowerCase() + "%"));
        }

        if (categoryModel.getRecStatus() != null && (categoryModel.getRecStatus().trim()
                .equalsIgnoreCase(GlobalConstant.REC_STATUS_ACTIVE)
                || categoryModel.getRecStatus().trim()
                        .equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE))) {
            p.getExpressions().add(cb.equal(cb.upper(root.get("recStatus")),
                    categoryModel.getRecStatus().toUpperCase()));
        }

        cq.orderBy(cb.asc(root.get("id")));

        return p;
    }

}
