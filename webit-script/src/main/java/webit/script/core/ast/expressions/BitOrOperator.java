package webit.script.core.ast.expressions;

import webit.script.Context;
import webit.script.core.ast.BinaryOperator;
import webit.script.core.ast.Expression;
import webit.script.core.ast.Optimizable;
import webit.script.exceptions.ParserException;
import webit.script.util.ALU;
import webit.script.util.StatmentUtil;

/**
 *
 * @author Zqq
 */
public final class BitOrOperator extends BinaryOperator implements Optimizable {

    public BitOrOperator(Expression leftExpr, Expression rightExpr, int line, int column) {
        super(leftExpr, rightExpr, line, column);
    }

    @Override
    public Object execute(Context context, boolean needReturn) {
        return ALU.bitOr(StatmentUtil.execute(leftExpr, context), StatmentUtil.execute(rightExpr, context));
    }

    @Override
    public Expression optimize() {
        if (leftExpr instanceof DirectValue && rightExpr instanceof DirectValue) {
            return new DirectValue(ALU.bitOr(((DirectValue) leftExpr).value, ((DirectValue) rightExpr).value), line, column);
        }
        return this;
    }
}
