module rest {
    requires transitive core;
    requires com.fasterxml.jackson.databind;
    requires spring.beans;
    requires spring.core;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    
    exports rest;
    opens rest;
}
