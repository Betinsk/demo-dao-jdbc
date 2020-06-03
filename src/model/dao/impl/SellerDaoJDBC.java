package model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	
	private java.sql.Connection conn;
	
	public  SellerDaoJDBC(java.sql.Connection connection) {
		this.conn = connection;
	}
	
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName"
				+   "FROM seller INNER JOIN department "
				+	"ON seller.DepartmentId = department.Id "
				+	"WHERE seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = instantianteDepartment(rs);
				Seller obj = instantiateSeller(rs,dep);
				return obj;
			}
			return null;
		}
	catch(SQLException e) {
		throw new DbException(e.getMessage());
	}
		
	finally {
		DB.closeStatement(st);
		DB.closeResultSet(rs);
	}
		
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBrithDate(rs.getDate("BirthDate"));
		obj.setDepartament(dep);
		return obj;
	}


	private Department instantianteDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
			dep.setId(rs.getInt("DepartmentI"));
			dep.setName(rs.getString("DepNane"));
		return dep;
	}


	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
