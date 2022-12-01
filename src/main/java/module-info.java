module alocaufc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;


    opens com.alocaufc to javafx.fxml;
    opens com.alocaufc.controllers to javafx.fxml;
    opens com.alocaufc.entities to org.hibernate.orm.core;
    opens com.alocaufc.entities.enums to javafx.fxml;


    exports com.alocaufc;
    exports com.alocaufc.controllers;
    exports com.alocaufc.entities;
}