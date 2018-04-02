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
public class TAddress extends AToken {

    private final String address;

    public TAddress(StringBuffer stringBuffer) {
        address = new String(stringBuffer);
    }

    
    public String getDescription() {
        return String.format("Addressing Mode = %s", address);
    }

    public String getStringValue() {
        return address;
    }
}
