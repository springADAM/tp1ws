import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static Connection openConnection() {
        final String url = "jdbc:postgresql://localhost:5432/banque";
        String user = "bendabizadam";
        String password = "root";

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return con;
    }
    public static Banque mBanque = new Banque();
    public static void main(String[] args) {
        Banque banque = new Banque(5F, 6F, 9);
        // adding banque
        mBanque.ajoutBanque(banque);
        // editing banque
        mBanque.modifierBanque(9, new Banque(7F, 8F, 9));
        //consulting list of banques
        List<Banque> arrayList = mBanque.consultListBanque();
        for (Banque mBanque : arrayList
        ) {
            System.out.println(mBanque.toString());
        }
        // consulting one banque
        Banque consultedBanque = mBanque.consultBanque(7);
        if(consultedBanque!=null) System.out.println("found " +consultedBanque);
        else System.out.println("not found");
    }
}
