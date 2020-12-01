package com.example.logica;

import com.example.engine.Engine;

import java.util.ArrayList;
import java.util.Arrays;

public class Nivel {

    Nivel(int nLevel, Engine _engine){
        _nLevel = nLevel;
        engine = _engine;
    }

    public void cargaNivel() throws Exception{

        jsonHandler = new JsonHandler();
        jsonHandler.JsonParseLevel(_nLevel,this);

        System.out.println(" Nivel " + _nLevel);
        System.out.println( " Paths: ");
        for(int i = 0; i < paths.size(); i++) {
            for(int j = 0; j < paths.get(i).vertices.size(); j++) {
                System.out.print(" x: " + paths.get(i).vertices.get(j)._pos.get_x() + " y: " + paths.get(i).vertices.get(j)._pos.get_y());
            }

            for(int j = 0; j < paths.get(i).directions.size(); j++) {
                System.out.print(" x: " + paths.get(i).directions.get(j)._pos.get_x() + " y: " + paths.get(i).directions.get(j)._pos.get_y());
            }
        }
        System.out.println( " Items: ");
        for(int i = 0; i < items.size(); i++) {

                System.out.print(" x: " + items.get(i)._pos.get_x() + " y: " + items.get(i)._pos.get_y() + " radius " + items.get(i)._radius + " speed " + items.get(i)._speed + " angle " + items.get(i)._angle);
        }
        System.out.println( " Enemigos: " );
        for(int i = 0; i < enemies.size(); i++) {

            System.out.print(" x: " + enemies.get(i)._pos.get_x() + " y: " + enemies.get(i)._pos.get_y() + " length " +  enemies.get(i)._length + " angle " + enemies.get(i)._angle+ " speed " + enemies.get(i)._speed
                    + "time1 " + enemies.get(i)._time1 + "time2" + enemies.get(i)._time2 + "offetx:" + enemies.get(i)._offset.get_x() + "offsety: " + enemies.get(i)._offset.get_y() );
        }
        System.out.println( " Tiempo: " + _time);

    }

    //Atributos
    public String _name;

    public ArrayList<Paths> paths = new ArrayList<Paths>();
    public ArrayList<Items> items = new ArrayList<Items>();
    public ArrayList<Enemies> enemies =  new ArrayList<Enemies>();

    public int _nLevel;

    public int _time;

    Engine engine;

    JsonHandler jsonHandler;

    //Métodos para poner los valores
    public void setName(String name){
        _name = name;
    }

    public void pushPathBack(){
        paths.add(new Paths());
    }

    public void pushTickBack(int time){
        _time = time;
    }

    public void pushItemsBack(float x, float y, float angle, float speed, float radius){
        items.add(new Items(x,y,angle,speed,radius));
    }

    public void pushEnemiesBack(float x, float y, float length, float angle, float speed, float offsetX, float offsetY, float time1, float time2){
        enemies.add(new Enemies(x,y,length,angle,speed, offsetX, offsetY,time1,time2));
    }

    public void pushVerticesBack(float x, float y){
        paths.get(paths.size() - 1).vertices.add(new Vertice(x,y));
    }

    public void pushDireccionesBack(float x, float y){
        paths.get(paths.size() - 1).directions.add(new Direciones(x,y));
    }


    //Clases usadas para los distintos atributos del nivel
    public class Paths{
        public ArrayList<Vertice> vertices = new ArrayList<Vertice>();
        public ArrayList<Direciones> directions = new ArrayList<Direciones>();
    }

    public class Items{
        Items(float x, float y, float angle, float speed, float radius){
            _pos = new Coordenada(x,y);
            _radius = radius;
            _angle = angle;
            _speed = speed;
        }
        public Coordenada _pos;
        public float _radius;
        public float _speed;
        public float _angle;
    }
    public class Enemies{
        Enemies(float x, float y, float length, float angle, float speed, float offsetX, float offsetY, float time1, float time2){
            _pos = new Coordenada(x,y);
            _length = length;
            _angle = angle;
            _speed = speed;
            _offset = new Coordenada(offsetX,offsetY);
            _time1 = time1;
            _time2 = time2;
        }
        public Coordenada _pos;
        public float _length;
        public float _angle;

        public float _speed;
        public Coordenada _offset;
        public float _time1;
        public float _time2;

    }

    public class Direciones{
        Direciones(float x, float y){ _pos = new Coordenada(x,y); }
        public Coordenada _pos;
    }

    public class Vertice{
        Vertice(float x, float y){ _pos = new Coordenada(x,y); }
        public Coordenada _pos;
    }

}
