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
public class TDotCommand extends AToken {

    private final String dotCommand;

    public TDotCommand(StringBuffer stringBuffer) {
        dotCommand = new String(stringBuffer);
    }

    
    public String getDescription() {
        return String.format("Dot Command = %s", dotCommand);
    }

    public String getStringValue() {
        return dotCommand;
    }
}
