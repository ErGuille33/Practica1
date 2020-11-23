package com.example.logica;

import com.example.engine.Engine;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;


import java.io.FileReader;

import java.math.BigDecimal;

public class JsonHandler {
    JsonHandler() {
    }

    //Esta clase parsea el archivo JSON indicado
    public void JsonParseLevel(int nLevel, Nivel nivelActual) throws Exception {
<<<<<<< Updated upstream
        //Lo utilizamos para sacar la ruta del proyecto

        try (FileReader fileReader = new FileReader("assets/levels.json")) {

            JsonArray deserialize = (JsonArray) Jsoner.deserialize(fileReader);
            JsonObject nivel = (JsonObject) deserialize.get(nLevel);
            JsonArray Paths = (JsonArray) nivel.get("paths");
=======



        try  {

            InputStream is = nivelActual.engine.openInputStream("levels.json");

            JSONParser jsonParser = new JSONParser();

            Object o = jsonParser.parse(new InputStreamReader(is));

            JSONArray deserialize = (JSONArray) JSONValue.parse(o.toString());
            JSONObject nivel = (JSONObject) deserialize.get(nLevel);
            JSONArray Paths = (JSONArray) nivel.get("paths");
>>>>>>> Stashed changes

            //For de PAth
            for (int i = 0; i < Paths.size(); i++) {
                //Push PAth
                nivelActual.pushPathBack();
                JsonObject Path0 = (JsonObject) Paths.get(i);
                JsonArray Vertices = (JsonArray) Path0.get("vertices");
                //For de vértices
                for (int j = 0; j < Vertices.size(); j++) {
                    JsonObject coord = (JsonObject) Vertices.get(j);
                    nivelActual.pushVerticesBack(((BigDecimal) coord.get("x")).floatValue(), ((BigDecimal) coord.get("y")).floatValue());
                }
                //For direcciones
                JsonArray Dir = (JsonArray) Path0.get("directions");
                if(Dir != null) {
                    for (int l = 0; l < Dir.size(); l++) {
                        JsonObject coord = (JsonObject) Dir.get(l);
                        nivelActual.pushDireccionesBack(((BigDecimal) coord.get("x")).floatValue(), ((BigDecimal) coord.get("y")).floatValue());
                    }
                }
            }
            //For Items

            JsonArray Items = (JsonArray) nivel.get("items");
            if(Items != null) {
                for (int t = 0; t < Items.size(); t++) {
                    JsonObject unit = (JsonObject) Items.get(t);
                    BigDecimal radio = (BigDecimal) unit.get("radius");
                    BigDecimal angle = (BigDecimal) unit.get("angle");
                    BigDecimal speed = (BigDecimal) unit.get("speed");
                    int radion = -1;
                    int anglen = -1;
                    int speedn = -1;

                    if( radio != null) {
                        radion = ((BigDecimal) unit.get("radius")).intValue();
                    }

                    if(angle != null) {
                        anglen = ((BigDecimal) unit.get("angle")).intValue();
                    }
                    if(speed != null) {
                        speedn = ((BigDecimal) unit.get("speed")).intValue();
                    }

                    nivelActual.pushItemsBack(((BigDecimal)unit.get("x")).floatValue(), ((BigDecimal)unit.get("y")).floatValue(), anglen,
                            speedn, radion);

                }

            }
            JsonArray Enemies = (JsonArray) nivel.get("enemies");
            //For enemigos
            if(Enemies != null) {
                for (int t = 0; t < Enemies.size(); t++) {
                    JsonObject unit = (JsonObject) Enemies.get(t);
                    BigDecimal length = (BigDecimal) unit.get("length");
                    BigDecimal angle = (BigDecimal) unit.get("angle");
                    BigDecimal speed = (BigDecimal) unit.get("speed");
                    BigDecimal time1 = (BigDecimal) unit.get("time1");
                    BigDecimal time2 = (BigDecimal) unit.get("time2");
                    JsonObject offset = (JsonObject) unit.get("offset");

                    int lengthn = -1;
                    int anglen = -1;
                    int speedn = -1;
                    float time1n = -1;
                    float time2n = -1;
                    float offsetnX = -1;
                    float offsetnY = -1;

                    if(length != null) {
                        lengthn = ((BigDecimal) unit.get("length")).intValue();
                    }
                    if(angle != null) {
                        anglen = ((BigDecimal) unit.get("angle")).intValue();
                    }
                    if(speed != null) {
                        speedn = ((BigDecimal) unit.get("speed")).intValue();
                    }
                    if(time1 != null) {
                        time1n = ((BigDecimal) unit.get("time1")).floatValue();
                    }
                    if(time2 != null) {
                        time2n = ((BigDecimal) unit.get("time2")).floatValue();
                    }
                    if(offset != null) {
                        offsetnY = ((BigDecimal) offset.get("y")).floatValue();
                        offsetnX = ((BigDecimal) offset.get("x")).floatValue();
                    }
                    nivelActual.pushEnemiesBack(((BigDecimal) unit.get("x")).floatValue(), ((BigDecimal) unit.get("y")).floatValue(), lengthn, anglen, speedn,
                            offsetnX, offsetnY, time1n, time2n);
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

