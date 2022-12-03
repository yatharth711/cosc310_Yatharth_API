import java.sql.SQLException;

import cosc310project_databaseAndLogin.dbKey;
import cosc310project_databaseAndLogin.dbRequest;
import cosc310project_databaseAndLogin.login;
import cosc310project_databaseAndLogin.userKey;

public class testMain {

	public static void main(String[] args) throws Exception {
		dbKey dbk = new dbKey("user","userpw","jdbc:mysql://localhost/wsDB");
		System.out.println(new userKey("derkaiser","projet8",dbk));
		
		

	}

}
