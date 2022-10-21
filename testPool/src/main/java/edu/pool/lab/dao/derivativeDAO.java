package edu.pool.lab.dao;

import edu.pool.lab.model.derivative;

import java.util.List;

public interface derivativeDAO {
    public List<derivative> list(String code);
    public List<derivative> listAll();
}
