package com.example.pcengine;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Font extends Frame implements com.example.engine.Font {

    public Font(String filename,float _size, boolean _isBold) throws Exception{
        try(InputStream is = new FileInputStream("assets/Fuentes/" + filename)){
            f =  java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, is);
            } catch (Exception e) {
                System.err.println("Error al cargar la fuente" + e);
        }
        if(_isBold){
            f = f.deriveFont(f.BOLD,_size);
        }
        else {
            f = f.deriveFont(_size);
        }
    }
    public java.awt.Font f;
}
