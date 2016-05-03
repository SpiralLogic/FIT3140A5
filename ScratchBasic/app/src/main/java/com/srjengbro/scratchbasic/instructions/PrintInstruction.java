package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.srjengbro.scratchbasic.Expression;
import com.srjengbro.scratchbasic.ExpressionMaker;
import com.srjengbro.scratchbasic.ExpressionParseException;
import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.VariableDoesNotExistException;
import com.srjengbro.scratchbasic.VariableStore;

/**
 * Created by Maidenii on 16/04/16.
 */
public class PrintInstruction extends Instruction {

    protected Expression expression;
    protected transient EditText lhsText;
    protected transient Spinner opSpinner;
    protected transient EditText rhsText;


    public PrintInstruction() {
        name = "PRINT";
    }

    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_print, null);
        lhsText = (EditText) layout.findViewById(R.id.lhs_text);
        rhsText = (EditText) layout.findViewById(R.id.rhs_text);
        opSpinner = (Spinner) layout.findViewById(R.id.operator_spinner);
        if (expression != null) {
            lhsText.setText(expression.getLhs());
            rhsText.setText(expression.getRhs());
            Integer oppos = ((ArrayAdapter<String>) opSpinner.getAdapter()).getPosition(expression.getOperator().getSymbol());
            opSpinner.setSelection(oppos);
        }
        return layout;
    }

    public void update() {
        String type = opSpinner.getSelectedItem().toString();
        String lhs = lhsText.getText().toString();
        String rhs = rhsText.getText().toString();
        expression = ExpressionMaker.generateExpression(type, lhs, rhs);
        instruction = expression.toString();
    }

    public void parse(String line) {
        try {
            expression = ExpressionMaker.generateExpression(line);
            instruction = expression.toString();
        } catch (ExpressionParseException e) {
            System.out.print(e.getMessage());
        }
    }

    @Override
    public String run(VariableStore variableStore) throws InstructionRunException {
        if (expression==null) {
            throw new InstructionRunException("Instruction missing expression");
        }
        Integer result;
        try {
            result = expression.evaluate(variableStore);
        } catch (VariableDoesNotExistException e) {
            throw new InstructionRunException(e.getMessage());
        }
        return result.toString();
    }


}