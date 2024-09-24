package com.top.effitopia.mapper.typehandler;

import com.top.effitopia.enumeration.MemberRole;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(MemberRole.class)
public class MemberRoleTypeHandler extends BaseTypeHandler<MemberRole> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, MemberRole parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public MemberRole getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return MemberRole.valueOf(rs.getString(columnName));
    }

    @Override
    public MemberRole getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return MemberRole.valueOf(rs.getString(columnIndex));
    }

    @Override
    public MemberRole getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return MemberRole.valueOf(cs.getString(columnIndex));
    }
}
