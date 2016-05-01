package com.srjengbro.scratchbasic.instructions;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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

    protected EditText ifText;
    protected GotoInstruction gotoInstruction;
    protected EditText gotoText;
    protected Expression expression;
    private Boolean evaluatedAs = false;
    public IfInstruction() {
        name = "IF";
        gotoInstruction = new GotoInstruction();
    }

    public String getName() {
        return this.name;
    }

    public void update() {
        gotoInstruction.parse(gotoText.getText().toString());
        parse(ifText.getText().toString());
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
        ifText = (EditText) layout.findViewById(R.id.if_text);
        if (expression != null) {
            ifText.setText(expression.toString());
        }
        gotoText = (EditText) layout.findViewById(R.id.goto_line);
        gotoText.setText(gotoInstruction.getInstruction());
        return layout;
    }

    public GotoInstruction getGotoInstruction() {
        return gotoInstruction;
    }


    @Override
    public String run(Integer lineno, VariableStore variableStore) throws InstructionRunException {
        Integer result;
        try {
            result = expression.evaluate(variableStore);

        } catch (VariableDoesNotExistException e) {
            throw new InstructionRunException("Error on line " + lineno.toString() + " " + e.getMessage());
        }
        if (result > 0) {
            gotoInstruction.run(lineno,variableStore );
            evaluatedAs = true;
        }else {
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
