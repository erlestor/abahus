module core {
    requires com.fasterxml.jackson.databind;
    exports core;
    exports jsonworker;
    opens core;
    opens jsonworker;
}
