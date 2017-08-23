package com.shang.spray.utils.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * info:
 * Created by shang on 2017/5/3.
 */
public class LogicalExpression implements Criterion {
    /**
     * @Fields : 逻辑表达式中包含的表达式
     */
    private Criterion[] criterion;
    /**
     * @Fields : 计算符
     */
    private Operator operator;

    public LogicalExpression(Criterion[] criterions, Operator operator) {
        this.criterion = criterions;
        this.operator = operator;
    }

    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (int i = 0; i < this.criterion.length; i++) {
            predicates.add(this.criterion[i].toPredicate(root, query, builder));
        }
        switch (operator) {
            case AND:
                return builder.and(predicates.toArray(new Predicate[predicates
                        .size()]));
            case OR:
                return builder.or(predicates.toArray(new Predicate[predicates
                        .size()]));
            default:
                return null;
        }
    }

}
