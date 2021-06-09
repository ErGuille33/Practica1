package com.example.pcengine;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class Font extends Frame implements com.example.engine.Font {
    //Crea una fuente de awt y se guarda en f
    public Font(String is, float _size, boolean _isBold) throws Exception {
        java.awt.Font auxFont;

        try
        {
            auxFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File("assets/"+ is));
        }
        catch(Exception e)
        {
            System.err.println("Error cargando la fuente: " + e);
            return;
        }
        if (_isBold)
            f = auxFont.deriveFont(java.awt.Font.BOLD, _size);
        else
            f = auxFont.deriveFont(_size);
    }

    //Getter de la fuente
    public java.awt.Font getFont() { return f; }

    public java.awt.Font f;
}
