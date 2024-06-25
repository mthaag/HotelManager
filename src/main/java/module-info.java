module br.com.matheus.hotelmanager {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;


    opens br.com.matheus.hotelmanager to javafx.fxml;
    exports br.com.matheus.hotelmanager;
    opens br.com.matheus.hotelmanager.controller to javafx.fxml;
    exports br.com.matheus.hotelmanager.controller;
    opens br.com.matheus.hotelmanager.model.reservas to javafx.base;
    opens br.com.matheus.hotelmanager.model.cliente to javafx.base;
    opens br.com.matheus.hotelmanager.model.usuario to javafx.base;
    opens br.com.matheus.hotelmanager.model.quarto to javafx.base;
}