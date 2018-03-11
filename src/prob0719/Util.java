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
public class Util {

    public static boolean isDigit(char ch) {
        return ('0' <= ch) && (ch <= '9');
    }

    public static boolean isAlpha(char ch) {
        return (('a' <= ch) && (ch <= 'z') || ('A' <= ch) && (ch <= 'Z'));
    }

    public static boolean isHexChar(char ch) {
        return (('a' <= ch) && (ch <= 'f') || ('A' <= ch) && (ch <= 'F'));
    }
}
