package com.jdbc.util;

public class QueryUtil {
    public static final String INSERT_DEPT = "INSERT INTO department (deptId, deptName, location) VALUES (?, ?, ?)";
    public static final String SELECT_DEPT_BY_ID = "SELECT * FROM department WHERE deptId = ?";
    public static final String SELECT_ALL_DEPTS = "SELECT * FROM department";
    public static final String UPDATE_DEPT = "UPDATE department SET deptName = ?, location = ? WHERE deptId = ?";
    public static final String DELETE_DEPT = "DELETE FROM department WHERE deptId = ?";
}
