package database;

import java.util.ArrayList;

public interface DAOInterface<ClassName> {
	
	public ArrayList<ClassName> selectAll();

	public ClassName selectById(ClassName obj);

	public int insert(ClassName obj);

	public int insertAll(ArrayList<ClassName> objs);

	public int delete(ClassName obj);

	public int deleteAll(ArrayList<ClassName> objs);

	public int update(ClassName obj);
}
