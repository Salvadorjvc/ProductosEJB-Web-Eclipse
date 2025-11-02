package pe.productos.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
@DataSourceDefinition(
		name="java:app/jdbc/DAE1DS",
		className="com.microsoft.sqlserver.jdbc.SQLServerDriver",
		url="jdbc:sqlserver://localhost:1433;databaseName=DAE1;encrypt=true;trustServerCertificate=true",
		user="TU_USUARIO_AQUI",
		password="TU_CONTRASEÑA_AQUI"
)

public class AppResources {

   // public AppResources() {
        // TODO Auto-generated constructor stub
    //}

}
