package com.example.logica;

import com.example.engine.Engine;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.io.FileReader;

import java.io.InputStream;

public class JsonHandler {
    JsonHandler(Engine engine){
        _engine =engine;
    };
    //Esta clase parsea el archivo JSON indicado
    public void JsonParseFile() throws Exception {
            //Lo utilizamos para sacar la ruta del proyecto
            try(FileReader fileReader = new FileReader("assets/levels.json")){
                JsonObject deserialize = (JsonObject) Jsoner.deserialize(fileReader);
                Mapper mapper = new DozerBeanMapper();

                Nivel nivel1 = mapper.map(deserialize, Nivel.class);

                System.out.println(nivel1.name);
            }
            catch (Exception e){
                System.out.println("No se pudo leer Json" + e);
            }
    }
    JsonObject obj;
    Engine _engine;



}
