package de.t0biii.ts.methods;

public class Tab
{
    /********************************************************
    * Fix string spaces to align text in minecraft chat
    *
    * @author David Toledo ([EMAIL]david.oracle@gmail.com[/EMAIL])
    * @param s to be resized
    * @param size to align
    * @return New aligned String
    */
    public static String fixFontSize (String s, int size) {
     
        String ret = s.toUpperCase();
     
        if ( s != null ) {
     
            for (int i=0; i < s.length(); i++) {
                if ( s.charAt(i) == 'I' || s.charAt(i) == ' ') {
                    ret += " ";
                }
            }
     
            int faltaEspacos = size - s.length();
            faltaEspacos = (faltaEspacos * 2);
     
            for (int i=0; i < faltaEspacos; i++) {
                ret += " ";
            }
        }
     
        return (ret);
    }
    /********************************************************
    * Fix string spaces to align text in minecraft chat
    *
    * @author David Toledo ([EMAIL]david.oracle@gmail.com[/EMAIL])
    * @param s to be resized
    * @param tabSize to align
    * @return New aligned String
    */
    public static String fFS (String s, int tabSize) {
     return fixFontSize(s, tabSize);
    }
}
