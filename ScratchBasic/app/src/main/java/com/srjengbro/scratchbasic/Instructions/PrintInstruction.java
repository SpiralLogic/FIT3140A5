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
 * Created by Maidenii on 16/04/16.
 */
public class PrintInstruction extends Instruction {

    protected EditText printText;
    protected Expression expression;

    public PrintInstruction() {
        name = "PRINT";
    }

    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_print, null);
        printText = (EditText) layout.findViewById(R.id.print_text);
        printText.setText(instruction);
        return layout;
    }

    public void update() {
        parse(printText.getText().toString());
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
    public String run(Integer line_no, VariableStore variableStore) throws InstructionRunException {
        Integer result;
        try {
            result = expression.evaluate(variableStore);
        } catch (VariableDoesNotExistException e) {
            throw new InstructionRunException("Error on line " + line_no.toString() + " " + e.getMessage());
        }
        return result.toString();
    }


}