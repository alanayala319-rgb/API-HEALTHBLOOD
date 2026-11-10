/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.controller;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author melan
 */
public class ConnectionMysql {
    
    Connection conn;
    
    public Connection open(){
        String host = env("DB_HOST", "127.0.0.1");
        String port = env("DB_PORT", "3306");
        String db_name = env("DB_NAME", "gluco_api");
        String user = env("DB_USER", "root");
        String password = env("DB_PASSWORD", "root");
        String sslMode = env("DB_SSL_MODE", "DISABLED");
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_name;
        String parametros = "?useUnicode=true&characterEncoding=utf-8"
                + "&serverTimezone=UTC&sslMode=" + sslMode;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+parametros,user,password);
            return conn;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static String env(String name, String defaultValue){
        String value = System.getenv(name);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }

    void close() {
        if(conn != null){
        try {
        conn.close();
        }catch (Exception e) {
            e.printStackTrace();
            
            }
        }
    }
}
