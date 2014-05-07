import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
	private Connection connection;
    
	PersonDAO() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection("jdbc:mysql://localhost/test",
				"root", "");

	}
	
	public boolean setPerson(Person person) throws SQLException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		PreparedStatement insertStatement = connection
				.prepareStatement("insert into persons(First_Name,Last_Name) values (?,?)");
		insertStatement.setString(1, person.getFirstName());
		insertStatement.setString(2, person.getLastName());
		boolean result;
		int count = insertStatement.executeUpdate();
		if (count > 0) {
			result = true;
		} else {
			throw new SQLException();
		}
		return result;
	}

	public List<Person> getAllPersons() throws SQLException ,InstantiationException,IllegalAccessException,ClassNotFoundException{
		
		List<Person> personList = new ArrayList<Person>();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from persons");
		while (resultSet.next()) {
			Person person = new Person();
			person.setFirstName(resultSet.getString("First_Name"));
			person.setLastName(resultSet.getString("Last_Name"));
			personList.add(person);
		}
		connection.close();
		return personList;
	}
}
