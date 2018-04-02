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
public class TIdentifier extends AToken {

    private final String stringValue;

    public TIdentifier(StringBuffer stringBuffer) {
        stringValue = new String(stringBuffer);
    }

    
    public String getDescription() {
        return String.format("Identifier = %s\n", stringValue);
    }

    public String getStringValue() {
        return stringValue;
    }
}
