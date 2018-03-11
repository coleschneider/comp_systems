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
public class THexConstant extends AToken {

    private final int hexValue;

    public THexConstant(int i) {
        hexValue = i;
    }

    @Override
    public String getDescription() {
        return String.format("Hexadecimal constant = %d", hexValue);
    }

    public int getIntValue() {
        return hexValue;
    }
}
