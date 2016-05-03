package com.srjengbro.scratchbasic.instructions;


import android.util.Log;
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
 * Created by Maidenii on 20/04/16.
 */
public class IfInstruction extends Instruction {

    protected GotoInstruction gotoInstruction;
    protected transient EditText gotoText;
    protected Expression expression;
    protected transient EditText lhsText;
    protected transient Spinner opSpinner;
    protected transient EditText rhsText;
    private Boolean evaluatedAs = false;

    public IfInstruction() {
        name = "IF";
        gotoInstruction = new GotoInstruction();
    }

    public String getName() {
        return this.name;
    }

    public void update() {
        String type = opSpinner.getSelectedItem().toString();
        String lhs = lhsText.getText().toString();
        String rhs = rhsText.getText().toString();
        expression = ExpressionMaker.generateExpression(type, lhs, rhs);
        gotoInstruction.parse(gotoText.getText().toString());
        updateInstructionText();

    }

    @Override
    public void parse(String line) {
        try {
            expression = ExpressionMaker.generateExpression(line);
            updateInstructionText();
        } catch (ExpressionParseException e) {
            System.out.print(e.getMessage());
        }
    }

    public void updateInstructionText() {
        StringBuilder s = new StringBuilder();
        s.append(expression.toString());
        s.append(" ");
        s.append(gotoInstruction.getName());
        s.append(" ");
        s.append(gotoInstruction.getInstruction());
        instruction = s.toString();
    }

    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_if, null);
        lhsText = (EditText) layout.findViewById(R.id.lhs_text);
        rhsText = (EditText) layout.findViewById(R.id.rhs_text);
        opSpinner = (Spinner) layout.findViewById(R.id.operator_spinner);
        if (expression != null) {
            lhsText.setText(expression.getLhs());
            rhsText.setText(expression.getRhs());
            Integer oppos = ((ArrayAdapter<String>) opSpinner.getAdapter()).getPosition(expression.getOperator().getSymbol());
            opSpinner.setSelection(oppos);
        }
        gotoText = (EditText) layout.findViewById(R.id.goto_line);
        gotoText.setText(gotoInstruction.getInstruction());
        return layout;
    }

    public GotoInstruction getGotoInstruction() {
        return gotoInstruction;
    }


    @Override
    public String run(VariableStore variableStore) throws InstructionRunException {
        Integer result;
        if (expression == null) {
            throw new InstructionRunException("Instruction missing expression");
        }
        try {
            result = expression.evaluate(variableStore);

        } catch (VariableDoesNotExistException e) {
            throw new InstructionRunException(e.getMessage());
        }
        if (result > 0) {
            gotoInstruction.run(variableStore);
            evaluatedAs = true;
        } else {
            evaluatedAs = false;
        }
        return "";
    }

    public Integer getNextLine() {
        if (evaluatedAs) {
            return gotoInstruction.getNextLine();
        }
        return null;
    }
}
