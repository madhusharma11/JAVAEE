package com.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.app.entities.Candidate;

public interface CandidateDao {

	List<Candidate> getAllCandidates() throws SQLException;
	String incrementCandidateVotes(int candidateId) throws SQLException;

	public List<Candidate> getResult() throws SQLException;
}
