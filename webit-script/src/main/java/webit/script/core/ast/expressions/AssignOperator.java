package webit.script.core.ast.expressions;

import webit.script.Context;
import webit.script.core.ast.AbstractExpression;
import webit.script.core.ast.Expression;
import webit.script.core.ast.ResetableValue;
import webit.script.core.ast.ResetableValueExpression;
import webit.script.util.StatmentUtil;

/**
 *
 * @author Zqq
 */
public final class AssignOperator extends AbstractExpression {

    private final Expression rexpr;
    private final ResetableValueExpression lexpr;

    public AssignOperator(ResetableValueExpression lexpr, Expression rexpr, int line, int column) {
        super(line, column);
        this.lexpr = lexpr;
        this.rexpr = rexpr;
    }

    @Override
    public Object execute(Context context, boolean needReturn) {
        Object result = StatmentUtil.execute(rexpr, context, needReturn);
        ResetableValue value = StatmentUtil.getResetableValue(lexpr, context);
        value.set(result);
        return result;
    }
}
