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
public class TInteger extends AToken {

    private final int intValue;

    public TInteger(int i) {
        intValue = i;
    }

    @Override
    public String getDescription() {
        return String.format("Integer = %d", intValue);
    }

    public int getIntValue() {
        return intValue;
    }
}
