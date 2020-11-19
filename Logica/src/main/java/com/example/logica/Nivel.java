package com.example.logica;

public class Nivel {

    Nivel(){

    }

    public String name;

    public Paths[] paths = new Paths[25];
    public Items[] items = new Items[50];
    public Enemies[] enemies = new Enemies[50];

    public class Paths{
        public Vertice[] vertices = new Vertice[125];
        public Direciones[] directions = new Direciones[125];
    }

    public class Items{
        public Coordenada pos;
        public int radius;
        public int speed;
        public int angle;
    }
    public class Enemies{
        public Coordenada pos;
        public int lenght;
        public int angle;

        public int speed;
        public int offset;
        public int time1;
        public int time2;

    }

    public class Direciones{
        public Coordenada pos;
    }

    public class Vertice{
        public Coordenada pos;
    }

}
