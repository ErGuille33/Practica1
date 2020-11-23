package com.example.logica;

import com.example.engine.Engine;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;


public class JsonHandler {
    JsonHandler() {
    }

    //Esta clase parsea el archivo JSON indicado
    public void JsonParseLevel(int nLevel, Nivel nivelActual) throws Exception {
        //Lo utilizamos para sacar la ruta del proyecto

        try  {
            InputStream is = nivelActual.engine.openInputStream("levels.json");

            JSONParser jsonParser = new JSONParser();

            Object o = jsonParser.parse(new InputStreamReader(is));

            JSONArray deserialize = (JSONArray) JSONValue.parse(o.toString());
            JSONObject nivel = (JSONObject) deserialize.get(nLevel);
            JSONArray Paths = (JSONArray) nivel.get("paths");

            //For de PAth
            for (int i = 0; i < Paths.size(); i++) {
                //Push PAth
                nivelActual.pushPathBack();
                JSONObject Path0 = (JSONObject) Paths.get(i);
                JSONArray Vertices = (JSONArray) Path0.get("vertices");
                //For de vértices
                for (int j = 0; j < Vertices.size(); j++) {
                    String aux1;
                    String aux2;
                    JSONObject coord = (JSONObject) Vertices.get(j);
                    aux1 = String.valueOf(coord.get("x")) ;
                    aux2 = (String) String.valueOf(coord.get("y"));
                    nivelActual.pushVerticesBack(( Float.parseFloat(aux1)), Float.parseFloat(aux2));
                }
                //For direcciones
                JSONArray Dir = (JSONArray) Path0.get("directions");
                if(Dir != null) {
                    for (int l = 0; l < Dir.size(); l++) {
                        String aux1;
                        String aux2;
                        JSONObject coord = (JSONObject) Dir.get(l);
                        aux1 = String.valueOf(coord.get("x"));
                        aux2 = String.valueOf(coord.get("y"));
                        nivelActual.pushDireccionesBack(( Float.parseFloat(aux1)), Float.parseFloat(aux2));
                    }
                }
            }
            //For Items

            JSONArray Items = (JSONArray) nivel.get("items");
            if(Items != null) {
                for (int t = 0; t < Items.size(); t++) {
                    JSONObject unit = (JSONObject) Items.get(t);

                    float radion = -1;
                    float anglen = -1;
                    float speedn = -1;

                    if( unit.get("radio") != null) {
                        radion = Float.parseFloat(String.valueOf(unit.get("radio")));
                    }

                    if(unit.get("angle") != null) {
                        anglen = Float.parseFloat(String.valueOf(unit.get("angle")));
                    }
                    if(unit.get("speed") != null) {
                        speedn = Float.parseFloat(String.valueOf(unit.get("speed")));
                    }

                    String aux1;
                    String aux2;
                    aux1 = String.valueOf(unit.get("x"));
                    aux2 = String.valueOf(unit.get("y"));

                    nivelActual.pushItemsBack((Float.parseFloat(aux1)), (Float.parseFloat(aux2)), anglen,
                            speedn, radion);

                }

            }
            JSONArray Enemies = (JSONArray) nivel.get("enemies");
            //For enemigos
            if(Enemies != null) {
                for (int t = 0; t < Enemies.size(); t++) {
                    JSONObject unit = (JSONObject) Enemies.get(t);

                    JSONObject offset = (JSONObject) unit.get("offset");

                    float lengthn = -1;
                    float anglen = -1;
                    float speedn = -1;
                    float time1n = -1;
                    float time2n = -1;
                    float aux1 = -1;
                    float aux2 = -1;

                    if(unit.get("length") != null) {
                        lengthn = Float.parseFloat(String.valueOf(unit.get("length")));
                    }
                    if(unit.get("angle") != null) {
                        anglen = Float.parseFloat(String.valueOf(unit.get("angle")));
                    }
                    if(unit.get("speed") != null) {
                        speedn = Float.parseFloat(String.valueOf(unit.get("speed")));
                    }
                    if(unit.get("time1") != null) {
                        time1n = Float.parseFloat(String.valueOf(unit.get("time1")));
                    }
                    if(unit.get("time2") != null) {
                        time2n = Float.parseFloat(String.valueOf(unit.get("time2")));
                    }
                    if(offset != null) {

                        aux1 = Float.parseFloat(String.valueOf(offset.get("x")));
                        aux2 = Float.parseFloat(String.valueOf(offset.get("y")));
                    }
                    nivelActual.pushEnemiesBack((Float.parseFloat(String.valueOf(unit.get("x")))), ((Float.parseFloat(String.valueOf(unit.get("y"))))), lengthn, anglen, speedn,
                            aux1, aux2, time1n, time2n);
                }
            }

            if(nivel.get("time") != null) {
                nivelActual.pushTickBack(Integer.parseInt((String) nivel.get("time")));
            }

        }
        catch(Exception e)
        {
            System.out.println("No se pudo leer Json" + e);
        }
    }

}

