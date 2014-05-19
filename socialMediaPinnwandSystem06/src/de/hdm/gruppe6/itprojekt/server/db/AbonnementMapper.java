package de.hdm.gruppe6.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.gruppe6.itprojekt.shared.bo.Abonnement;

public class AbonnementMapper {

private static AbonnementMapper abonnementMapper = null;

protected AbonnementMapper() {
}

public static AbonnementMapper abonnementMapper() {
if (abonnementMapper == null) {
abonnementMapper = new AbonnementMapper();
}
return abonnementMapper;
}

public Abonnement anlegen(Abonnement abo) throws Exception {
Connection con = DBVerbindung.connection();
Statement stmt = null;

try {
Statement stm = con.createStatement();

/*
* Zunächst schauen wir nach, welches der momentan höchste
* Primärschlüsselwert ist.
*/
ResultSet rs = stm.executeQuery("SELECT MAX(AboID) AS maxid "
+ "FROM abonnement ");

// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
if (rs.next()) {
/*
* abo erhält den bisher maximalen, nun um 1 inkrementierten
* Primärschlüssel.
*/
abo.setId(rs.getInt("maxid") + 1);

stmt = con.createStatement();

stmt.executeUpdate("INSERT INTO abonnement (AboID, User, Pinnwand, ErstellungsZeitpunkt)"
+ "VALUES ("
+ abo.getId()
+ ","
+ abo.getUser() // TODO OBJEKT? eig-> String oder int (Methodenparameter auch ändern
+ "','"
+ abo.getPinnwand() //TODO OBJEKT? eig-> String oder int
+ "',"
+ abo.getErstellungsZeitpunkt() + ")");
}
}

catch (SQLException e2) {
e2.printStackTrace();
throw new Exception("Datenbank fehler!" + e2.toString());
} finally {
DBVerbindung.closeAll(null, stmt, con);
}
return abo;
}

public void loeschen(Abonnement abo) throws Exception {
Connection con = DBVerbindung.connection();
Statement stmt = null;

try {
stmt = con.createStatement();

stmt.executeUpdate("DELETE FROM abonnement "
+ "WHERE AboID=" + abo.getId());

} catch (SQLException e2) {
e2.printStackTrace();
throw new Exception("Datenbankfehler!" + e2.toString());
} finally {
DBVerbindung.closeAll(null, stmt, con);
}
return;
}

public Abonnement findeAnhandID(int aboID) throws Exception {
Connection con = DBVerbindung.connection();
ResultSet rs = null;
Statement stmt = null;

try {
stmt = con.createStatement();

rs = stmt
.executeQuery("SELECT AboID, User, Pinnwand, ErstellungsZeitpunkt" // TODO OBJEKT wird übergeben kein WERT
+ "WHERE AboID="
+ aboID
+ " ORDER BY AboID");
// Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt
// erstellt.
if (rs.next()) {
Abonnement abonnement = new Abonnement();
abonnement.setId(rs.getInt("AboID"));
abonnement.setUser(((Abonnement) rs).getUser());
abonnement.setPinnwand(((Abonnement) rs).getPinnwand());
abonnement.setErstellungsZeitpunkt(rs
.getDate("ErstellungsZeitpunkt"));

return abonnement;
}
} catch (SQLException e2) {
e2.printStackTrace();
throw new Exception("Datenbank fehler!" + e2.toString());
} finally {
DBVerbindung.closeAll(rs, stmt, con);
}

return null;
}

}
