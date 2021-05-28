package br.com.lorrane.expression;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author renanravelli
 */
public class BooleanExpressionBuilder {

    private BooleanExpression predicate;

    public BooleanExpressionBuilder(BooleanExpression predicate) {
        this.predicate = predicate;
    }

    public <T> BooleanExpressionBuilder notNullAnd(Function<T, BooleanExpression> expressionFunction, T value) {
        if (Objects.nonNull(value)) {
            return new BooleanExpressionBuilder(predicate.and(expressionFunction.apply(value)));
        }
        return this;
    }

    public <T> BooleanExpressionBuilder notNullOr(Function<T, BooleanExpression> expressionFunction, T value) {
        if (Objects.nonNull(value)) {
            return new BooleanExpressionBuilder(predicate.or(expressionFunction.apply(value)));
        }
        return this;
    }

    public BooleanExpressionBuilder notEmptyAnd(Function<String, BooleanExpression> expressionFunction, String value) {
        if (!StringUtils.isEmpty(value)) {
            return new BooleanExpressionBuilder(predicate.and(expressionFunction.apply(value)));
        }
        return this;
    }

    public <T> BooleanExpressionBuilder notEmptyAnd(Function<Collection<T>, BooleanExpression> expressionFunction, Collection<T> collection) {
        if (!CollectionUtils.isEmpty(collection)) {
            return new BooleanExpressionBuilder(predicate.and(expressionFunction.apply(collection)));
        }
        return this;
    }

    public BooleanExpression build() {
        return predicate;
    }
}
