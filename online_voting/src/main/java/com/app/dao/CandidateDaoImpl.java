package com.app.dao;

import static com.app.utils.DBUtils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.entities.Candidate;

public class CandidateDaoImpl implements CandidateDao {
	private Connection cn;
	private PreparedStatement pst1, pst2,pst3;

	public CandidateDaoImpl() throws SQLException {
		
		cn = getConnection();
		pst1 = cn.prepareStatement("select * from candidates");
		pst2 = cn.prepareStatement("update candidates set votes=votes+1 where id=?");
		pst3=cn.prepareStatement("select * from candidates order by votes desc limit 2");
		System.out.println("candidate dao created....");
	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException {
		List<Candidate> candidateList = new ArrayList<>();
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				candidateList.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		return candidateList;
	}

	@Override
	public String incrementCandidateVotes(int candidateId) throws SQLException {
		
		pst2.setInt(1, candidateId);
		int updateCount = pst2.executeUpdate();
		if (updateCount == 1)
			return "Votes updated...";
		return "Votes updation failed !!!!!!!!!!!!";
	}

	
	public List<Candidate> getResult() throws SQLException
	{
		List<Candidate> list = new ArrayList<>();
		try(ResultSet rst=pst3.executeQuery())
		{
			while(rst.next())
			{
				 list.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
			return list;
			}
		}
		
		
	
	// clean up
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		
	}

}
