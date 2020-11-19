package com.example.logica;

public class CargaNivel {
    CargaNivel(JsonHandler json){
        _json = json;
    }
    public boolean cargaNivel(int numNivel){
        String nombreNivel = "";
        switch (numNivel){
            case 0:
                nombreNivel = "THE BOX";
                break;
            case 1:
                nombreNivel = "PEGBOARD";
                break;
            case 2:
                nombreNivel = "ORBIT";
                break;
            case 3:
                nombreNivel = "NEEDLE";
                break;
            case 4:
                nombreNivel = "PATIENCE";
                break;
            case 5:
                nombreNivel = "BOOMERANG";
            case 6:
                nombreNivel = "SPLITTER";
                break;
            case 7:
                nombreNivel = "TRIPLE SHOT";
                break;
            case 8:
                nombreNivel = "DONUT";
                break;
            case 9:
                nombreNivel = "LONG DISTANCE";
                break;
            case 10:
                nombreNivel = "BAR GAPS";
                break;
            case 11:
                nombreNivel = "RAZOR";
                break;
            case 12:
                nombreNivel = "SHELL";
                break;
            case 13:
                nombreNivel = "REVENGE OF THE BOX";
                break;
            case 14:
                nombreNivel = "QUADS";
                break;
            case 15:
                nombreNivel = "ZIG ZAG";
                break;
            case 16:
                nombreNivel = "X FACTOR";
                break;
            case 17:
                nombreNivel = "CIRCUIT";
                break;
            case 18:
                nombreNivel = "SNAKE";
                break;
            case 19:
                nombreNivel = "ALIENS";
                break;


        }
        return true;
    }
    int numNivel;
    JsonHandler _json;
}
