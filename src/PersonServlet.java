import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(urlPatterns = "/Person")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		List<Person> personList;
		try {

			PersonDAO personDAO = new PersonDAO();
			personList = personDAO.getAllPersons();
			Gson gson = new Gson();
			String name = gson.toJson(personList);
			out.write(name);
		} catch (SQLException e) {
			out.print("SQL Exception" + e.getMessage());
			

		} catch (ClassNotFoundException e) {
			out.print("ClassNotFound Exception" + e.getMessage());

		} catch (IllegalAccessException e) {
			out.print("IllegalAccess Exception" + e.getMessage());

		} catch (InstantiationException e) {
			out.print("Instantiation Exception" + e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		try {
			PersonDAO personDAO = new PersonDAO();
			Person person = new Person();
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			person.setFirstName(firstName);
			person.setLastName(lastName);
			boolean result = personDAO.setPerson(person);
			out.print(result);
			out.flush();
		} catch (ClassNotFoundException e) {
			out.print("ClassNotFound Exception" + e.getMessage());
		} catch (SQLException e) {
			out.print("SQL Exception" + e.getMessage());
		} catch (InstantiationException e) {
			out.print("Instantiation Exception" + e.getMessage());
		} catch (IllegalAccessException e) {
			out.print("IllegalAccess Exception" + e.getMessage());
		}
	}
}
