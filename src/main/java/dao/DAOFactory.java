package dao;

import db.DbConfig;
import service.ProductServiceImpl;

public class DAOFactory {

	public static ProductDAO createProductDAO() {
		return new ProductServiceImpl(DbConfig.connect());
	}
}
