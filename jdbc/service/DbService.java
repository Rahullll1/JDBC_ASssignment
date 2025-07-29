package com.jdbc.service;

import java.sql.*;
import java.util.*;
import com.jdbc.model.Department;
import com.jdbc.util.DbUtil;
import com.jdbc.util.QueryUtil;

public class DbService {

    public void addDepartment(Department dept) throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.INSERT_DEPT)) {
            ps.setInt(1, dept.getDeptId());
            ps.setString(2, dept.getDeptName());
            ps.setString(3, dept.getLocation());
            ps.executeUpdate();
        }
    }

    public Department getDepartmentById(int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.SELECT_DEPT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Department(rs.getInt("deptId"), rs.getString("deptName"), rs.getString("location"));
            }
            return null;
        }
    }

    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.SELECT_ALL_DEPTS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                departments.add(new Department(rs.getInt("deptId"), rs.getString("deptName"), rs.getString("location")));
            }
        }
        return departments;
    }

    public boolean updateDepartment(Department dept) throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.UPDATE_DEPT)) {
            ps.setString(1, dept.getDeptName());
            ps.setString(2, dept.getLocation());
            ps.setInt(3, dept.getDeptId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteDepartment(int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.DELETE_DEPT)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
