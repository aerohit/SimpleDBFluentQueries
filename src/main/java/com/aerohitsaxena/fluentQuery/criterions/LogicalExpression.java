package com.aerohitsaxena.fluentQuery.criterions;

import com.aerohitsaxena.fluentQuery.operators.LogicalOperators;
import com.aerohitsaxena.fluentQuery.utils.CriterionValidator;
import com.aerohitsaxena.fluentQuery.utils.Preconditions;
import com.aerohitsaxena.fluentQuery.utils.StringUtils;

public class LogicalExpression implements Criterion {
	private final Criterion m_c1;
	private final Criterion m_c2;
	private final LogicalOperators m_operator;

	public LogicalExpression(Criterion c1, Criterion c2,
			LogicalOperators operator) {
		CriterionValidator.validate(c1);
		CriterionValidator.validate(c2);
		Preconditions.check(operator != null);
		m_c1 = c1;
		m_c2 = c2;
		m_operator = operator;
	}

	@Override
	public String toSql() {
		return StringUtils.spaceJoin(m_c1.toSql(), m_operator.getOperator(),
				m_c2.toSql());
	}
}
