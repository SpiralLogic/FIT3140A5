package com.srjengbro.scratchbasic;

import com.srjengbro.scratchbasic.instructions.GoSubInstruction;
import com.srjengbro.scratchbasic.instructions.GotoInstruction;
import com.srjengbro.scratchbasic.instructions.IfInstruction;
import com.srjengbro.scratchbasic.instructions.Instruction;
import com.srjengbro.scratchbasic.instructions.LetInstruction;
import com.srjengbro.scratchbasic.instructions.PrintInstruction;
import com.srjengbro.scratchbasic.instructions.RemInstruction;
import com.srjengbro.scratchbasic.instructions.ReturnInstruction;
import com.srjengbro.scratchbasic.instructions.SubInstruction;

/**
 * @author Sol Jennigns & Giles Browne
 * @description Creates an instruction object from  a text string
 */
public class InstructionMaker {


    /**
     * @param instructionText instruction type to create
     * @return the instruction object
     * @throws Exception
     */
    public static Instruction generateInstruction(String instructionText) throws InvalidInstructionException {

        switch (instructionText) {

            case "PRINT":
                return new PrintInstruction();
            case "IF":
                return new IfInstruction();
            case "GOTO":
                return new GotoInstruction();
            case "REM":
                return new RemInstruction();
            case "LET":
                return new LetInstruction();
            case "GOSUB":
                return new GoSubInstruction();
            case "SUB":
                return new SubInstruction();
            case "RETURN":
                return new ReturnInstruction();
            default:
                throw new InvalidInstructionException(instructionText + " is not a valid instruction");

        }

    }

}
