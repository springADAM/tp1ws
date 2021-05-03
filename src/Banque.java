import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Banque {

    private float nCompte, nCompteEuro;
    private int nBanque;

    Connection connection = null;

    public Banque(float nCompte, float nCompteEuro, int nBanque) {
        this.nCompte = nCompte;
        this.nCompteEuro = nCompteEuro;
        this.nBanque = nBanque;
    }

    public Banque() {

    }

    public void ajoutBanque(Banque banque) {
        connection = Main.openConnection();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO banque(ncompte, ncompteeuro, nbanque) VALUES (?,?,?)");
                preparedStatement.setFloat(1, banque.nCompte);
                preparedStatement.setFloat(2, banque.nCompteEuro);
                preparedStatement.setInt(3, banque.nBanque);
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void modifierBanque(int nBanque, Banque banque) {
        banque.nBanque = nBanque;
        connection = Main.openConnection();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE banque SET ncompte = ?,ncompteeuro = ? WHERE nbanque = ?");
                preparedStatement.setFloat(1, banque.nCompte);
                preparedStatement.setFloat(2, banque.nCompteEuro);
                preparedStatement.setInt(3, banque.nBanque);
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public Banque consultBanque(int nBanque) {
        Banque banque = null;
        connection = Main.openConnection();
        ResultSet resultSet;
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM banque WHERE nbanque = ?");
                preparedStatement.setInt(1, nBanque);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                    banque = new Banque(resultSet.getFloat("ncompte"), resultSet.getFloat("ncompteeuro"), resultSet.getInt("nBanque"));
            } catch (SQLException exception) {
                exception.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return banque;
    }

    public List<Banque> consultListBanque() {
        ArrayList<Banque> banques = new ArrayList<>();
        connection = Main.openConnection();
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM banque ");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                    banques.add(new Banque(resultSet.getFloat("ncompte"), resultSet.getFloat("ncompteeuro"), resultSet.getInt("nBanque")));
            } catch (SQLException exception) {
                exception.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }

        return banques;
    }

    public void supprimBanque(int nBanque) {
        connection = Main.openConnection();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE from banque WHERE nbanque = ?");
                preparedStatement.setFloat(1, nBanque);
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Banque{" +
                "nCompte=" + nCompte +
                ", nCompteEuro=" + nCompteEuro +
                ", nBanque=" + nBanque +
                '}';
    }
}
