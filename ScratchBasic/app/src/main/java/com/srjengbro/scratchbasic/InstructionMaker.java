package com.srjengbro.scratchbasic;

/**
 * Created by Maidenii on 29/04/16.
 */
public class InstructionMaker {


    public static Instruction generateInstruction(String instructionText) throws Exception {

        switch (instructionText) {

            case "PRINT":
                return new PrintInstruction();
            case "IF":
                return new IfInstruction();
            case "GOTO":
                return new GotoInstruction();
            case "REM" :
                return new RemInstruction();
            case "LET" :
                return new LetInstruction();
            default:
                throw new Exception("Not a valid instruction");

        }

    }

}
