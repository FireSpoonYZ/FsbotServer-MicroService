package org.firespoon.fsbotserver.handler;

import com.google.gson.reflect.TypeToken;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.firespoon.fsbotserver.utils.JsonUtils;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CardPropertiesJsonTypeHandler extends BaseTypeHandler<Map<String, Integer>> {
    private static final Type type = new TypeToken<
            Map<String, Integer>
            >() {
    }.getType();

    @Override
    public void setNonNullParameter(
            PreparedStatement ps,
            int i,
            Map<String, Integer> t,
            JdbcType jdbcType
    ) throws SQLException {
        String json = JsonUtils.toJson(t);
        ps.setString(i, json);
    }

    @Override
    public Map<String, Integer> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String json = resultSet.getString(s);
        return JsonUtils.fromJson(json, type);
    }

    @Override
    public Map<String, Integer> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String json = resultSet.getString(i);
        return JsonUtils.fromJson(json, type);
    }

    @Override
    public Map<String, Integer> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String json = callableStatement.getString(i);
        return JsonUtils.fromJson(json, type);
    }
}
