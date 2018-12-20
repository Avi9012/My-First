package com.example.lenovo.sangam3;

public class User {
    private int ID;
    private String name;
    private String email;
    private String password;

    public void setId(int ID)
    {
        this.ID = ID;
    }

    public int getId()
    {
        return ID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setPassword(String password)
    {
        this.password =  password;
    }

    public String getPassword()
    {
        return password;
    }
}
