package iclient.data;

public class Student
{
    private String email;
    private String fname;
    private String lname;
    private String ID;

    public Student(String email, String fname, String lname, String ID)
    {
        // Set fields
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.ID = ID;
    }

    public String getFname()
    {
        return fname;
    }

    public String getLname()
    {
        return lname;
    }

    public String getEmail()
    {
        return email;
    }

    public String getID()
    {
        return ID;
    }
}
