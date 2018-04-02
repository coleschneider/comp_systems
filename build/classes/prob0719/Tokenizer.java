/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prob0719;

/**
 *
 * @author coleschneider
 */
public class Tokenizer {

    private final InBuffer b;

    public Tokenizer(InBuffer inBuffer) {
        b = inBuffer;
    }

    public AToken getToken() {
        char nextChar;
        StringBuffer localStringValue = new StringBuffer("");
        int localIntValue = 0;
        int localHexValue = 0;
        int sign = +1;
        AToken aToken = new TEmpty();
        LexState state = LexState.LS_START;
        do {
            nextChar = b.advanceInput();
            switch (state) {
                //intitial state: check if string,
                    //check if empty, check sign and assign value,
                    //check if 0 if it is 
                case LS_START:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                        state = LexState.LS_IDENT;
                    } else if (nextChar == ' ') {
                        state = LexState.LS_START;
                    } else if (nextChar == '-') {
                        sign = -1;
                        state = LexState.LS_SIGN;
                    } else if (nextChar == '+') {
                        sign = +1;
                        state = LexState.LS_SIGN;
                    } else if (nextChar == '0') {
                        localIntValue = nextChar - '0';
                        state = LexState.LS_INT1;
                    } else if (Util.isDigit(nextChar)) {
                        localIntValue = nextChar - '0';
                        state = LexState.LS_INT2;
                    } else if (nextChar == '.') {
                        state = LexState.LS_DOT1;
                    } else if (nextChar == ',') {
                        state = LexState.LS_ADDR1;
                    } else if (nextChar == '\n') {
                        //use line break from buffered char to determine when to stop
                        aToken = new TEmpty();
                        state = LexState.LS_STOP;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_INT1:
                    if (Util.isDigit(nextChar)) {
                        localIntValue = 10 * localIntValue + nextChar - '0';
                        state = LexState.LS_INT2;
                    } else if (nextChar == 'x' || nextChar == 'X') {
                        state = LexState.LS_HEX1;
                    } else {
                        b.backUpInput();
                        aToken = new TInteger(sign * localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_INT2:
                    if (Util.isDigit(nextChar)) {
                        localIntValue = 10 * localIntValue + nextChar - '0';
                        state = LexState.LS_INT2;
                    } else {
                        b.backUpInput();
                        aToken = new TInteger(sign * localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_SIGN:
                    if (Util.isDigit(nextChar)) {
                        localIntValue = 10 * localIntValue + nextChar - '0';
                        state = LexState.LS_INT2;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_IDENT:
                    if (Util.isDigit(nextChar) || Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                        state = LexState.LS_IDENT;
                    } else {
                        b.backUpInput();
                        aToken = new TIdentifier(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_DOT1:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                        state = LexState.LS_DOT2;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_ADDR1:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                        state = LexState.LS_ADDR2;
                    } else if (nextChar == ' ') {
                        state = LexState.LS_ADDR1;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_HEX1:
                    if (Util.isDigit(nextChar)) {
                        localHexValue = nextChar - '0';
                        state = LexState.LS_HEX2;
                    } else if (Util.isHexChar(nextChar)) {
                        localHexValue = (nextChar % 0x10) + 9;
                        state = LexState.LS_HEX2;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_HEX2:
                    if (Util.isDigit(nextChar)) {
                        localHexValue = 16 * localHexValue + nextChar - '0';
                        state = LexState.LS_HEX2;
                    } else if (Util.isHexChar(nextChar)) {
                        localHexValue = 16 * localHexValue + ((nextChar % 0x10) + 9);
                        state = LexState.LS_HEX2;
                        if (localHexValue > 65535 || (localHexValue > 32768 && sign == -1)) {
                            aToken = new TInvalid();
                            state = LexState.LS_STOP;
                        }
                    } else {
                        b.backUpInput();
                        aToken = new THexConstant(localHexValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_DOT2:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                        state = LexState.LS_DOT2;
                    } else {
                        b.backUpInput();
                        aToken = new TDotCommand(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_ADDR2:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                        state = LexState.LS_ADDR2;
                    } else {
                        b.backUpInput();
                        aToken = new TAddress(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                default:
                    // Do nothing
                    break;
            }
        } while ((state != LexState.LS_STOP) && !(aToken instanceof TInvalid));
        return aToken;
    }
}
