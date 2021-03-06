package curs.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import curs.banking.model.Address;

public class AddressDAO extends AbstractBaseDAO<Address> {
  private CityDAO mCityDAO;

  public AddressDAO(Connection pConn) {
    super(pConn);
    mCityDAO = new CityDAO(pConn);
  }

  @Override
  protected Address loadFromResultSet(ResultSet pRS) throws SQLException {
    long id = pRS.getLong(1);
    long cityId = pRS.getLong(2);
    String street = pRS.getString(3);
    String number = pRS.getString(4);
    String pc = pRS.getString(5);
    Address addr = new Address();
    addr.setId(id);
    addr.setCity(mCityDAO.findById(cityId));
    addr.setStreet(street);
    addr.setNumber(number);
    addr.setPostalCode(pc);
    return addr;
  }

  @Override
  protected String getSQLForFindAll() {
    return "SELECT ID,CITY_ID,STREET,NUMBER,POSTAL_CODE FROM BANK.ADDRESS";
  }

  @Override
  public Address insert(Address pEntity) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("INSERT INTO BANK.ADDRESS(CITY_ID,STREET,NUMBER,POSTAL_CODE) VALUES(?,?,?,?)",
          Statement.RETURN_GENERATED_KEYS);
      stmt.setLong(1, pEntity.getCity().getId());
      stmt.setString(2, pEntity.getStreet());
      stmt.setString(3, pEntity.getNumber());
      stmt.setString(4, pEntity.getPostalCode());
      stmt.executeUpdate();
      rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        long id = rs.getLong(1);
        return findById(id);
      } else {
        throw new DAOException("Id not found in insert");
      }
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      SQLUtils.closeQuietly(rs, stmt);
    }
  }

  @Override
  public Address update(Address pEntity) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection
          .prepareStatement("UPDATE BANK.ADDRESS SET CITY_ID=?,STREET=?,NUMBER=?,POSTAL_CODE=? WHERE ID=?");
      stmt.setLong(1, pEntity.getCity().getId());
      stmt.setString(2, pEntity.getStreet());
      stmt.setString(3, pEntity.getNumber());
      stmt.setString(4, pEntity.getPostalCode());
      stmt.setLong(5, pEntity.getId());
      stmt.executeUpdate();
      return findById(pEntity.getId());
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      SQLUtils.closeQuietly(rs, stmt);
    }

  }

  @Override
  public void delete(Address pEntity) {
    throw new DAOException("Not implemented!!!");

  }

}
