package backend.restapi;

public class LogInResource {

    private String password;
    private String user; 
    private  Jsonworker jsonworker;

    //konstruktør 
    public LogInResource(String password, String user){
        this.user= user; 
        this.password=password; 
        
    }

    public getUser(){
        return jsonworker.getUser(); 
    }

    public getPassword(){
        return this.password; 
    }

@Path ("/logIn")


}

