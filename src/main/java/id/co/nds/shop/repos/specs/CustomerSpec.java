package id.co.nds.shop.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import id.co.nds.shop.globals.GlobalConstant;
import id.co.nds.shop.entities.CustomerEntity;
import id.co.nds.shop.models.CustomerModel;

import org.springframework.data.jpa.domain.Specification;

public class CustomerSpec implements Specification<CustomerEntity> {

    private CustomerModel customerModel;

    public CustomerSpec(CustomerModel customerModel) {
        super();
        this.customerModel = customerModel;
    }

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> cq,
            CriteriaBuilder cb) {

        Predicate p = cb.disjunction();
        String id = customerModel.getId();
        String name = customerModel.getName();
        String callNumber = customerModel.getCallNumber();
        String recStatus = customerModel.getRecStatus();

        if (id != null && id.length() > 0) {
            p.getExpressions().add(cb.equal(root.get("id"), id));
        }

        if (name != null && name.length() > 0) {
            p.getExpressions()
                    .add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }

        if (callNumber != null && callNumber.length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("callNumber")),
                    "%" + callNumber.toLowerCase() + "%"));
        }

        if (recStatus != null && (recStatus.trim()
                .equalsIgnoreCase(GlobalConstant.REC_STATUS_ACTIVE)
                || recStatus.trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE))) {
            p.getExpressions()
                    .add(cb.equal(cb.upper(root.get("recStatus")), recStatus.toUpperCase()));
        }

        cq.orderBy(cb.desc(root.get("id")));

        return p;
    }

}
