package sg.edu.nus.iss.day13_lecture.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import sg.edu.nus.iss.day13_lecture.model.Employee;

@Repository
public class EmployeeRepo {
    //directory path
    final String dirPath = System.getProperty("user.dir") + File.separator + "data";
    final String fileName = "employee.txt";

    private List<Employee> employees;
    
    public EmployeeRepo() throws ParseException{
        if (employees == null){
            employees = new ArrayList<Employee>();
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse("1973-04-26");
        Employee em = new Employee("vincent","ang","vinceajy93@gmail.com","98616608",9199,dt,"farrer park 31", 271024);
        employees.add(em);

        dt = df.parse("1993-04-26");
        em = new Employee("Wolonglong","Young","Younglong21@gmail.com","9724212",9992,dt,"Suntec Tower 2", 436322);
        employees.add(em);

        dt = df.parse("1973-04-26");
        em = new Employee("Pixel","Biang","BiangXpixel@gmail.com","8823642",9919,dt,"Hougang Mall", 52421);
        employees.add(em);

    }
    
    public List<Employee> findAll(){
        return employees;
    }
    
    public Boolean save(Employee employee) throws FileNotFoundException{
        Boolean result = employees.add(employee);

        File f = new File(dirPath + File.separator + fileName);
        System.out.println(f.toString());
        OutputStream os = new FileOutputStream(f, true);
        PrintWriter pw = new PrintWriter(os);
        pw.println(employee.toString());
        pw.flush();
        pw.close();
        return result;
    }

    public Boolean delete(Employee employee){
        Boolean result = false;
        int empIndex = employees.indexOf(employee);
        if (empIndex>=0){
            employees.remove(empIndex);
            result = true;
        }

        return result;
    }

    public Boolean update(Employee em){
        Employee emp = employees.stream().filter(e->e.getEmail().equals(em.getEmail())).findFirst().get();

        int empIndex = employees.indexOf(emp);
        if(empIndex >= 0){
            employees.remove(empIndex);
        }
        employees.add(em);
        return true;
    }

    public Employee findByEmailId(String email){
        Employee emp = employees.stream().filter(e->e.getEmail().equals(email)).findFirst().get();
        return emp;
    }
}
