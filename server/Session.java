package server;

import java.util.ArrayList;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class Session
{
	private String key;
	private ArrayList<StudentProcessor> students;
	private InstructorProcessor instructor;
	private int ID;

	public Session(String key)
	{
		this.key = key;
		ID = 0;

		students = new ArrayList<StudentProcessor>();
	}

	public String getKey()
	{
		return key;
	}

	public void addStudent(Socket socket, BufferedReader inClient, String[] message)
	{
		StudentProcessor student = new StudentProcessor(this, socket, inClient, message);
		student.start();
		students.add(student);
	}

	public void setInstructor(Socket socket, BufferedReader inClient)
	{
		instructor = new InstructorProcessor(this, socket, inClient);
		instructor.start();
	}

	public InstructorProcessor getInstructor()
	{
		return instructor;
	}

	public ArrayList<StudentProcessor> getStudents()
	{
		return students;
	}

	public int generateID()
	{
		return ID++;
	}

	public void close()
	{
		instructor.stop();

		for(StudentProcessor student : students)
		{
			student.stop();
		}
	}
}
